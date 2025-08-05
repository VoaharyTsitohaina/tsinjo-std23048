package com.hei.tsinjo.modele;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Beneficiary {
  private String email;
  private String fullName;
}
