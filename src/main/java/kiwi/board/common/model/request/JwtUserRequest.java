package kiwi.board.common.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtUserRequest {
    private String member_id;
    private String user_name;
    private String member_name;
    private List<String> scope;
    private List<String> authorities;
    private List<JwtUserScopeRequest> roles;
    private Long member_seq;
    private String client_id;
    private Long exp;
    private String jti;

    @Builder
    public JwtUserRequest(String member_id, String user_name, String member_name, List<String> scope, List<String> authorities, List<JwtUserScopeRequest> roles, Long member_seq, String client_id, Long exp, String jti) {
        this.member_id = member_id;
        this.user_name = user_name;
        this.member_name = member_name;
        this.scope = scope;
        this.authorities = authorities;
        this.roles = roles;
        this.member_seq = member_seq;
        this.client_id = client_id;
        this.exp = exp;
        this.jti = jti;
    }
}
