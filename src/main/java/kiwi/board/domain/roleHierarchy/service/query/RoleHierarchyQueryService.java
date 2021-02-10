package kiwi.board.domain.roleHierarchy.service.query;

import kiwi.board.domain.roleHierarchy.model.entity.RoleHierarchy;
import kiwi.board.domain.roleHierarchy.repository.RoleHierarchyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleHierarchyQueryService {

    private final RoleHierarchyRepository roleHierarchyRepository;

    public String findAllHierarchy() {

        List<RoleHierarchy> rolesHierarchy = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> itr = rolesHierarchy.iterator();
        StringBuilder concatedRoles = new StringBuilder();
        while (itr.hasNext()) {
            RoleHierarchy roleHierarchy = itr.next();
            if (roleHierarchy.getParentName() != null) {
                concatedRoles.append(roleHierarchy.getParentName().getChildName());
                concatedRoles.append(" > ");
                concatedRoles.append(roleHierarchy.getChildName());
                concatedRoles.append("\n");
            }
        }

        return concatedRoles.toString();
    }
}
