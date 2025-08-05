package com.hei.tsinjo.endpoint.rest.controller;

import com.hei.tsinjo.modele.*;
import com.hei.tsinjo.repository.DonationRepository;
import com.hei.tsinjo.repository.HelpRepository;
import com.hei.tsinjo.service.VolaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TsinjoController {
  private final DonationRepository donationRepo;
  private final HelpRepository helpRepo;
  private final VolaService volaService;

  public TsinjoController(
      DonationRepository donationRepo, HelpRepository helpRepo, VolaService volaService) {
    this.donationRepo = donationRepo;
    this.helpRepo = helpRepo;
    this.volaService = volaService;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("donations", donationRepo.findAllOrderByCreationInstantDesc());
    model.addAttribute("helps", helpRepo.findAllOrderByCreationInstantDesc());
    return "index";
  }

  @PostMapping("/donate")
  public String donate(
      @RequestParam String email, @RequestParam String name, @RequestParam String pspPaymentId) {
    Payment payment = volaService.createPayment(email, pspPaymentId);
    donationRepo.save(new Donation(null, new Donor(email, name), payment));
    return "redirect:/";
  }
}
