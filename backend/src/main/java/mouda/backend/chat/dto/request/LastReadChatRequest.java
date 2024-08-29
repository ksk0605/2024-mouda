package mouda.backend.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "마지막으로 읽은 채팅 저장 요청", description = "마지막으로 읽은 채팅 ID를 저장할 때 사용")
public record LastReadChatRequest(
	@NotNull
	@Schema(description = "저장할 채팅의 모임 ID", example = "1")
	Long moimId,

	@NotNull
	@Schema(description = "마지막으로 읽은 채팅 ID", example = "1")
	Long lastReadChatId
) {
}
