package com.tzuyao.communityboardaiot.service;

import com.tzuyao.communityboardaiot.model.Board;

import java.util.List;

public interface BoardService {

    Board getBoardById(Integer boardId);

    List<Board> getBoards();
}
