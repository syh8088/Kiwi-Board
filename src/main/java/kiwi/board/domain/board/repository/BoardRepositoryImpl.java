package kiwi.board.domain.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kiwi.board.domain.board.model.entity.Board;
import kiwi.board.board.model.entity.QBoard;
import kiwi.board.domain.board.model.request.BoardsRequest;
import kiwi.board.member.model.entity.QMember;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */

    QBoard qBoard = QBoard.board;
    QMember qMember = QMember.member;

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public List<Board> selectBoards(BoardsRequest boardsRequest) {

        JPQLQuery<Board> postJPQLQuery = selectBoardsJPQLQuery(boardsRequest);

        if (boardsRequest.getOffset() != null && boardsRequest.getLimit() != null) {
            postJPQLQuery
                    .limit(boardsRequest.getLimit())
                    .offset((boardsRequest.getOffset() - 1) * boardsRequest.getLimit());
        }

        postJPQLQuery.orderBy(qBoard.boardNo.desc());

        return postJPQLQuery.fetch();
    }

    @Override
    public long selectCountBoards(BoardsRequest boardsRequest) {
        JPQLQuery<Board> query = selectBoardsJPQLQuery(boardsRequest);

        return query.fetchCount();
    }

    private JPQLQuery<Board> selectBoardsJPQLQuery(BoardsRequest boardsRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (Strings.isNotEmpty(boardsRequest.getTitle())) {
            booleanBuilder.and(qBoard.title.like("%" + boardsRequest.getTitle() + "%"));
        }

        booleanBuilder.and(qBoard.useYn.eq(true));

        return from(qBoard)
                .leftJoin(qBoard.member, qMember).fetchJoin()
                .where(booleanBuilder);
    }

/*    @Override
    public long updateCategory(long previousCategoryNo, long destinationCategoryNo) {
        return update(qPost)
                .set(qPost.category.categoryNo, destinationCategoryNo)
                .where(qPost.category.categoryNo.eq(previousCategoryNo))
                .execute();
    }*/

}
