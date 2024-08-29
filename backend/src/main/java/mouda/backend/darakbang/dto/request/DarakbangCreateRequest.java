package mouda.backend.darakbang.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import mouda.backend.darakbang.domain.Darakbang;

@Schema(name = "다락방 생성 요청", description = "다락방을 생성할 때 사용")
public record DarakbangCreateRequest(
	@NotNull
	@Schema(description = "다락방 이름", maxLength = 40, example = "모우다")
	String name,

	@NotNull
	@Schema(description = "다락방에서 사용할 닉네임", maxLength = 10, example = "테니")
	String nickname
) {
	public Darakbang toEntity(String code) {
		return Darakbang.builder()
			.name(name)
			.code(code)
			.build();
	}
}
