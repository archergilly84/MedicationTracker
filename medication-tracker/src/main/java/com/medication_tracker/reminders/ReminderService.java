package com.medication_tracker.reminders;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

  private final ReminderAssembler reminderAssembler;
  private final ReminderRepository reminderRepository;

    public ReminderService(
            ReminderAssembler reminderAssembler,
            ReminderRepository reminderRepository
    ) {
        this.reminderAssembler = reminderAssembler;
        this.reminderRepository = reminderRepository;
    }

    public List<ReminderDto> getAllReminders() {
    return reminderRepository.getAllReminders()
            .stream()
            .map(reminderAssembler::toDto)
            .toList();
  }

  public ReminderDto createReminder(ReminderDto reminderDto) {
    return reminderAssembler.toDto(
            reminderRepository.createReminder(
                    reminderAssembler.toDomain(reminderDto)
            )
    );
  }

  public ReminderDto updateReminder(
      UUID id,
      ReminderDto reminderDto
  ) {
    return null;
  }

  public UUID removeReminder(UUID reminderId) {
    return null;
  }
}
