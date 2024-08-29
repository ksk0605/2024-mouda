package mouda.backend.darakbang.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import mouda.backend.darakbang.domain.Darakbang;
import mouda.backend.darakbangmember.domain.DarakBangMemberRole;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.member.domain.Member;

@Schema(name = "다락방 가입 요청", description = "다락방에 가입할 때 사용")
public record DarakbangEnterRequest(
	@NotNull
	@Schema(description = "다락방에서 사용할 닉네임", maxLength = 10, example = "테니")
	String nickname
) {
	public DarakbangMember toEntity(Darakbang darakbang, Member member) {
		return DarakbangMember.builder()
			.darakbang(darakbang)
			.memberId(member.getId())
			.nickname(nickname)
			.role(DarakBangMemberRole.MEMBER)
			.build();
	}
}
