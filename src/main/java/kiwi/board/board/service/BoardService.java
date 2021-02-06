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

        List<BoardResponse> boardResponses = boards.stream()
                .map(board -> {
                    BoardResponse boardResponse = new BoardResponse();
                    boardResponse.setBoardNo(board.getBoardNo());
                    boardResponse.setTitle(board.getTitle());
                    boardResponse.setContent(board.getContent());

                    Member member = board.getMember();

                    MemberResponse memberResponse = new MemberResponse();
                    memberResponse.setMemberNo(member.getMemberNo());
                    memberResponse.setId(member.getId());
                    memberResponse.setName(member.getName());
                    memberResponse.setEmail(member.getEmail());

                    boardResponse.setMemberResponse(memberResponse);

                    return boardResponse;
                }).collect(Collectors.toList());

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

    public BoardResponse getBoard(long boardNo) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        Board one = boardRepository.getOne(1L);
        Member member = one.getMember();


        Board board = boardRepository.findByBoardNoAndUseYn(boardNo, true);

        if (board == null) return null;

        return BeanUtils.copyProperties(board, BoardResponse.class);
    }

    @Transactional
    public void saveBoard(SaveBoardRequest saveBoardRequest) {

        Board board = Board.createBoard(saveBoardRequest.getTitle(), saveBoardRequest.getContent());

        Member one = memberRepository.getOne(1L);
        // 임시 회원 적용
        board.setMember(one);

        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(long boardNo, UpdateBoardRequest updateBoardRequest) {
        Board board = boardRepository.findById(boardNo).orElse(null);

        if (board == null) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_BOARD);
        }

        if (!updateBoardRequest.getTitle().isEmpty()) board.setTitle(updateBoardRequest.getTitle());
        if (!updateBoardRequest.getContent().isEmpty()) board.setContent(updateBoardRequest.getContent());
    }

    @Transactional
    public void deleteBoard(long boardNo) {
        Board board = boardRepository.findById(boardNo).orElse(null);

        if (board == null) {
            throw new BusinessException(BoardErrorCode.NOT_EXIST_BOARD);
        }

        board.setUseYn(false);
    }

    public boolean isAlreadyRegisteredId(Long boardNo) {
        return boardRepository.findById(boardNo).isPresent();
    }


}
