package mouda.backend.darakbangmember.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.darakbangmember.domain.DarakBangMemberRole;

@Builder
@Schema(name = "다락방 멤버 역할 조회 응답", description = "다락방 멤버 역할 조회 요청에 대한 응답")
public record DarakbangMemberRoleResponse(
	@Schema(description = "다락방 멤버 ID", examples = "MANAGER")
	String role
) {
	private static final String EMPTY_ROLE = "OUTSIDER";

	public static DarakbangMemberRoleResponse toResponse(DarakBangMemberRole role) {
		return DarakbangMemberRoleResponse.builder()
			.role(role.name())
			.build();
	}

	public static DarakbangMemberRoleResponse toResponse() {
		return DarakbangMemberRoleResponse.builder()
			.role(EMPTY_ROLE)
			.build();
	}
}
