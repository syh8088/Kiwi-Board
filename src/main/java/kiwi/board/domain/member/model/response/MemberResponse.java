package kiwi.board.domain.member.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    private Long memberNo;
    private String id;
    private String name;
    private String email;
}
