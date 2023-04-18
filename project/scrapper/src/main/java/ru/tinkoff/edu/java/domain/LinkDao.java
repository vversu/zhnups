package ru.tinkoff.edu.java.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class LinkDao {
    private JdbcTemplate jdbcTemplate;

    public LinkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Link link) {
        String sql = "INSERT INTO link (user_id, url) VALUES (?, ?)";
        jdbcTemplate.update(sql, link.userId(), link.url());
    }

    public void remove(int id) {
        String sql = "DELETE FROM link WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Link> findAll() {
        String sql = "SELECT * FROM link";
        return jdbcTemplate.query(sql, new LinkMapper());
    }

    private static class LinkMapper implements RowMapper<Link> {
        public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            String url = rs.getString("url");
            return new Link(id, userId, url);
        }
    }
}