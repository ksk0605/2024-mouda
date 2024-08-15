package mouda.backend.darakbangmember.repository.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mouda.backend.darakbangmember.domain.DarakbangMember;

@Repository
public interface DarakbangMemberRepository extends JpaRepository<DarakbangMember, Long> {

	List<DarakbangMember> findAllByMemberId(long memberId);
}
