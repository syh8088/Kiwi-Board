package kiwi.board.domain.role.repository;

import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.role.model.entity.Role;

import java.util.List;

public interface RoleRepositoryCustom {

    List<Role> selectAllRolesByMember(Member member);
}
