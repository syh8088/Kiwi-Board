package kiwi.board.common.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonsResponse {
    @ApiModelProperty(value = "페이징 최대 건수", position = 10)
    private long totalPages;

    @ApiModelProperty(value = "데이터 최대 건수", position = 20)
    private long totalElements;

    @ApiModelProperty(value = "페이징 숫자", position = 30)
    private long number;

    @ApiModelProperty(value = "조회결과 최대 건수", position = 40)
    private long size;
}
