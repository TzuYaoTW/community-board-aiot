package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.BoardDao;
import com.tzuyao.communityboardaiot.dto.BoardRequest;
import com.tzuyao.communityboardaiot.model.Board;
import com.tzuyao.communityboardaiot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;
    @Override
    public Board getBoardById(Integer boardId) {
        return boardDao.getBoardById(boardId);
    }

    @Override
    public List<Board> getBoards() {
        return boardDao.getBoards();
    }

    @Override
    public Integer createBoard(BoardRequest boardRequest) {
        return boardDao.createBoard(boardRequest);
    }

    @Override
    public void updateBoard(Integer boardId, BoardRequest boardRequest) {
        boardDao.updateBoard(boardId, boardRequest);
    }

    @Override
    public void deleteBoard(Integer boardId) {
        boardDao.deleteBoard(boardId);
    }


}
