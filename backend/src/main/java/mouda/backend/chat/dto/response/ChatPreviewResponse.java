package mouda.backend.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.chamyo.domain.Chamyo;

@Builder
@Schema(name = "채팅 미리보기 정보", description = "채팅 목록을 조회할 때 사용")
public record ChatPreviewResponse(
	@Schema(description = "모임 ID", example = "1")
	Long moimId,

	@Schema(description = "모임 제목", example = "치킨먹을 사람")
	String title,

	@Schema(description = "참여 인원", example = "3")
	int currentPeople,

	@Schema(description = "모임 전이면 true, 모임 후면 false")
	boolean isStarted,

	@Schema(description = "마지막 채팅 내용", example = "마지막으로")
	String lastContent,

	@Schema(description = "마지막 채팅 아이디", example = "10")
	long lastReadChatId
) {

	public static ChatPreviewResponse toResponse(
		Chamyo chamyo,
		int currentPeople,
		String lastContent
	) {
		return ChatPreviewResponse.builder()
			.moimId(chamyo.getMoim().getId())
			.title(chamyo.getMoim().getTitle())
			.currentPeople(currentPeople)
			.isStarted(chamyo.getMoim().isPastMoim())
			.lastContent(lastContent)
			.lastReadChatId(chamyo.getLastReadChatId())
			.build();
	}
}
