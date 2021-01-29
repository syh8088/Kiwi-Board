package kiwi.board.board.controller;

import kiwi.board.board.model.request.BoardsRequest;
import kiwi.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> getBoards(@ModelAttribute BoardsRequest boardsRequest) {

        return ResponseEntity.ok().body(boardService.getBoards(boardsRequest));
    }

}
