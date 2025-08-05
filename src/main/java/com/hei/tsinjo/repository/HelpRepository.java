package com.hei.tsinjo.repository;

import com.hei.tsinjo.modele.*;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelpRepository {
  private final JdbcTemplate jdbcTemplate;

  public HelpRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Help> findAllOrderByCreationInstantDesc() {
    return jdbcTemplate.query(
        """
            SELECT * FROM help ORDER BY creation_instant DESC
        """,
        (ResultSet rs, int rowNum) ->
            new Help(
                rs.getLong("id"),
                new Beneficiary(
                    rs.getString("beneficiary_email"), rs.getString("beneficiary_name")),
                new Payment(
                    rs.getString("payment_id"),
                    rs.getString("psp_type"),
                    rs.getString("psp_payment_id"),
                    rs.getInt("amount"),
                    rs.getString("status"),
                    rs.getTimestamp("creation_instant").toLocalDateTime()),
                rs.getString("accident_description")));
  }
}
