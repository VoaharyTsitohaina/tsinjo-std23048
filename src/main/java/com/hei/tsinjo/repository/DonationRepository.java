package com.hei.tsinjo.repository;

import com.hei.tsinjo.modele.*;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DonationRepository {
  private final JdbcTemplate jdbcTemplate;

  public DonationRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void save(Donation donation) {
    jdbcTemplate.update(
        """
    INSERT INTO donation (donor_email, donor_name, payment_id, psp_type, psp_payment_id, amount, status, creation_instant)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
""",
        donation.getDonor().getEmail(),
        donation.getDonor().getFullName(),
        donation.getPayment().getId(),
        donation.getPayment().getPspType(),
        donation.getPayment().getPspPaymentId(),
        donation.getPayment().getAmount(),
        donation.getPayment().getStatus(),
        donation.getPayment().getCreationInstant());
  }

  public List<Donation> findAllOrderByCreationInstantDesc() {
    return jdbcTemplate.query(
        """
            SELECT * FROM donation ORDER BY creation_instant DESC
        """,
        (ResultSet rs, int rowNum) ->
            new Donation(
                rs.getLong("id"),
                new Donor(rs.getString("donor_email"), rs.getString("donor_name")),
                new Payment(
                    rs.getString("payment_id"),
                    rs.getString("psp_type"),
                    rs.getString("psp_payment_id"),
                    rs.getInt("amount"),
                    rs.getString("status"),
                    rs.getTimestamp("creation_instant").toLocalDateTime())));
  }
}
