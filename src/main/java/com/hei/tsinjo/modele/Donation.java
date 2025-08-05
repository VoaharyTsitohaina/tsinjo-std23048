package com.hei.tsinjo.modele;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Donation {
  private Long id;
  private Donor donor;
  private Payment payment;
}
