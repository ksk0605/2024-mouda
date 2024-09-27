package mouda.backend.bet.business;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mouda.backend.bet.domain.Bet;
import mouda.backend.bet.entity.BetDarakbangMemberEntity;
import mouda.backend.bet.entity.BetEntity;
import mouda.backend.bet.infrastructure.BetDarakbangMemberRepository;
import mouda.backend.bet.infrastructure.BetRepository;
import mouda.backend.bet.presentation.request.BetCreateRequest;
import mouda.backend.common.fixture.DarakbangSetUp;
import mouda.backend.darakbangmember.infrastructure.DarakbangMemberRepository;

@SpringBootTest
class BetServiceTest extends DarakbangSetUp {

	@Autowired
	BetService betService;

	@Autowired
	BetRepository betRepository;

	@Autowired
	BetDarakbangMemberRepository betDarakbangMemberRepository;

	@Autowired
	DarakbangMemberRepository darakbangMemberRepository;

	@DisplayName("새로운 안내면진다를 생성한다.")
	@Test
	void createBet() {
		// given
		BetCreateRequest betRequest = new BetCreateRequest("테니바보", 10);
		// when
		long createdBetId = betService.createBet(1L, betRequest, darakbangHogee);
		//then
		assertThat(createdBetId).isEqualTo(1L);
	}

	@DisplayName("안내면진다에 참여한다.")
	@Test
	void participateBet() {
		// given
		long darakbangId = 1L;
		BetCreateRequest betRequest = new BetCreateRequest("테니바보", 10);
		Bet bet = betRequest.toBet(2L);
		BetEntity betEntity = betRepository.save(BetEntity.create(bet, darakbangId));

		long betId = betEntity.getId();

		// when
		betService.participateBet(darakbangId, betId, darakbangHogee);

		//then
		List<BetDarakbangMemberEntity> entities = betDarakbangMemberRepository.findAll();
		assertThat(entities).hasSize(1);
	}
}
