package mouda.backend.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mouda.backend.chat.dto.request.ChatCreateRequest;
import mouda.backend.chat.dto.request.DateTimeConfirmRequest;
import mouda.backend.chat.dto.request.LastReadChatRequest;
import mouda.backend.chat.dto.request.PlaceConfirmRequest;
import mouda.backend.chat.dto.response.ChatFindUnloadedResponse;
import mouda.backend.chat.dto.response.ChatPreviewResponses;
import mouda.backend.common.RestResponse;
import mouda.backend.config.argumentresolver.LoginDarakbangMember;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.exception.ErrorResponse;

public interface ChatSwagger {

	@Operation(summary = "채팅 생성", description = "한 건의 채팅 메시지를 보낸다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "채팅 생성 성공!"),
		@ApiResponse(responseCode = "400", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "모임에 참여한 회원만 채팅을 조회할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<Void> createChat(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember darakbangMember,
		@RequestBody ChatCreateRequest chatCreateRequest
	);

	@Operation(summary = "채팅 조회", description = "아직 조회되지 않은 채팅 내역을 조회한다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "채팅 조회 성공!"),
		@ApiResponse(responseCode = "400", description = "모임에 참여한 회원만 채팅을 조회할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "최근 조회된 채팅 아이디를 잘 못 입력하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<RestResponse<ChatFindUnloadedResponse>> findUnloadedChats(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember darakbangMember,
		@RequestParam Long recentChatId,
		@RequestParam Long moimId
	);

	@Operation(summary = "장소 확정", description = "작성자가 장소를 확정하는 채팅을 전송합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "장소 확정 성공!"),
		@ApiResponse(responseCode = "400", description = "모임에 참여한 회원만 채팅을 조회할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "모이머만 모임 장소를 확정할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "모임 장소를 입력해주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "모임 장소를 조금 더 짧게 입력해주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<Void> confirmPlace(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember darakbangMember,
		@RequestBody PlaceConfirmRequest placeConfirmRequest
	);

	@Operation(summary = "날짜 시간 확정", description = "작성자가 날짜와 시간을 확정하는 채팅을 전송합니다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "날짜 시간 확정 성공!"),
		@ApiResponse(responseCode = "400", description = "모임에 참여한 회원만 채팅을 조회할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "모이머만 날짜와 시간을 확정할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "모임 날짜를 현재 시점 이후로 입력해주세요.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<Void> confirmDateTime(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember darakbangMember,
		@RequestBody DateTimeConfirmRequest dateTimeConfirmRequest
	);

	@Operation(summary = "채팅방 목록 조회", description = "채팅방 목록을 조회한다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "채팅방 조회 성공!"),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<RestResponse<ChatPreviewResponses>> findChatPreviews(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember darakbangMember
	);

	@Operation(summary = "마지막 읽은 채팅 저장", description = "마지막 읽은 채팅을 저장한다.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "마지막 채팅 저장 성공!"),
		@ApiResponse(responseCode = "400", description = "모임에 참여한 회원만 채팅을 조회할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<Void> createLastReadChatId(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember darakbangMember,
		@RequestBody LastReadChatRequest lastReadChatRequest
	);

	@Operation(summary = "채팅방 열기", description = "채팅방을 연다!")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "채팅방 열기 성공!"),
		@ApiResponse(responseCode = "400", description = "모임에 참여한 회원만 채팅을 조회할 수 있습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "400", description = "채팅은 모이머만 열 수 있습니다", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "가입한 다락방이 아닙니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "다락방이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "모임이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	ResponseEntity<Void> openChatRoom(
		@PathVariable Long darakbangId,
		@LoginDarakbangMember DarakbangMember member,
		@RequestParam("moimId") Long moimId
	);
}
