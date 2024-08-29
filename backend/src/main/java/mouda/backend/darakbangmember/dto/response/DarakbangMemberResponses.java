package mouda.backend.darakbangmember.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "다락방 멤버 목록 응답", description = "다락방 멤버 목록 조회 요청에 대한 응답")
public record DarakbangMemberResponses(
	List<DarakbangMemberResponse> darakbangMemberResponses
) {

	public static DarakbangMemberResponses toResponse(List<DarakbangMemberResponse> darakbangMemberResponse) {
		return DarakbangMemberResponses.builder()
			.darakbangMemberResponses(darakbangMemberResponse)
			.build();
	}
}
