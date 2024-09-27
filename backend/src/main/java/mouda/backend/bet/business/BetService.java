package mouda.backend.bet.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mouda.backend.bet.domain.Bet;
import mouda.backend.bet.domain.Loser;
import mouda.backend.bet.implement.BetFinder;
import mouda.backend.bet.implement.BetWriter;
import mouda.backend.bet.presentation.request.BetCreateRequest;
import mouda.backend.bet.presentation.response.BetFindAllResponses;
import mouda.backend.bet.presentation.response.BetFindResponse;
import mouda.backend.bet.presentation.response.BetResultResponse;
import mouda.backend.darakbangmember.domain.DarakbangMember;

@Service
@RequiredArgsConstructor
public class BetService {

	private final BetFinder betFinder;
	private final BetWriter betWriter;

	public BetFindAllResponses findAllBets(long darakbangId) {
		List<Bet> bets = betFinder.findAllByDarakbangId(darakbangId);
		return BetFindAllResponses.toResponse(bets);
	}

	public BetFindResponse findBet(long darakbangId, long betId, DarakbangMember darakbangMember) {
		Bet bet = betFinder.find(darakbangId, betId);
		return BetFindResponse.toResponse(bet, darakbangMember);
	}

	public long createBet(long darakbangId, BetCreateRequest betRequest, DarakbangMember darakbangMember) {
		Bet bet = betRequest.toBet(darakbangMember.getId());
		long savedBetId = betWriter.save(darakbangId, bet);
		betWriter.participate(darakbangId, savedBetId, darakbangMember);

		return savedBetId;
	}

	public void participateBet(long darakbangId, long betId, DarakbangMember darakbangMember) {
		betWriter.participate(darakbangId, betId, darakbangMember);
	}

	@Transactional(readOnly = true)
	public BetResultResponse findBetResult(long darakbangId, long betId) {
		Loser loser = betFinder.findResult(darakbangId, betId);
		return BetResultResponse.from(loser);
	}
}

