package kiwi.board.member.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMemberRequest {
    private String id;
    private String password;
    private String name;
    private String email;
}
