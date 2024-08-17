package mouda.backend.chamyo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mouda.backend.chamyo.domain.Chamyo;
import mouda.backend.member.domain.Member;
import mouda.backend.moim.domain.Moim;

public interface ChamyoRepository extends JpaRepository<Chamyo, Long> {

	Optional<Chamyo> findByMoimIdAndMemberId(Long moimId, Long id);

	List<Chamyo> findAllByMoimId(Long moimId);

	int countByMoim(Moim moim);

	boolean existsByMoimAndMember(Moim moim, Member member);

	List<Chamyo> findAllByMemberId(Long memberId);

	void deleteAllByMoimId(Long moimId);

	void deleteByMoimAndMember(Moim moim, Member member);

	@Query("SELECT c.member.id FROM Chamyo c WHERE c.moim.id = :moimId AND c.moimRole = 'MOIMER'")
	Long findMoimerIdByMoimId(@Param("moimId") Long moimId);
}
