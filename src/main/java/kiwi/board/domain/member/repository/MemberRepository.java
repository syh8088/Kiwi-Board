package kiwi.board.domain.member.repository;

import kiwi.board.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findById(String id);

    Member findByEmail(String email);
}
