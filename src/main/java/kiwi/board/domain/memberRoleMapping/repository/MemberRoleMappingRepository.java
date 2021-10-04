package kiwi.board.domain.memberRoleMapping.repository;

import kiwi.board.domain.memberRoleMapping.model.entity.MemberRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleMappingRepository extends JpaRepository<MemberRoleMapping, Long> {
}
