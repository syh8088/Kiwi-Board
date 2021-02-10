package kiwi.board.domain.board.model.entity;

import kiwi.board.common.model.entity.Common;
import kiwi.board.domain.member.model.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo"/*, insertable = false, updatable = false*/)
    private Member member;

    public static Board createBoard(Long boardNo, String title, String content) {
        Board board = new Board();
        if (boardNo != null) board.boardNo = boardNo;
        board.title = title;
        board.content = content;

        return board;
    }

    public static Board createBoard(String title, String content) {
        Board board = new Board();
        board.title = title;
        board.content = content;

        return board;
    }

}
