package kiwi.board.annotation;

import kiwi.board.common.config.filters.JwtProvider;
import kiwi.board.common.config.filters.UserDetailsImpl;
import kiwi.board.common.model.request.JwtUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 	@EnableResourceServer 사용 안하기........
     */
    //private final JwtTokenProvider jwtTokenProvider;
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        boolean isUserClass = JwtUserRequest.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        /**
         * 	@EnableResourceServer 사용 안하기........
         */
        /*OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        JwtUserRequest jwtUserRequest = jwtTokenProvider.getJwtTokenByClientCredentialForUser(oAuth2Authentication);
        return jwtUserRequest;
        */

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        JwtUserRequest jwtUserRequest = JwtUserRequest.builder()
                .member_seq(userDetails.getId())
                .member_id(userDetails.getUsername())
                .member_name(userDetails.getUsername())
                .build();

        return jwtUserRequest;

    }
}