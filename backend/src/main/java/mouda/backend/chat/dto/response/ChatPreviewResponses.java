package mouda.backend.chat.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "채팅 미리보기 정보 목록", description = "채팅 목록을 조회할 때 사용")
public record ChatPreviewResponses(
	List<ChatPreviewResponse> chatPreviewResponses
) {
}
