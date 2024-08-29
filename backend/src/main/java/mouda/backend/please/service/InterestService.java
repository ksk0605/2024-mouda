package mouda.backend.please.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.please.domain.Interest;
import mouda.backend.please.domain.Please;
import mouda.backend.please.dto.request.InterestUpdateRequest;
import mouda.backend.please.exception.PleaseErrorMessage;
import mouda.backend.please.exception.PleaseException;
import mouda.backend.please.repository.InterestRepository;
import mouda.backend.please.repository.PleaseRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestService {

	private final PleaseRepository pleaseRepository;
	private final InterestRepository interestRepository;

	public void updateInterest(Long darakbangId, DarakbangMember darakbangMember, InterestUpdateRequest request) {
		Please please = pleaseRepository.findById(request.pleaseId())
			.orElseThrow(() -> new PleaseException(HttpStatus.NOT_FOUND, PleaseErrorMessage.NOT_FOUND));
		if (please.isNotInDarakbang(darakbangId)) {
			throw new PleaseException(HttpStatus.NOT_FOUND, PleaseErrorMessage.PLEASE_NOT_IN_DARAKBANG);
		}

		if (request.isInterested()) {
			addInterest(darakbangMember, please);
			return;
		}
		removeInterest(darakbangMember, please);
	}

	private void addInterest(DarakbangMember darakbangMember, Please please) {
		boolean isInterestExists = interestRepository.existsByDarakbangMemberIdAndPleaseId(darakbangMember.getId(),
			please.getId());

		if (!isInterestExists) {
			Interest newInterest = Interest.builder()
				.darakbangMember(darakbangMember)
				.please(please)
				.build();
			interestRepository.save(newInterest);
		}
	}

	private void removeInterest(DarakbangMember darakbangMember, Please please) {
		interestRepository.findByDarakbangMemberIdAndPleaseId(darakbangMember.getId(), please.getId())
			.ifPresent(interestRepository::delete);
	}
}
