package mouda.backend.moim.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.moim.domain.Chamyo;
import mouda.backend.moim.domain.Chat;
import mouda.backend.moim.domain.Moim;
import mouda.backend.moim.implement.finder.ChamyoFinder;
import mouda.backend.moim.implement.finder.ChatFinder;
import mouda.backend.moim.implement.finder.MoimFinder;
import mouda.backend.moim.implement.validator.ChamyoValidator;
import mouda.backend.moim.implement.validator.MoimValidator;
import mouda.backend.moim.implement.writer.ChatWriter;
import mouda.backend.moim.presentation.request.chat.ChatCreateRequest;
import mouda.backend.moim.presentation.request.chat.DateTimeConfirmRequest;
import mouda.backend.moim.presentation.request.chat.LastReadChatRequest;
import mouda.backend.moim.presentation.request.chat.PlaceConfirmRequest;
import mouda.backend.moim.presentation.response.chat.ChatFindDetailResponse;
import mouda.backend.moim.presentation.response.chat.ChatFindUnloadedResponse;
import mouda.backend.moim.presentation.response.chat.ChatPreviewResponse;
import mouda.backend.moim.presentation.response.chat.ChatPreviewResponses;
import mouda.backend.notification.business.NotificationService;
import mouda.backend.notification.domain.NotificationType;

@Transactional
@Service
@RequiredArgsConstructor
public class ChatService {

	private final NotificationService notificationService;
	private final MoimValidator moimValidator;
	private final MoimFinder moimFinder;
	private final ChatFinder chatFinder;
	private final ChatWriter chatWriter;
	private final ChamyoValidator chamyoValidator;
	private final ChamyoFinder chamyoFinder;

	public void createChat(long darakbangId, ChatCreateRequest chatCreateRequest, DarakbangMember darakbangMember) {
		Moim moim = moimFinder.read(chatCreateRequest.moimId(), darakbangId);
		chamyoValidator.exists(moim.getId(), darakbangMember);

		Chat chat = chatCreateRequest.toEntity(moim, darakbangMember);
		chatWriter.save(chat);

		notificationService.notifyToMembers(NotificationType.NEW_CHAT, darakbangId, moim, darakbangMember);
	}

	@Transactional(readOnly = true)
	public ChatFindUnloadedResponse findUnloadedChats(
		long darakbangId, long recentChatId, long moimId, DarakbangMember darakbangMember
	) {
		moimValidator.validateMoimExists(moimId, darakbangId);
		chamyoValidator.exists(moimId, darakbangMember);
		List<Chat> chats = chatFinder.readAllUnloadedChats(moimId, recentChatId);

		return new ChatFindUnloadedResponse(chats.stream()
			.map(chat -> ChatFindDetailResponse.toResponse(chat, chat.isMyMessage(darakbangMember.getId())))
			.toList());
	}

	public void confirmPlace(
		long darakbangId, PlaceConfirmRequest placeConfirmRequest, DarakbangMember darakbangMember
	) {
		Moim moim = moimFinder.read(placeConfirmRequest.moimId(), darakbangId);
		chamyoValidator.validateMoimer(moim, darakbangMember);

		Chat chat = placeConfirmRequest.toEntity(moim, darakbangMember);
		chatWriter.save(chat);
		moim.confirmPlace(placeConfirmRequest.place());

		notificationService.notifyToMembers(NotificationType.MOIM_PLACE_CONFIRMED, darakbangId, moim, darakbangMember);
	}

	public void confirmDateTime(
		long darakbangId, DateTimeConfirmRequest dateTimeConfirmRequest, DarakbangMember darakbangMember
	) {
		Moim moim = moimFinder.read(dateTimeConfirmRequest.moimId(), darakbangId);
		chamyoValidator.validateMoimer(moim, darakbangMember);

		Chat chat = dateTimeConfirmRequest.toEntity(moim, darakbangMember);
		chatWriter.save(chat);
		moim.confirmDateTime(dateTimeConfirmRequest.date(), dateTimeConfirmRequest.time());

		notificationService.notifyToMembers(NotificationType.MOIM_TIME_CONFIRMED, darakbangId, moim, darakbangMember);
	}

	public ChatPreviewResponses findChatPreview(long darakbangId, DarakbangMember darakbangMember) {
		List<Chamyo> chamyos = chamyoFinder.readAllOrderByLastChat(darakbangId, darakbangMember);
		List<ChatPreviewResponse> chatPreviews = chamyos.stream()
			.map(chamyo -> getChatPreviewResponse(chamyo, darakbangId))
			.toList();

		return new ChatPreviewResponses(chatPreviews);
	}

	private ChatPreviewResponse getChatPreviewResponse(Chamyo chamyo, long darakbangId) {
		String lastContent = chatFinder.findLastChatContent(chamyo.getMoim().getId());
		int currentPeople = moimFinder.countCurrentPeople(chamyo.getMoim().getId(), darakbangId);

		return ChatPreviewResponse.toResponse(chamyo, currentPeople, lastContent);
	}

	public void createLastChat(
		long darakbangId, long moimId, LastReadChatRequest lastReadChatRequest, DarakbangMember darakbangMember
	) {
		moimValidator.validateMoimExists(moimId, darakbangId);
		Chamyo chamyo = chamyoFinder.read(moimId, darakbangMember);
		chamyo.updateLastChat(lastReadChatRequest.lastReadChatId());
	}

	public void openChatRoom(Long darakbangId, Long moimId, DarakbangMember darakbangMember) {
		Moim moim = moimFinder.read(moimId, darakbangId);
		chamyoValidator.validateMoimer(moim, darakbangMember);
		moim.openChat();
	}
}
