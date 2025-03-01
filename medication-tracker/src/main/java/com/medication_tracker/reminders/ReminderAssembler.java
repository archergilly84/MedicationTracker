package com.medication_tracker.reminders;

public class ReminderAssembler {

    public ReminderDto toDto(Reminder reminder) {
        if (reminder == null){
            return null;
        }
        return new ReminderDto(
            reminder.getId(),
            reminder.getMedicationId(),
            reminder.getReminderStart(),
            reminder.getReminderEnd(),
            reminder.getReminderType()
        );
    }

    public Reminder toDomain(ReminderDto reminderDto) {
        if (reminderDto == null){
            return null;
        }
        return new Reminder(
            reminderDto.id(),
            reminderDto.medicationId(),
            reminderDto.reminderStart(),
            reminderDto.reminderEnd(),
            reminderDto.reminderType()
        );
    }

    public Reminder fromPo(ReminderPo reminderPo) {
        return null;
    }

    public ReminderPo toPo(Reminder reminder) {
        return null;
    }
}
