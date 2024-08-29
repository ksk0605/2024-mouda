package mouda.backend.please.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "관심 상태 변경 요청", description = "관심 상태를 변경할 때 사용")
public record InterestUpdateRequest(
	@NotNull
	@Schema(description = "관심을 변경할 해주세요 ID", example = "1")
	Long pleaseId,

	@NotNull
	@Schema(description = "관심 있으면 true, 없으면 false", example = "true")
	Boolean isInterested
) {
}
