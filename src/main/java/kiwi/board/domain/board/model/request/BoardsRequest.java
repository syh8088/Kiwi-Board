package kiwi.board.domain.board.model.request;

import io.swagger.annotations.ApiModelProperty;
import kiwi.board.common.model.request.CommonRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardsRequest extends CommonRequest {

    @ApiModelProperty(value = "제목", position = 10)
    private String title;

}
