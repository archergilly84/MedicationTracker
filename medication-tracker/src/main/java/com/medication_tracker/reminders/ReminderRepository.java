package com.medication_tracker.reminders;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ReminderRepository {

  private final ReminderAssembler reminderAssembler;
  private final ReminderDao reminderDao;

  public ReminderRepository(
      ReminderAssembler reminderAssembler,
      ReminderDao reminderDao
  ) {
    this.reminderAssembler = reminderAssembler;
    this.reminderDao = reminderDao;
  }

  public List<Reminder> getAllReminders() {
    return reminderDao.getAllReminders()
        .stream()
        .map(reminderAssembler::fromPo)
        .toList();
  }

  public Reminder createReminder(Reminder reminder) {
    return reminderAssembler.fromPo(
        reminderDao.save(
            reminderAssembler.toPo(reminder)
        )
    );
  }

  public Reminder updateReminder(UUID id, Reminder reminder) {
    return reminderDao.findById(id)
        .map(reminderPo ->
            reminderAssembler.fromPo(
                reminderDao.update(
                    reminderPo,
                    reminderAssembler.toPo(reminder)
                )
            )
        ).orElse(null);
  }

  public Reminder removeReminder(UUID id) {
    return reminderDao.findById(id)
        .map(reminderPo ->
            reminderAssembler.fromPo(
                reminderDao.remove(reminderPo)
            )
        ).orElse(null);
  }
}