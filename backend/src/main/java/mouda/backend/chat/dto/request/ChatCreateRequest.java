package mouda.backend.chat.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mouda.backend.chat.domain.Chat;
import mouda.backend.chat.domain.ChatType;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.moim.domain.Moim;

@Schema(name = "채팅 생성 요청", description = "채팅을 전송할 때 사용")
public record ChatCreateRequest(
	@NotNull
	@Schema(description = "채팅을 전송할 모임 ID", example = "1")
	Long moimId,

	@NotBlank
	@Schema(description = "채팅 내용", maxLength = 255, example = "안녕하세요")
	String content
) {
	public Chat toEntity(Moim moim, DarakbangMember darakbangMember) {
		return Chat.builder()
			.content(content)
			.date(LocalDate.now())
			.time(LocalTime.now())
			.darakbangMember(darakbangMember)
			.moim(moim)
			.chatType(ChatType.BASIC)
			.build();
	}
}
