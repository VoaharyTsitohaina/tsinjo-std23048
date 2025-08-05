package com.hei.tsinjo.service;

import com.hei.tsinjo.modele.Payment;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VolaService {
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${vola.api.key}")
  private String apiKey;

  private static final String BASE_URL =
      "https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws";

  public Payment createPayment(String email, String pspPaymentId) {
    String url =
        BASE_URL
            + "/payment?apiKey="
            + apiKey
            + "&payerEmail="
            + email
            + "&pspType=ORANGE_MONEY"
            + "&pspPaymentId="
            + pspPaymentId;

    Map<?, ?> response = restTemplate.postForObject(url, null, Map.class);
    return mapToPayment(response);
  }

  private Payment mapToPayment(Map<?, ?> data) {
    Map<?, ?> pspPayment = (Map<?, ?>) data.get("pspPayment");
    Map<?, ?> payer = (Map<?, ?>) data.get("payer");

    return new Payment(
        (String) data.get("id"),
        (String) pspPayment.get("pspType"),
        (String) pspPayment.get("id"),
        (Integer) pspPayment.get("amount"),
        (String) data.get("verificationStatus"),
        LocalDateTime.now());
  }
}
