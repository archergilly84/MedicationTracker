package com.medication_tracker.reminders;

import java.util.List;
import java.util.UUID;
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
@RequestMapping("api/v1")
public class ReminderController {

  private final ReminderService reminderService;

  public ReminderController(ReminderService reminderService) {
    this.reminderService = reminderService;
  }

  @GetMapping(path = "/reminder")
  public ResponseEntity<List<ReminderDto>> getReminders() {
    return ResponseEntity.ok(reminderService.getAllReminders());
  }

  @PostMapping(path = "/reminder")
  public ResponseEntity<ReminderDto> createReminder(@RequestBody ReminderDto reminderDto) {
    return new ResponseEntity<>(reminderService.createReminder(reminderDto), HttpStatus.CREATED);
  }

  @PutMapping(path = "/reminder/{reminder_id}")
  public ResponseEntity<ReminderDto> updateReminder(
      @PathVariable UUID id,
      @RequestBody ReminderDto reminderDto
  ) {
    return ResponseEntity.ok(reminderService.updateReminder(id, reminderDto));
  }

  @DeleteMapping(path = "/reminder/{reminder_id}")
  public ResponseEntity<UUID> removeReminder(@PathVariable UUID id) {
    return ResponseEntity.ok(reminderService.removeReminder(id));
  }
}
