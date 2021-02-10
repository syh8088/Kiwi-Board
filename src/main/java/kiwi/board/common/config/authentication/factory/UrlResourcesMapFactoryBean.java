package kiwi.board.common.config.authentication.factory;

import kiwi.board.domain.resources.service.query.ResourceQueryService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

// DB로 부터 얻은 권한/자원 정보를 resourceMap 을 빈으로 생성하서 UrlFilterInvocationSecurityMetadataSource 에 전달
public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

    private ResourceQueryService resourceQueryService;
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;

    public void setSecurityResourceService(ResourceQueryService resourceQueryService) {
        this.resourceQueryService = resourceQueryService;
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {

        if (resourceMap == null) {
            init();
        }

        return resourceMap;
    }

    private void init() {
        resourceMap = resourceQueryService.getResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
