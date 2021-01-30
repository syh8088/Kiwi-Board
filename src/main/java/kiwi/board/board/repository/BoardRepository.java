package kiwi.board.board.repository;

import kiwi.board.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Board findByBoardNoAndUseYn(long boardNo, boolean useYn);
}
