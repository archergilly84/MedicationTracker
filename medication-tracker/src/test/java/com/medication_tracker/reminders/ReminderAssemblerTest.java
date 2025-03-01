package com.medication_tracker.reminders;

import static com.medication_tracker.reminders.ReminderType.TAKE;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public class ReminderAssemblerTest {

  private final ReminderAssembler underTest = new ReminderAssembler();

  @Test
  void toDto() {

    var reminder = new Reminder(
        UUID.randomUUID(),
        UUID.randomUUID(),
        LocalDateTime.now(),
        LocalDateTime.now().plusMonths(1),
        TAKE
    );

    var actual = underTest.toDto(reminder);

    assertThat(actual)
        .isEqualTo(
            new ReminderDto(
                reminder.getId(),
                reminder.getMedicationId(),
                reminder.getReminderStart(),
                reminder.getReminderEnd(),
                reminder.getReminderType()
            )
        );
  }

  @ParameterizedTest
  @NullSource
  void toDtoHandlesNull(Reminder reminder) {
    assertThat(underTest.toDto(reminder))
        .isNull();
  }

  @Test
  void toDomain() {

    var reminderDto = new ReminderDto(
        UUID.randomUUID(),
        UUID.randomUUID(),
        LocalDateTime.now(),
        LocalDateTime.now().plusMonths(1),
        TAKE
    );

    var actual = underTest.toDomain(reminderDto);

    assertThat(actual)
        .isEqualTo(
            new Reminder(
                reminderDto.id(),
                reminderDto.medicationId(),
                reminderDto.reminderStart(),
                reminderDto.reminderEnd(),
                reminderDto.reminderType()
            )
        );
  }

  @ParameterizedTest
  @NullSource
  void toDomainHandlesNull(ReminderDto reminderDto) {
    assertThat(underTest.toDomain(reminderDto))
        .isNull();
  }
}