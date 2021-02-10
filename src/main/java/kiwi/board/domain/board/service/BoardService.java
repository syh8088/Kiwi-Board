package kiwi.board.domain.board.service;

import kiwi.board.domain.board.model.entity.Board;
import kiwi.board.domain.board.model.request.SaveBoardRequest;
import kiwi.board.domain.board.model.request.UpdateBoardRequest;
import kiwi.board.domain.board.repository.BoardRepository;
import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.member.repository.MemberRepository;
import kiwi.board.error.errorCode.BoardErrorCode;
import kiwi.board.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void saveBoard(long memberNo, SaveBoardRequest saveBoardRequest) {

        Board board = Board.createBoard(saveBoardRequest.getTitle(), saveBoardRequest.getContent());

        Member member = memberRepository.getOne(memberNo);
        board.setMember(member);

        boardRepository.save(board);
    }

    public void updateBoard(long memberNo, long boardNo, UpdateBoardRequest updateBoardRequest) {
        Board board = boardRepository.selectByBoardNoAndUseYn(boardNo, true);

        if (board == null) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_BOARD);
        }

        if (board.getMember().getMemberNo() != memberNo) {
            throw new BusinessException(BoardErrorCode.NOT_PERMISSION);
        }

        if (!updateBoardRequest.getTitle().isEmpty()) board.setTitle(updateBoardRequest.getTitle());
        if (!updateBoardRequest.getContent().isEmpty()) board.setContent(updateBoardRequest.getContent());
    }

    public void deleteBoard(long memberNo, long boardNo) {
        Board board = boardRepository.selectByBoardNoAndUseYn(boardNo, true);

        if (board == null) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_BOARD);
        }

        if (board.getMember().getMemberNo() != memberNo) {
            throw new BusinessException(BoardErrorCode.NOT_PERMISSION);
        }

        board.setUseYn(false);
    }


}
