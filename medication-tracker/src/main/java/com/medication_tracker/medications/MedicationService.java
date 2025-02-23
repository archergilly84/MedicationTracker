package com.medication_tracker.medications;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {

  private final MedicationRepository medicationRepository;
  private final MedicationAssembler medicationAssembler;

  public MedicationService(
      MedicationRepository medicationRepository,
      MedicationAssembler medicationAssembler
  ) {
    this.medicationRepository = medicationRepository;
    this.medicationAssembler = medicationAssembler;
  }

  public MedicationDto createMedication(MedicationDto medicationDto) {
    return medicationAssembler.toDto(
        medicationRepository.saveMedication(
            medicationAssembler.toDomain(medicationDto)
        )
    );
  }

  public List<MedicationDto> getAllMedications() {
    return medicationRepository.getAllMedications()
        .stream()
        .map(medicationAssembler::toDto)
        .toList();
  }

  public MedicationDto updateMedication(
      UUID id,
      MedicationDto medicationDto
  ) {
    return medicationAssembler.toDto(
        medicationRepository.updateMedication(
            id,
            medicationAssembler.toDomain(medicationDto)
        )
    );
  }

  public UUID removeMedication(UUID id) {
    if (id == null) {
      return null;
    }
    var medicationDto = medicationAssembler.toDto(
        medicationRepository.removeMedication(id)
    );
    return medicationDto.id();
  }
}
