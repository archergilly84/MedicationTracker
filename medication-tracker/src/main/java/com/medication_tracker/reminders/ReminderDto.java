package com.medication_tracker.reminders;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderDto(
        UUID id,
        UUID medicationId,
        LocalDateTime reminderStart,
        LocalDateTime reminderEnd,
        ReminderType reminderType
) {
}
