package com.medication_tracker.medications;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class MedicationRepository {

  public Medication saveMedication(Medication medication) {
    return null;
  }

  public List<Medication> getAllMedications() {
    return null;
  }

  public Medication updateMedication(
      UUID id,
      Medication medication
  ) {
    return null;
  }

  public Medication removeMedication(UUID id) {
    return null;
  }
}
