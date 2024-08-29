package mouda.backend.darakbang.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "가입한 다락방 목록 정보", description = "가입한 다락방 목록 조회 요청에 대한 응답")
public record DarakbangResponses(
	List<DarakbangResponse> darakbangResponses
) {

	public static DarakbangResponses toResponse(List<DarakbangResponse> responses) {
		return DarakbangResponses.builder()
			.darakbangResponses(responses)
			.build();
	}
}
