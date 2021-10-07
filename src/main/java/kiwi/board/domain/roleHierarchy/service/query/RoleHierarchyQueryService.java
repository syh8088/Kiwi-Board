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

    public String selectHierarchies() {

        List<RoleHierarchy> rolesHierarchy = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> roleHierarchyIterator = rolesHierarchy.iterator();
        StringBuilder stringBuilder = new StringBuilder();

        while (roleHierarchyIterator.hasNext()) {

            RoleHierarchy roleHierarchy = roleHierarchyIterator.next();
            if (roleHierarchy.getParentName() != null) {
                stringBuilder.append(roleHierarchy.getParentName().getChildName());
                stringBuilder.append(" > ");
                stringBuilder.append(roleHierarchy.getChildName());
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
