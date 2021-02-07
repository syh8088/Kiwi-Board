package kiwi.board.common.config;

import kiwi.board.annotation.LoginUserArgumentResolver;
import kiwi.board.common.config.interceptor.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class WebConfig extends WebMvcConfigurerAdapter {

    private final LoginUserArgumentResolver loginUserArgumentResolver;
    private final RequestInterceptor requestInterceptor;

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("lang");
        resolver.setDefaultLocale(Locale.KOREA);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Locale
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        // Interceptor
//        registry.addInterceptor(requestInterceptor)
//                .addPathPatterns("/*")
//                .excludePathPatterns("/test/**/")
//                .excludePathPatterns("/login"); //로그인 쪽은 예외처리를 한다.
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
