package mouda.backend.fixture;

import java.time.LocalDate;
import java.time.LocalTime;

import mouda.backend.chat.domain.Chat;
import mouda.backend.chat.domain.ChatType;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.moim.domain.Moim;

public class ChatFixture {

	public static Chat getChatWithMemberAtMoim(DarakbangMember member, Moim moim) {
		return Chat.builder()
			.time(LocalTime.now())
			.date(LocalDate.now())
			.content("안녕하쎄요")
			.member(member)
			.moim(moim)
			.chatType(ChatType.BASIC)
			.build();
	}
}
