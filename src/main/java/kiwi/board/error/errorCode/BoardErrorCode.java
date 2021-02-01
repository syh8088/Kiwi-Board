package kiwi.board.error.errorCode;

public enum BoardErrorCode implements ErrorCode {
    NOT_EXIST_BOARD("BEC0001"),
    NOT_LIMIT_WRITE_COUNT("BEC0002");

    private final String code;

    BoardErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return "error.board." + code;
    }
}
