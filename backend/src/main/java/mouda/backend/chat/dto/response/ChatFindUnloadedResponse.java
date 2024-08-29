package mouda.backend.chat.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "채팅 상세 정보 목록", description = "채팅방에서 채팅을 조회할 때 사용")
public record ChatFindUnloadedResponse(
	List<ChatFindDetailResponse> chats
) {
}
