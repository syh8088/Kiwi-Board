package kiwi.board.util.validator;

import kiwi.board.board.service.BoardService;
import kiwi.board.error.errorCode.MemberErrorCode;
import kiwi.board.error.exception.BusinessException;
import kiwi.board.member.model.request.SaveMemberRequest;
import kiwi.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {

    private final MemberService memberService;
    private final BoardService boardService;

    public void saveMember(SaveMemberRequest saveMemberRequest) {

        if (saveMemberRequest.getPassword().length() < 10) {
            throw new BusinessException(MemberErrorCode.NOT_VALID_PASSWORD_LENGTH);
        }

        if (memberService.isAlreadyRegisteredId(saveMemberRequest.getId())) {
            throw new BusinessException(MemberErrorCode.ALREADY_JOIN_ID);
        }

        if (memberService.isAlreadyRegisteredEmail(saveMemberRequest.getEmail())) {
            throw new BusinessException(MemberErrorCode.ALREADY_JOIN_EMAIL);
        }
    }

/*    public void isRegisteredBoardId(String id) {
        Board board = boardService.getBoard(id);
        if (board == null) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_BOARD);
        }
    }*/

/*    public void setBaord(BoardList boardList) {
        Board board = boardService.getBoard(boardList.getBoardId());

        // 하루 글쓰기 제한 유효성 검사
        int limitWrite = board.getLimitWrite();
        if(limitWrite > 0) {

            int limitWriteCount = boardService.getBoardLimitWriteCount(boardList.getBoardId());
            if(limitWriteCount > limitWrite) {
                throw new BusinessException(BoardErrorCode.NOT_LIMIT_WRITE_COUNT, limitWrite);
            }
        }
    }*/

}
