package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.dto.BoardRequest;
import com.tzuyao.communityboardaiot.model.Board;

import java.util.List;

public interface BoardDao {

    Board getBoardById(Integer boardId);

    List<Board> getBoards();

    Integer createBoard(BoardRequest boardRequest);

    void updateBoard(Integer boardId, BoardRequest boardRequest);

    void deleteBoard(Integer boardId);
}
