package mouda.backend.chat.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.chat.domain.Chat;
import mouda.backend.chat.domain.ChatType;

@Builder
@Schema(name = "채팅 상세 정보", description = "조회하지 않은 채팅 상세 정보를 조회할 때 사용")
public record ChatFindDetailResponse(
	@Schema(description = "채팅 ID", example = "1")
	long chatId,

	@Schema(description = "채팅 내용", example = "안녕하세요")
	String content,

	@Schema(description = "작성자 본인인지 확인")
	boolean isMyMessage,

	@Schema(description = "채팅 작성자 닉네임", example = "상돌")
	String nickname,

	@Schema(description = "채팅을 전송한 날짜", pattern = "yyyy-MM-dd", example = "2030-12-25")
	LocalDate date,

	@Schema(description = "채팅을 전송한 시간", type = "string", pattern = "HH:mm", example = "12:00")
	LocalTime time,

	@Schema(description = "채팅 종류", example = "BASIC")
	ChatType chatType
) {
	public static ChatFindDetailResponse toResponse(Chat chat, boolean isMyMessage) {
		return ChatFindDetailResponse.builder()
			.chatId(chat.getId())
			.content(chat.getContent())
			.isMyMessage(isMyMessage)
			.nickname(chat.getDarakbangMember().getNickname())
			.date(chat.getDate())
			.time(chat.getTime())
			.chatType(chat.getChatType())
			.build();
	}
}
