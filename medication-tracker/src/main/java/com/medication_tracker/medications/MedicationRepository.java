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
    return medicationDao.getAllMedications()
        .stream()
        .map(medicationAssembler::fromPo)
        .toList();
  }

  public Medication updateMedication(
      UUID id,
      Medication medication
  ) {

    var medicationPo = medicationDao.findById(id);
    if (medicationPo.isPresent()) {
      var updatedMedicationPo = medicationDao.update(
          medicationPo.get(),
          medicationAssembler.toPo(medication)
      );
      return medicationAssembler.fromPo(updatedMedicationPo);
    }
    return null;
  }

  public Medication removeMedication(UUID id) {
    return medicationDao.remove(id)
        .map(medicationAssembler::fromPo)
        .orElse(null);
  }
}
