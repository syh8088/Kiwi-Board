package kiwi.board.util.validator;

import kiwi.board.domain.board.model.request.SaveBoardRequest;
import kiwi.board.domain.board.model.request.UpdateBoardRequest;
import kiwi.board.domain.member.service.query.MemberQueryService;
import kiwi.board.error.errorCode.BoardErrorCode;
import kiwi.board.error.errorCode.MemberErrorCode;
import kiwi.board.error.exception.BusinessException;
import kiwi.board.domain.member.model.request.SaveMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@RequiredArgsConstructor
public class Validator {

    private final MemberQueryService memberQueryService;

    public void saveMember(SaveMemberRequest saveMemberRequest) {

        if (StringUtils.isEmpty(saveMemberRequest.getId()) ||  StringUtils.isEmpty(saveMemberRequest.getName()) || StringUtils.isEmpty(saveMemberRequest.getPassword()) || StringUtils.isEmpty(saveMemberRequest.getEmail())) {
            throw new BusinessException(MemberErrorCode.NO_REQUIRED_INFORMATION);
        }

        if (saveMemberRequest.getPassword().length() < 10) {
            throw new BusinessException(MemberErrorCode.NOT_VALID_ID_LENGTH);
        }

        if (saveMemberRequest.getId().length() < 5) {
            throw new BusinessException(MemberErrorCode.NOT_VALID_PASSWORD_LENGTH);
        }

        if (!isValidId(saveMemberRequest.getId())) {
            throw new BusinessException(MemberErrorCode.NOT_VALID_ID);
        }

        if (!isValidEmail(saveMemberRequest.getEmail())) {
            throw new BusinessException(MemberErrorCode.NOT_VALID_EMAIL);
        }

        if (memberQueryService.isAlreadyRegisteredId(saveMemberRequest.getId())) {
            throw new BusinessException(MemberErrorCode.ALREADY_JOIN_ID);
        }

        if (memberQueryService.isAlreadyRegisteredEmail(saveMemberRequest.getEmail())) {
            throw new BusinessException(MemberErrorCode.ALREADY_JOIN_EMAIL);
        }
    }

    public void saveBoard(SaveBoardRequest saveBoardRequest) {

        if (StringUtils.isEmpty(saveBoardRequest.getTitle()) || StringUtils.isEmpty(saveBoardRequest.getContent())) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_TITLE_AND_CONTENT);
        }
    }

    public void updateBoard(UpdateBoardRequest updateBoardRequest) {

        if (StringUtils.isEmpty(updateBoardRequest.getTitle()) || StringUtils.isEmpty(updateBoardRequest.getContent())) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_TITLE_AND_CONTENT);
        }
    }

    private void isBoardTitleAndContent(String title, String content) {
        if (title.isEmpty() || content.isEmpty()) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_TITLE_AND_CONTENT);
        }
    }

    private boolean isValidEmail(String email) {

        boolean isValid = false;

        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    private boolean isValidId(String id) {

        boolean isValid = false;

        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

}
