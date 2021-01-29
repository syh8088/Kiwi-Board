package kiwi.board.board.service;

import kiwi.board.board.model.Board;
import kiwi.board.board.model.request.BoardsRequest;
import kiwi.board.board.model.response.BoardResponse;
import kiwi.board.board.model.response.BoardsResponse;
import kiwi.board.board.repository.BoardRepository;
import kiwi.board.common.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardsResponse getBoards(BoardsRequest boardsRequest) {

        BoardsResponse boardsResponse = new BoardsResponse();

        long countBoards = boardRepository.selectCountBoards(boardsRequest);
        List<Board> boards = boardRepository.selectBoards(boardsRequest);

        List<BoardResponse> boardResponses = BeanUtils.copyProperties(boards, BoardResponse.class);

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
}
