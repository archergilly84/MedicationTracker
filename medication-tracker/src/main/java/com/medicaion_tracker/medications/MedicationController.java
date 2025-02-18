package com.medicaion_tracker.medications;

import com.medicaion_tracker.reminders.ReminderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1")
public class MedicationController {

    @GetMapping(path = "/medication")
    public ResponseEntity<?> getMedication(){
        return ResponseEntity.ok()
                .body("Medication Returned");
    }

    @PostMapping(path = "/medication")
    public ResponseEntity<?> createMedication(@RequestBody MedicationDto medicationDto){
        return null;
    }

    @PutMapping(path = "/medication/{medication_id}")
    public ResponseEntity<?> updateMedication(@PathVariable UUID id, @RequestBody MedicationDto medicationDto) {
        return null;
    }

    @DeleteMapping(path = "/medication/{medication_id}")
    public ResponseEntity<?> removeMedication(@PathVariable UUID id){
        return null;
    }

    @GetMapping(path = "/reminder")
    public ResponseEntity<?> getReminders(){
        return null;
    }

    @PostMapping(path = "/reminder")
    public ResponseEntity<?> setReminder(@RequestBody ReminderDto reminderDto){
        return null;
    }

    @PutMapping(path = "/reminder/{reminder_id}")
    public ResponseEntity<?> updateReminder(@PathVariable UUID id, @RequestBody ReminderDto reminderDto){
        return null;
    }

    @DeleteMapping(path = "/reminder/{reminder_id}")
    public ResponseEntity<?> removeReminder(@PathVariable UUID id){
        return null;
    }

}
