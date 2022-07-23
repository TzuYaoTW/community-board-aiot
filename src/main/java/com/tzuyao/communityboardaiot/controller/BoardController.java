package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.model.Board;
import com.tzuyao.communityboardaiot.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Operation(summary = "取得公佈欄資料", description = "透過board_id取得公佈欄資料")
    @GetMapping("/board/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable Integer boardId){
        Board board = boardService.getBoardById(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

    @Operation(summary = "取得所有公佈欄資訊", description = "取得所有公佈欄資訊")
    @GetMapping("/board")
    public ResponseEntity<List<Board>> getBoards() {
        List<Board> boardList = boardService.getBoards();
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }
}
