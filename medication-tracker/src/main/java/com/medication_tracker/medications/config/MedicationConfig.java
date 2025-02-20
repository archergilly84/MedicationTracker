package com.medication_tracker.medications.config;

import com.medication_tracker.medications.MedicationAssembler;
import com.medication_tracker.medications.MedicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicationConfig {

  @Bean
  public MedicationService medicationService() {
    return new MedicationService();
  }

  @Bean
  public MedicationAssembler medicationAssembler() {
    return new MedicationAssembler();
  }
}
