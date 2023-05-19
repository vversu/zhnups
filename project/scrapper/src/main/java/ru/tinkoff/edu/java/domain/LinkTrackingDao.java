package ru.tinkoff.edu.java.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class LinkTrackingDao {
    private JdbcTemplate jdbcTemplate;

    public LinkTrackingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(LinkTracking linkTracking) {
        String sql = "INSERT INTO link_tracking (link_id, chat_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, linkTracking.linkId(), linkTracking.chatId());
    }

    public void remove(int id) {
        String sql = "DELETE FROM link_tracking WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<LinkTracking> findAll() {
        String sql = "SELECT * FROM link_tracking";
        return jdbcTemplate.query(sql, new LinkTrackingMapper());
    }

    private static class LinkTrackingMapper implements RowMapper<LinkTracking> {
        public LinkTracking mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            int linkId = rs.getInt("link_id");
            int chatId = rs.getInt("chat_id");
            return new LinkTracking(id, linkId, chatId);
            }
    }
    }
            