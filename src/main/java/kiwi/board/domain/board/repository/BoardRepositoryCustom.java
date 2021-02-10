package kiwi.board.domain.board.repository;

import kiwi.board.domain.board.model.entity.Board;
import kiwi.board.domain.board.model.request.BoardsRequest;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> selectBoards(BoardsRequest boardsRequest);
    long selectCountBoards(BoardsRequest boardsRequest);
}
