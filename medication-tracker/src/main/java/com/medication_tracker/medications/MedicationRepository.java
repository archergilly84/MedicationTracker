package com.medication_tracker.medications;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class MedicationRepository {

  private final MedicationDao medicationDao;
  private final MedicationAssembler medicationAssembler;

  public MedicationRepository(
      MedicationDao medicationDao,
      MedicationAssembler medicationAssembler
  ) {
    this.medicationDao = medicationDao;
    this.medicationAssembler = medicationAssembler;
  }

  public Medication saveMedication(Medication medication) {
    return medicationAssembler.fromPo(
        medicationDao.save(
            medicationAssembler.toPo(medication)
        )
    );
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
