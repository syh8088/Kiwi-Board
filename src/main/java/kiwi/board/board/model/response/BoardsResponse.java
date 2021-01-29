package kiwi.board.board.model.response;

import io.swagger.annotations.ApiModelProperty;
import kiwi.board.common.model.response.CommonsResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardsResponse extends CommonsResponse {

    @ApiModelProperty(value = "게시글")
    private List<BoardResponse> boardResponses;

}
