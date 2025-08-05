package com.hei.tsinjo.modele;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Help {
  private Long id;
  private Beneficiary beneficiary;
  private Payment payment;
  private String accidentDescription;
}
