package com.hei.tsinjo.modele;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
  private String id;
  private String pspType; // ORANGE_MONEY
  private String pspPaymentId;
  private int amount;
  private String status; // VERIFYING, SUCCEEDED, FAILED
  private LocalDateTime creationInstant;
}
