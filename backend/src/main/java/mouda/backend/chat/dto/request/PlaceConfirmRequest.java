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

@Schema(name = "모임 장소 확정 요청", description = "모임 장소를 확정하는 채팅을 전송할 때 사용")
public record PlaceConfirmRequest(
	@NotNull
	@Schema(description = "채팅을 전송할 모임 ID", example = "1")
	Long moimId,

	@NotBlank
	@Schema(description = "확정할 모임 장소", maxLength = 100, example = "루터회관")
	String place
) {
	public Chat toEntity(Moim moim, DarakbangMember darakbangMember) {
		return Chat.builder()
			.content(place)
			.moim(moim)
			.date(LocalDate.now())
			.time(LocalTime.now())
			.darakbangMember(darakbangMember)
			.chatType(ChatType.PLACE)
			.build();
	}
}
