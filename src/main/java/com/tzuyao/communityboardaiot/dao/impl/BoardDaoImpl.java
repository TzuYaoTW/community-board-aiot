package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.BoardDao;
import com.tzuyao.communityboardaiot.dto.BoardRequest;
import com.tzuyao.communityboardaiot.model.Board;
import com.tzuyao.communityboardaiot.rowmapper.BoardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Override
    public Integer createBoard(BoardRequest boardRequest) {
        String sql = "INSERT INTO board(board_category, board_title, board_description, " +
                "created_date, last_modified_date) " +
                "VALUES (:boardCategory, :boardTitle, :boardDescription, " +
                ":createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("boardCategory", boardRequest.getBoardCategory().toString());
        map.put("boardTitle", boardRequest.getBoardTitle());
        map.put("boardDescription", boardRequest.getBoardDescription());
        map.put("createdDate", new Date());
        map.put("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer boardId = keyHolder.getKey().intValue();

        return boardId;
    }

    @Override
    public void updateBoard(Integer boardId, BoardRequest boardRequest) {
        String sql = "UPDATE board SET board_category = :boardCategory, board_title = :boardTitle, " +
                "board_description = :boardDescription, last_modified_date = :lastModifiedDate WHERE board_id = :boardId";
        Map<String, Object> map = new HashMap<>();
        map.put("boardCategory", boardRequest.getBoardCategory().toString());
        map.put("boardTitle", boardRequest.getBoardTitle());
        map.put("boardDescription", boardRequest.getBoardDescription());
        map.put("lastModifiedDate", new Date());
        map.put("boardId", boardId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteBoard(Integer boardId) {
        String sql = "DELETE FROM board WHERE board_id = :boardId;";

        Map<String, Object> map = new HashMap<>();
        map.put("boardId", boardId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
