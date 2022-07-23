package com.tzuyao.communityboardaiot.rowmapper;

import com.tzuyao.communityboardaiot.constant.BoardCategory;
import com.tzuyao.communityboardaiot.model.Board;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<Board> {
    @Override
    public Board mapRow(ResultSet resultSet, int i) throws SQLException {

        Board board = new Board();
        board.setBoardId(resultSet.getInt("board_id"));

        String categoryStr = resultSet.getString("board_category");
        BoardCategory boardCategory = BoardCategory.valueOf(categoryStr);
        board.setBoardCategory(boardCategory);

        board.setBoardTitle(resultSet.getString("board_title"));
        board.setBoardDescription(resultSet.getString("board_description"));
        board.setCreatedDate(resultSet.getTimestamp("created_date"));
        board.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return board;
    }
}
