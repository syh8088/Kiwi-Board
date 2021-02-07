package kiwi.board.board.service;

import kiwi.board.board.model.entity.Board;
import kiwi.board.board.model.request.BoardsRequest;
import kiwi.board.board.model.request.SaveBoardRequest;
import kiwi.board.board.model.request.UpdateBoardRequest;
import kiwi.board.board.model.response.BoardResponse;
import kiwi.board.board.model.response.BoardsResponse;
import kiwi.board.board.repository.BoardRepository;
import kiwi.board.common.utils.BeanUtils;
import kiwi.board.error.errorCode.BoardErrorCode;
import kiwi.board.error.exception.BusinessException;
import kiwi.board.member.model.entity.Member;
import kiwi.board.member.model.response.MemberResponse;
import kiwi.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardsResponse getBoards(BoardsRequest boardsRequest) {

        BoardsResponse boardsResponse = new BoardsResponse();

        long countBoards = boardRepository.selectCountBoards(boardsRequest);
        List<Board> boards = boardRepository.selectBoards(boardsRequest);

        AtomicInteger index = new AtomicInteger();
        List<BoardResponse> boardResponses = boards.stream()
                .map(board -> copyBoardToBoardResponse(board, index.getAndIncrement(), boardsRequest.getLimit(), boardsRequest.getOffset(), countBoards))
                .collect(Collectors.toList());

        //List<BoardResponse> boardResponses = BeanUtils.copyProperties(boards, BoardResponse.class);

        int totalPages = (int) (countBoards / boardsRequest.getLimit());
        if (countBoards % boardsRequest.getLimit() > 0)
            totalPages = totalPages + 1;

        boardsResponse.setBoardResponses(boardResponses);
        boardsResponse.setSize(boardResponses.size());
        boardsResponse.setTotalPages(totalPages);
        boardsResponse.setNumber(boardsRequest.getOffset());
        boardsResponse.setTotalElements(countBoards);

        return boardsResponse;
    }

    private BoardResponse copyBoardToBoardResponse(Board board, int i , Long limit, Long offset, Long countBoards) {

        BoardResponse boardResponse = new BoardResponse();

        if (offset != null && countBoards != null) {
            boardResponse.setNumber(countBoards - (limit * (offset - 1)) - i);
        }

        boardResponse.setBoardNo(board.getBoardNo());
        boardResponse.setTitle(board.getTitle());
        boardResponse.setContent(board.getContent());
        boardResponse.setCreatedAt(board.getCreatedAt());
        boardResponse.setUpdatedAt(board.getUpdatedAt());
        boardResponse.setUseYn(board.getUseYn());

        Member member = board.getMember();

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberNo(member.getMemberNo());
        memberResponse.setId(member.getId());
        memberResponse.setName(member.getName());
        memberResponse.setEmail(member.getEmail());

        boardResponse.setMemberResponse(memberResponse);

        return boardResponse;
    }

    public BoardResponse getBoard(long boardNo) {

        Board board = boardRepository.selectByBoardNoAndUseYn(boardNo, true);
        if (board == null) return null;

        return copyBoardToBoardResponse(board, 0, null, null, null);
    }

    @Transactional
    public void saveBoard(long memberNo, SaveBoardRequest saveBoardRequest) {

        Board board = Board.createBoard(saveBoardRequest.getTitle(), saveBoardRequest.getContent());

        Member member = memberRepository.getOne(memberNo);
        board.setMember(member);

        boardRepository.save(board);
    }

    @Transactional
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

    @Transactional
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

    public boolean isAlreadyRegisteredId(Long boardNo) {
        return boardRepository.findById(boardNo).isPresent();
    }


}
