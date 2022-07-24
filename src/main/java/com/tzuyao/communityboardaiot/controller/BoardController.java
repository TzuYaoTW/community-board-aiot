package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.BoardRequest;
import com.tzuyao.communityboardaiot.model.Board;
import com.tzuyao.communityboardaiot.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "公佈欄 CRUD", description = "管理員可以新增查詢修改刪除關於公佈欄的資料")
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

    @Operation(summary = "新增公佈欄訊息", description = "新增公佈欄訊息")
    @PostMapping("/board")
    public ResponseEntity<Board> createBoard(@RequestBody @Valid BoardRequest boardRequest) {
        Integer boardId = boardService.createBoard(boardRequest);
        Board board = boardService.getBoardById(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Board.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 公佈欄訊息 ID，無法執行修改功能", content = {
                    @Content()
            })
    })
    @Operation(summary = "修改公佈欄訊息", description = "透過 boardId 查詢訊息後修改訊息")
    @PutMapping("/board/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Integer boardId,
                                             @RequestBody BoardRequest boardRequest) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        boardService.updateBoard(boardId, boardRequest);

        Board updateBoard = boardService.getBoardById(boardId);

        return ResponseEntity.status(HttpStatus.OK).body(updateBoard);
    }

    @Operation(summary = "刪除公佈欄訊息", description = "透過 boardId 刪除已存在訊息")
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Integer boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
