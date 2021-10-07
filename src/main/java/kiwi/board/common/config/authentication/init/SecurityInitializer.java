package kiwi.board.common.config.authentication.init;

import kiwi.board.domain.roleHierarchy.service.query.RoleHierarchyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {

    private final RoleHierarchyQueryService roleHierarchyQueryService;
    private final RoleHierarchyImpl roleHierarchy;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String allHierarchy = roleHierarchyQueryService.selectHierarchies();
        roleHierarchy.setHierarchy(allHierarchy);
    }
}
