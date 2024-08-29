package mouda.backend.darakbang.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.darakbang.domain.Darakbang;

@Builder
@Schema(name = "다락방 초대코드 검증 응답", description = "다락방 초대코드 검증 요청에 대한 응답")
public record CodeValidationResponse(
	@Schema(description = "초대코드 검증한 다락방 ID", example = "1")
	long darakbangId,

	@Schema(description = "초대코드 검증한 다락방 이름", example = "모우다")
	String name
) {

	public static CodeValidationResponse toResponse(Darakbang darakbang) {
		return CodeValidationResponse.builder()
			.darakbangId(darakbang.getId())
			.name(darakbang.getName())
			.build();
	}
}
