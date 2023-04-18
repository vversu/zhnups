package ru.tinkoff.edu.java.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ChatDao {
    private JdbcTemplate jdbcTemplate;

    public ChatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Chat chat) {
        String sql = "INSERT INTO chat (user_id) VALUES (?)";
        jdbcTemplate.update(sql, chat.userId());
    }

    public void remove(int id) {
        String sql = "DELETE FROM chat WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Chat> findAll() {
        String sql = "SELECT * FROM chat";
        return jdbcTemplate.query(sql, new ChatMapper());
    }

    private static class ChatMapper implements RowMapper<Chat> {
        public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            return new Chat(id, userId);
        }
    }
}