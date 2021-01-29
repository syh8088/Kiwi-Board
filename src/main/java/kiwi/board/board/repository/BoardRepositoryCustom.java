package kiwi.board.board.repository;

import kiwi.board.board.model.Board;
import kiwi.board.board.model.request.BoardsRequest;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> selectBoards(BoardsRequest boardsRequest);
    long selectCountBoards(BoardsRequest boardsRequest);
}
