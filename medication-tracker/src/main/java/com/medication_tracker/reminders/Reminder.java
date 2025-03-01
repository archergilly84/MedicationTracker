package com.medication_tracker.reminders;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reminder {

  private final UUID id;
  private final UUID medicationId;
  private final LocalDateTime reminderStart;
  private final LocalDateTime reminderEnd;;
  private final ReminderType reminderType;

  public Reminder(
      UUID id,
      UUID medicationId,
      LocalDateTime reminderStart,
      LocalDateTime reminderEnd,
      ReminderType reminderType
  ) {
    this.id = id;
    this.medicationId = medicationId;
    this.reminderStart = reminderStart;
    this.reminderEnd = reminderEnd;
    this.reminderType = reminderType;
  }

  public UUID getId() {
    return id;
  }

  public UUID getMedicationId() {
    return medicationId;
  }

  public LocalDateTime getReminderStart() {
    return reminderStart;
  }

  public LocalDateTime getReminderEnd() {
    return reminderEnd;
  }

  public ReminderType getReminderType() {
    return reminderType;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reminder reminder = (Reminder) o;
    return Objects.equals(id, reminder.id) && Objects.equals(
        medicationId,
        reminder.medicationId
    ) && Objects.equals(reminderStart, reminder.reminderStart) && Objects.equals(
        reminderEnd,
        reminder.reminderEnd
    ) && reminderType == reminder.reminderType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, medicationId, reminderStart, reminderEnd, reminderType);
  }

  @Override
  public String toString() {
    return "Reminder{" +
        "id=" + id +
        ", medicationId=" + medicationId +
        ", reminderStart=" + reminderStart +
        ", reminderEnd=" + reminderEnd +
        ", reminderType=" + reminderType +
        '}';
  }
}
