package kiwi.board.board.repository;

import kiwi.board.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Board findByBoardNoAndUseYn(long boardNo, boolean useYn);

    @Query("select b from Board b join fetch b.member where b.boardNo = :boardNo and b.useYn = :useYn")
    Board selectByBoardNoAndUseYn(@Param("boardNo") long boardNo, @Param("useYn") boolean useYn);
}
