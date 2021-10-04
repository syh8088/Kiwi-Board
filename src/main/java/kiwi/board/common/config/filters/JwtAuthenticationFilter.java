package kiwi.board.common.config.filters;

import kiwi.board.common.config.authentication.jwt.JwtTokenProvider;
import kiwi.board.common.config.authentication.model.transfer.PrincipalDetails;
import kiwi.board.common.config.handler.UserServiceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserServiceHandler userServiceHandler;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String jwt = null;

        Optional<String> optAccessToken = Optional.ofNullable(request.getHeader("accessToken"));

        if (optAccessToken.isPresent()) {
            jwt = optAccessToken.get();
            username = jwtTokenProvider.extractUsernameByAccessToken(jwt);
        }

        /**
         * 토큰에서 username 을 정상적으로 추출할 수 있고
         * SecurityContextHolder 내에 authentication 객체(이전에 인증된 정보)가 없는 상태인지를 검사한다.
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            PrincipalDetails principalDetails = (PrincipalDetails) userServiceHandler.loadUserByUsername(username);

            if (jwtTokenProvider.validateAccessToken(jwt, principalDetails.getUsername())) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                principalDetails,
                                null,
                                principalDetails.getAuthorities()
                        );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
