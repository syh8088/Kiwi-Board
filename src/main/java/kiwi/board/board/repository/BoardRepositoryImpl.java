package kiwi.board.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kiwi.board.board.model.Board;
import kiwi.board.board.model.QBoard;
import kiwi.board.board.model.request.BoardsRequest;
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

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public List<Board> selectBoards(BoardsRequest boardsRequest) {

        JPQLQuery<Board> postJPQLQuery = selectBoardJPQLQuery(boardsRequest);

        if (boardsRequest.getOffset() != null && boardsRequest.getLimit() != null) {
            postJPQLQuery
                    .limit(boardsRequest.getLimit())
                    .offset((boardsRequest.getOffset() - 1) * boardsRequest.getLimit());
        }

        return postJPQLQuery.fetch();
    }

    @Override
    public long selectCountBoards(BoardsRequest boardsRequest) {
        JPQLQuery<Board> query = selectBoardJPQLQuery(boardsRequest);

        return query.fetchCount();
    }

    private JPQLQuery<Board> selectBoardJPQLQuery(BoardsRequest boardsRequest) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (Strings.isNotEmpty(boardsRequest.getTitle())) {
            booleanBuilder.and(qBoard.title.like("%" + boardsRequest.getTitle() + "%"));
        }

        return from(qBoard).where(booleanBuilder);
    }

/*    @Override
    public long updateCategory(long previousCategoryNo, long destinationCategoryNo) {
        return update(qPost)
                .set(qPost.category.categoryNo, destinationCategoryNo)
                .where(qPost.category.categoryNo.eq(previousCategoryNo))
                .execute();
    }*/

}
