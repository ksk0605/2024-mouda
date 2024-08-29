package mouda.backend.darakbangmember.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import mouda.backend.darakbangmember.domain.DarakbangMember;

@Builder
@Schema(name = "다락방 멤버 조회 응답", description = "다락방 멤버 정보 조회 요청에 대한 응답")
public record DarakbangMemberResponse(
	@Schema(description = "다락방 멤버 ID", example = "1")
	long memberId,

	@Schema(description = "다락방 멤버 닉네임", example = "테니")
	String nickname,

	@Schema(description = "다락방 멤버 프로필 사진", example = "profile.png")
	String profile
) {

	public static DarakbangMemberResponse toResponse(DarakbangMember darakbangMember) {
		return DarakbangMemberResponse.builder()
			.memberId(darakbangMember.getMemberId())
			.nickname(darakbangMember.getNickname())
			.profile("")
			.build();
	}
}
