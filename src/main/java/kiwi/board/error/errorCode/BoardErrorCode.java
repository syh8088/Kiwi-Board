package kiwi.board.error.errorCode;

public enum BoardErrorCode implements ErrorCode {
    NOT_EXIST_BOARD("BEC0001"),
    NOT_LIMIT_WRITE_COUNT("BEC0002"),
    NOT_EXIST_TITLE_AND_CONTENT("BEC0003"),
    NOT_EXIST_BOARD_NO("BEC0004"),
    NOT_PERMISSION("BEC0005");

    private final String code;

    BoardErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return "error.board." + code;
    }
}
