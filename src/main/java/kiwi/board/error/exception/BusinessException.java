package kiwi.board.error.exception;

import kiwi.board.common.config.handler.CustomMessageHandler;
import kiwi.board.error.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
    private int limitWrite;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
        this.message = CustomMessageHandler.getMessage(errorCode.getCode());
    }

    public BusinessException(ErrorCode errorCode, int limitWrite) {
        super(errorCode.getCode());
        this.limitWrite = limitWrite;
        this.errorCode = errorCode;
        this.message = CustomMessageHandler.getMessage(errorCode.getCode());
    }

    @Override
    public String getMessage() {
        //if(StringUtils.contains(message, "{:limitWrite:}")) {
            //message = StringUtils.replace(message, "{:limitWrite:}", String.valueOf(limitWrite));
        //}

        return message;
    }

}
