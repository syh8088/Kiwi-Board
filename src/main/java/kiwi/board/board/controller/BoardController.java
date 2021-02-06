package kiwi.board.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kiwi.board.board.model.request.BoardsRequest;
import kiwi.board.board.model.request.SaveBoardRequest;
import kiwi.board.board.model.request.UpdateBoardRequest;
import kiwi.board.board.model.response.BoardResponse;
import kiwi.board.board.service.BoardService;
import kiwi.board.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Board", description = "게시판 본문")
@RestController
@RequiredArgsConstructor
@RequestMapping("boards")
public class BoardController {

    private final BoardService boardService;
    private final Validator validator;

    @GetMapping
    @ApiOperation(value = "게시판 LIST 출력", notes = "게시판 LIST 를 출력합니다. ")
    public ResponseEntity<?> getBoards(@ModelAttribute BoardsRequest boardsRequest) {

        return ResponseEntity.ok().body(boardService.getBoards(boardsRequest));
    }

    @GetMapping("{boardNo}")
    @ApiOperation(value = "게시판 상세 조회", notes = "게시판을 조회 합니다.")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable long boardNo) {

        BoardResponse boardResponse = boardService.getBoard(boardNo);

        return (boardResponse == null) ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(boardResponse);
    }

    @PostMapping
    @ApiOperation(value = "게시판 저장", notes = "게시판을 저장 합니다")
    public ResponseEntity<?> saveBoard(@RequestBody SaveBoardRequest saveBoardRequest) {

        validator.saveBoard(saveBoardRequest);

        boardService.saveBoard(saveBoardRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{boardNo}")
    @ApiOperation(value = "게시판 수정", notes = "게시판을 수정 합니다")
    public ResponseEntity<?> updateBoard(@PathVariable long boardNo, @RequestBody UpdateBoardRequest updateBoardRequest) {

        validator.updateBoard(updateBoardRequest);

        boardService.updateBoard(boardNo, updateBoardRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{boardNo}")
    @ApiOperation(value = "게시판 삭제", notes = "게시판을 식제 합니다")
    public ResponseEntity<?> deleteBoard(@PathVariable long boardNo) {

        boardService.deleteBoard(boardNo);
        return ResponseEntity.noContent().build();
    }
}
