package com.medication_tracker.medications;

import com.medication_tracker.reminders.ReminderDto;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
public class MedicationController {

  private final MedicationService medicationService;
  private final MedicationAssembler medicationAssembler;

  @Autowired
  public MedicationController(
      MedicationService medicationService,
      MedicationAssembler medicationAssembler
  ) {
    this.medicationService = medicationService;
    this.medicationAssembler = medicationAssembler;
  }

  @GetMapping(path = "/medication")
  public ResponseEntity<List<MedicationDto>> getMedications() {
    var medicationDtos = medicationService.getAllMedications()
        .stream()
        .map(medicationAssembler::toDto)
        .toList();
    return ResponseEntity.ok()
        .body(medicationDtos);
  }

  @PostMapping(path = "/medication")
  public ResponseEntity<MedicationDto> createMedication(@RequestBody MedicationDto medicationDto) {
    return new ResponseEntity<>(
        medicationService.createMedication(medicationDto),
        HttpStatus.CREATED
    );
  }

  @PutMapping(path = "/medication/{medication_id}")
  public ResponseEntity<MedicationDto> updateMedication(
      @PathVariable UUID id,
      @RequestBody MedicationDto medicationDto
  ) {
    return ResponseEntity.ok()
            .body(medicationService.updateMedication(id, medicationDto));
  }

  @DeleteMapping(path = "/medication/{medication_id}")
  public ResponseEntity<UUID> removeMedication(@PathVariable UUID id) {
    return ResponseEntity.ok()
            .body(medicationService.removeMedication(id));
  }

  @GetMapping(path = "/reminder")
  public ResponseEntity<?> getReminders() {
    return null;
  }

  @PostMapping(path = "/reminder")
  public ResponseEntity<?> setReminder(@RequestBody ReminderDto reminderDto) {
    return null;
  }

  @PutMapping(path = "/reminder/{reminder_id}")
  public ResponseEntity<?> updateReminder(
      @PathVariable UUID id,
      @RequestBody ReminderDto reminderDto
  ) {
    return null;
  }

  @DeleteMapping(path = "/reminder/{reminder_id}")
  public ResponseEntity<?> removeReminder(@PathVariable UUID id) {
    return null;
  }

}
