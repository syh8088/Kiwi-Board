package kiwi.board.board.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveBoardRequest {
    @ApiModelProperty(value = "게시판 번호", position = 5)
    private Long boardNo;

    @ApiModelProperty(value = "제목", required = true, position = 10)
    private String title;

    @ApiModelProperty(value = "내용", required = true, position = 15)
    private String content;
}
