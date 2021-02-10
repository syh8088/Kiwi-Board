package kiwi.board.domain.roleHierarchy.repository;

import kiwi.board.domain.roleHierarchy.model.entity.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByChildName(String roleName);
}