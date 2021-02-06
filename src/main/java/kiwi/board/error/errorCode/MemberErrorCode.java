package kiwi.board.error.errorCode;

public enum MemberErrorCode implements ErrorCode {

    NOT_VALID_PASSWORD_LENGTH("MEC0001"),
    ALREADY_JOIN_ID("MFC0002"),
    ALREADY_JOIN_EMAIL("MFC0003"),
    NOT_FOUND_MEMBER("MFC0004"),
    NO_REQUIRED_INFORMATION("MFC0005");

    private final String code;

    MemberErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return "error.member." + code;
    }
}
