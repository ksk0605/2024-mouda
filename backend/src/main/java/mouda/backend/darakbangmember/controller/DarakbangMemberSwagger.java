package mouda.backend.darakbangmember.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mouda.backend.common.RestResponse;
import mouda.backend.config.argumentresolver.LoginDarakbangMember;
import mouda.backend.config.argumentresolver.LoginMember;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.darakbangmember.dto.response.DarakbangMemberResponses;
import mouda.backend.darakbangmember.dto.response.DarakbangMemberRoleResponse;
import mouda.backend.exception.ErrorResponse;
import mouda.backend.member.domain.Member;

public interface DarakbangMemberSwagger {

	@Operation(summary = "다락방 멤버 목록 조회", description = "다락방 멤버 목록을 조회한다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "다락방 멤버 목록 조회 성공!"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "403", description = "조회 권한이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "403", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<RestResponse<DarakbangMemberResponses>> findAllDarakbangMembers(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember member
	);

	@Operation(summary = "다락방 멤버 권한 조회", description = "다락방 멤버 권한을 조회한다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "다락방 멤버 권한 조회 성공!"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<RestResponse<DarakbangMemberRoleResponse>> findDarakbangMemberRole(
		@PathVariable Long darakbangId,
		@LoginMember Member member
	);
}
