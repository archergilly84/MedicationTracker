package com.medication_tracker.reminders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReminderServiceTest {

  public static final UUID ID = UUID.randomUUID();
  @Mock
  private ReminderRepository reminderRepository;
  @Mock
  private ReminderAssembler reminderAssembler;
  @InjectMocks
  private ReminderService underTest;

  @Test
  void getAllReminders() {

    var firstReminder = mock(Reminder.class);
    var firstReminderDto = mock(ReminderDto.class);
    var secondReminder = mock(Reminder.class);
    var secondReminderDto = mock(ReminderDto.class);
    given(reminderAssembler.toDto(firstReminder))
        .willReturn(firstReminderDto);
    given(reminderAssembler.toDto(secondReminder))
        .willReturn(secondReminderDto);
    given(reminderRepository.getAllReminders())
        .willReturn(List.of(firstReminder, secondReminder));

    var actual = underTest.getAllReminders();

    assertThat(actual)
        .hasSize(2)
        .containsExactlyInAnyOrder(
            firstReminderDto,
            secondReminderDto
        );
  }

  @ParameterizedTest
  @EmptySource
  void getAllReminders_returnEmptyList(List<Reminder> reminders) {

    given(reminderRepository.getAllReminders())
        .willReturn(reminders);

    var actual = underTest.getAllReminders();

    assertThat(actual)
        .isEmpty();
  }

  @Test
  void createReminder() {

    var reminderDto = mock(ReminderDto.class);
    var reminder = mock(Reminder.class);
    var createdReminder = mock(Reminder.class);
    var createdReminderDto = mock(ReminderDto.class);

    given(reminderAssembler.toDomain(reminderDto))
        .willReturn(reminder);
    given(reminderRepository.createReminder(reminder))
        .willReturn(createdReminder);
    given(reminderAssembler.toDto(createdReminder))
        .willReturn(createdReminderDto);

    var actual = underTest.createReminder(reminderDto);

    assertThat(actual).isEqualTo(createdReminderDto);
  }

  @ParameterizedTest
  @NullSource
  void createReminder_handlesNullReminder(ReminderDto reminderDto) {

    var actual = underTest.createReminder(reminderDto);

    assertThat(actual).isNull();
  }

  @Test
  void updateReminder() {

    var reminderDto = mock(ReminderDto.class);
    var updatedReminderDto = mock(ReminderDto.class);
    var reminder = mock(Reminder.class);
    var updatedReminder = mock(Reminder.class);

    given(reminderAssembler.toDomain(reminderDto))
        .willReturn(reminder);
    given(reminderRepository.updateReminder(ID, reminder))
        .willReturn(updatedReminder);
    given(reminderAssembler.toDto(updatedReminder))
        .willReturn(updatedReminderDto);

    var actual = underTest.updateReminder(ID, reminderDto);

    assertThat(actual).isEqualTo(updatedReminderDto);
  }

  public static Stream<Arguments> nullArguments() {
    return Stream.of(
        Arguments.of(null, mock(ReminderDto.class)),
        Arguments.of(null, null),
        Arguments.of(ID, null)
    );
  }

  @ParameterizedTest
  @MethodSource("nullArguments")
  void updateReminder(UUID id, ReminderDto reminderDto) {
    assertThat(underTest.updateReminder(id, reminderDto))
        .isNull();
  }

  @Test
  void removeReminder() {

    var removedReminder = mock(Reminder.class);
    var removedReminderDto = mock(ReminderDto.class);

    given(reminderRepository.removeReminder(ID))
        .willReturn(removedReminder);
    given(reminderAssembler.toDto(removedReminder))
        .willReturn(removedReminderDto);
    given(removedReminderDto.id())
        .willReturn(ID);

    var actual = underTest.removeReminder(ID);

    assertThat(actual)
        .isEqualTo(ID);
  }

  @ParameterizedTest
  @NullSource
  void removeReminder(UUID id) {
    assertThat(underTest.removeReminder(id))
        .isNull();
  }
}