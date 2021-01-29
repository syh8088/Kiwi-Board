package kiwi.board.common.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonResponse {
    @ApiModelProperty(value = "사용 여부")
    private boolean useYn;

    @ApiModelProperty(value = "등록일")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정일")
    private LocalDateTime updatedAt;
}
