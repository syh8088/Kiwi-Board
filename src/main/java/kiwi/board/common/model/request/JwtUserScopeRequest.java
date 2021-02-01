package kiwi.board.common.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserScopeRequest {
    private String authority;
}
