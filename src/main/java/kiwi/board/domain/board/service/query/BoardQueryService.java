package kiwi.board.domain.board.service.query;

import kiwi.board.domain.board.model.entity.Board;
import kiwi.board.domain.board.model.request.BoardsRequest;
import kiwi.board.domain.board.model.response.BoardResponse;
import kiwi.board.domain.board.model.response.BoardsResponse;
import kiwi.board.domain.board.repository.BoardRepository;
import kiwi.board.domain.member.model.entity.Member;
import kiwi.board.domain.member.model.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardQueryService {

    private final BoardRepository boardRepository;

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

    public boolean isAlreadyRegisteredId(Long boardNo) {
        return boardRepository.findById(boardNo).isPresent();
    }
}


