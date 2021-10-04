package kiwi.board.domain.role.service.query;

import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.role.model.entity.Role;
import kiwi.board.domain.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleQueryService {

    private final RoleRepository roleRepository;

    public List<Role> selectAllRolesByMember(Member member) {
        return roleRepository.selectAllRolesByMember(member);
    }
}
