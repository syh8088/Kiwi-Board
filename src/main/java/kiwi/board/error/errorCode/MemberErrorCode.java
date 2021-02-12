package kiwi.board.error.errorCode;

public enum MemberErrorCode implements ErrorCode {

    NOT_VALID_PASSWORD_LENGTH("MEC0001"),
    ALREADY_JOIN_ID("MEC0002"),
    ALREADY_JOIN_EMAIL("MEC0003"),
    NOT_FOUND_MEMBER("MEC0004"),
    NO_REQUIRED_INFORMATION("MEC0005"),
    NOT_VALID_ID_LENGTH("MEC0006"),
    NOT_VALID_EMAIL("MEC0007"),
    NOT_VALID_ID("MEC0008");


    private final String code;

    MemberErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return "error.member." + code;
    }
}
