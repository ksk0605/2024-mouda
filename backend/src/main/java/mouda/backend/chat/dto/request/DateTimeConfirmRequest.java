package mouda.backend.chat.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import mouda.backend.chat.domain.Chat;
import mouda.backend.chat.domain.ChatType;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.moim.domain.Moim;

@Schema(name = "모임 날짜/시간 확정 요청", description = "모임 날짜/시간을 확정하는 채팅을 전송할 때 사용")
public record DateTimeConfirmRequest(
	@NotNull
	@Schema(description = "채팅을 전송할 모임 ID", example = "1")
	Long moimId,

	@NotNull
	@Schema(description = "확정할 모임 날짜", pattern = "yyyy-MM-dd", example = "2030-12-25")
	LocalDate date,

	@NotNull
	@Schema(description = "확정할 모임 시간", pattern = "HH:mm", type = "string", example = "20:00")
	LocalTime time
) {
	public Chat toEntity(Moim moim, DarakbangMember darakbangMember) {
		return Chat.builder()
			.content(date.toString() + " " + time.toString())
			.moim(moim)
			.date(LocalDate.now())
			.time(LocalTime.now())
			.darakbangMember(darakbangMember)
			.chatType(ChatType.DATETIME)
			.build();
	}
}


