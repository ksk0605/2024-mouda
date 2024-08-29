package mouda.backend.darakbang.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.darakbang.domain.Darakbang;

@Builder
@Schema(name = "다락방 이름 응답", description = "다락방 ID로 조회 요청에 대한 응답")
public record DarakbangNameResponse(
	@Schema(description = "다락방 이름", example = "모우다")
	String name
) {

	public static DarakbangNameResponse toResponse(Darakbang darakbang) {
		return DarakbangNameResponse.builder()
			.name(darakbang.getName())
			.build();
	}
}
