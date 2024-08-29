package mouda.backend.darakbang.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.darakbang.domain.Darakbang;

@Builder
@Schema(name = "다락방 초대코드 응답", description = "다락방장의 초대코드 조회 요청에 대한 응답")
public record InvitationCodeResponse(
	@Schema(description = "다락방 초대코드", example = "SOFABAB")
	String code
) {

	public static InvitationCodeResponse toResponse(Darakbang darakbang) {
		return InvitationCodeResponse.builder()
			.code(darakbang.getCode())
			.build();
	}
}
