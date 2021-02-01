package kiwi.board.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public String handleBaseException(BusinessException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException() {
        return "error";
    }

}
