package kiwi.board.board.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardRequest {

    @ApiModelProperty(value = "제목", required = true, position = 10)
    private String title;

    @ApiModelProperty(value = "내용", required = true, position = 20)
    private String content;
}
