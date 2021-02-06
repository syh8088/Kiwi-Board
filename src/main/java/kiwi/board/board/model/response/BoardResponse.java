package kiwi.board.board.model.response;

import io.swagger.annotations.ApiModelProperty;
import kiwi.board.common.model.response.CommonResponse;
import kiwi.board.member.model.response.MemberResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse extends CommonResponse {

    @ApiModelProperty(value = "게시글 번호", position = 10)
    private long number;

    @ApiModelProperty(value = "게시글 번호", position = 10)
    private long boardNo;

    @ApiModelProperty(value = "제목", position = 20)
    private String title;

    @ApiModelProperty(value = "본문", position = 30)
    private String content;

    private MemberResponse memberResponse;
}
