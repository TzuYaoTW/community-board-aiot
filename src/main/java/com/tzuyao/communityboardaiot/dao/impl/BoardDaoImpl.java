package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.BoardDao;
import com.tzuyao.communityboardaiot.model.Board;
import com.tzuyao.communityboardaiot.rowmapper.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Board getBoardById(Integer boardId) {

        String sql = "SELECT board_id, board_category, board_title, board_description, " +
                "created_date, last_modified_date FROM board " +
                "WHERE board_id = :boardId;";

        Map<String, Object> map = new HashMap<>();
        map.put("boardId", boardId);

        List<Board> boardList = namedParameterJdbcTemplate.query(sql, map, new BoardRowMapper());

        if (boardList.size() > 0) {
            return boardList.get(0);
        }else {
            return null;
        }

    }

    @Override
    public List<Board> getBoards() {

        String sql = "SELECT board_id, board_category, board_title, board_description, " +
                "created_date, last_modified_date FROM board;";

        Map<String, Object> map = new HashMap<>();

        List<Board> boardList = namedParameterJdbcTemplate.query(sql, map, new BoardRowMapper());

        return boardList;
    }
}
