package kiwi.board.board.model;

import kiwi.board.common.model.entity.Common;
import kiwi.board.member.model.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Board extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo", insertable = false, updatable = false)
    private Member member;
}
