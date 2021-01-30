package kiwi.board.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kiwi.board.board.model.request.BoardsRequest;
import kiwi.board.board.model.request.SaveBoardRequest;
import kiwi.board.board.model.response.BoardResponse;
import kiwi.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Board", description = "게시판 본문")
@RestController
@RequiredArgsConstructor
@RequestMapping("boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    @ApiOperation(value = "게시판 LIST 출력", notes = "게시판 LIST 를 출력합니다. ")
    public ResponseEntity<?> getBoards(@ModelAttribute BoardsRequest boardsRequest) {

        return ResponseEntity.ok().body(boardService.getBoards(boardsRequest));
    }

    @GetMapping("{boardNo}")
    @ApiOperation(value = "게시판 상세 조회", notes = "게시판을 조회합니다.")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable long boardNo) {

        BoardResponse boardResponse = boardService.getBoard(boardNo);

        return (boardResponse == null) ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(boardResponse);
    }

    @PostMapping
    @ApiOperation(value = "게시판 저장", notes = "게시판을 저장합니다")
    public ResponseEntity<?> savePost(@RequestBody SaveBoardRequest saveBoardRequest) {

        boardService.saveBoard(saveBoardRequest);
        return ResponseEntity.noContent().build();
    }

}
