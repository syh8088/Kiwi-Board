package kiwi.board.common.config.authentication;

/*
import kiwi.board.common.config.authentication.factory.UrlResourcesMapFactoryBean;
import kiwi.board.common.config.authentication.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import kiwi.board.common.config.filters.PermitAllFilter;
import kiwi.board.domain.resources.service.query.ResourceQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;
*/

public class ResourceServerConfig {

}
/*

@Slf4j
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Value("${app.oauth.jwt.public-key}")
    private String jwtPublicKey;

    private String[] permitAllResources = {"/", "/login", "/user/login/**"};

    private static final String ROOT_PATTERN = "/**";

    @Autowired
    private ResourceQueryService resourceQueryService;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //converter.setSigningKey("secret");
        converter.setVerifierKey(jwtPublicKey);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http
                .authorizeRequests()
                //.antMatchers("/categories").hasRole("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/profile")
                .permitAll();


        http.addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId);
    }

//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
//
//        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
//        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
//        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
//       // filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());
//
//        return filterSecurityInterceptor;
//    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {

        PermitAllFilter permitAllFilter = new PermitAllFilter(permitAllResources);
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        permitAllFilter.setAccessDecisionManager(affirmativeBased());
        //permitAllFilter.setAuthenticationManager(authenticationManagerBean());

        return permitAllFilter;
    }

    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<? extends Object>> getAccessDecisionVoters() {

        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
//        accessDecisionVoters.add(new IpAddressVoter(securityResourceService));

        accessDecisionVoters.add(roleVoter());  // Role Hierarchy 이용하기
        //accessDecisionVoters.add(new RoleVoter()); // Role Hierarchy 이용 안하기

        return accessDecisionVoters;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleVoter() {
        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
        return roleHierarchyVoter;
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        return roleHierarchy;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
        // return new UrlFilterInvocationSecurityMetadataSource();
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), resourceQueryService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {

        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(resourceQueryService);

        return urlResourcesMapFactoryBean;
    }

//    @Override
//    public void configure(final HttpSecurity http) throws Exception {
//    		http.
//    		anonymous().disable()
//    		.authorizeRequests()
//    		.antMatchers("/oauth/token/**").permitAll();
//    }
//
//    @Bean
//    public WebResponseExceptionTranslator exceptionTranslator() {
//        return new CustomRestErrorWebResponseExceptionTranslator();
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.
//                anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/oauth/token/**").authenticated()
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//    }

}
*/
