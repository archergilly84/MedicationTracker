package com.medication_tracker.medications.config;

import com.medication_tracker.medications.MedicationAssembler;
import com.medication_tracker.medications.MedicationDao;
import com.medication_tracker.medications.MedicationRepository;
import com.medication_tracker.medications.MedicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicationConfig {

  @Bean
  public MedicationService medicationService() {
    return new MedicationService(medicationRepository(), medicationAssembler());
  }

  @Bean
  public MedicationAssembler medicationAssembler() {
    return new MedicationAssembler();
  }

  @Bean
  public MedicationRepository medicationRepository() {
    return new MedicationRepository(medicationDao(), medicationAssembler());
  }

  @Bean
  public MedicationDao medicationDao() {
    return new MedicationDao();
  }
}
