package gdg.swu.planmeal.member.repository;

import gdg.swu.planmeal.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
