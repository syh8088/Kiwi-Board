package kiwi.board.domain.resources.service.query;

import kiwi.board.domain.resources.model.entity.Resource;
import kiwi.board.domain.resources.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResourceQueryService {

    private final ResourceRepository resourceRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();

        List<Resource> resourcesList = resourceRepository.findAllResources();

        resourcesList.forEach(resource -> {

            List<ConfigAttribute> configAttributeList = new ArrayList<>();

            resource.getRoleSet().forEach(role -> {
                configAttributeList.add(new SecurityConfig(role.getName()));
            });

            result.put(new AntPathRequestMatcher(resource.getName()), configAttributeList);
        });

        return result;
    }
}
