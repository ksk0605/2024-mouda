package mouda.backend.darakbang.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.darakbangmember.domain.DarakbangMember;

@Builder
@Schema(name = "다락방 정보 응답", description = "다락방 조회 요청에 대한 응답")
public record DarakbangResponse(
	@Schema(description = "초대코드 검증한 다락방 ID", example = "1")
	long darakbangId,

	@Schema(description = "다락방 이름", example = "모우다")
	String name
) {

	public static DarakbangResponse toResponse(DarakbangMember darakbangMember) {
		return DarakbangResponse.builder()
			.darakbangId(darakbangMember.getDarakbang().getId())
			.name(darakbangMember.getDarakbangName())
			.build();
	}
}
