package kiwi.board.domain.member.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMemberRequest {

    @ApiModelProperty(value = "회원 아이디", required = true, position = 10)
    private String id;

    @ApiModelProperty(value = "패스워드", required = true, position = 20)
    private String password;

    @ApiModelProperty(value = "회원 이름", required = true, position = 30)
    private String name;

    @ApiModelProperty(value = "회원 이메일", required = true, position = 40)
    private String email;
}
