package mouda.backend.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "멤버 정보", description = "로그인 회원의 정보 조회할 때 사용")
public record MemberFindResponse(
	@Schema(description = "닉네임", example = "테니")
	String nickname,

	@Schema(description = "프로필 사진", example = "profile.png")
	String profile
) {
}
