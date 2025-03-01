package com.medication_tracker.reminders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReminderRepositoryTest {

  public static final UUID ID = UUID.randomUUID();
  @Mock
  private ReminderAssembler reminderAssembler;
  @Mock
  private ReminderDao reminderDao;
  @InjectMocks
  private ReminderRepository underTest;

  @Test
  void getAllReminders() {

    var firstReminder = mock(Reminder.class);
    var secondReminder = mock(Reminder.class);
    var firstReminderPo = mock(ReminderPo.class);
    var secondReminderPo = mock(ReminderPo.class);

    given(reminderAssembler.fromPo(firstReminderPo))
        .willReturn(firstReminder);
    given(reminderAssembler.fromPo(secondReminderPo))
        .willReturn(secondReminder);
    given(reminderDao.getAllReminders())
        .willReturn(List.of(firstReminderPo, secondReminderPo));

    var actual = underTest.getAllReminders();

    assertThat(actual)
        .hasSize(2)
        .containsExactlyInAnyOrder(firstReminder, secondReminder);
  }

  @Test
  void createReminder() {

    var reminder = mock(Reminder.class);
    var savedReminder = mock(Reminder.class);
    var reminderPo = mock(ReminderPo.class);
    var savedReminderPo = mock(ReminderPo.class);

    given(reminderAssembler.toPo(reminder))
        .willReturn(reminderPo);
    given(reminderDao.save(reminderPo))
        .willReturn(savedReminderPo);
    given(reminderAssembler.fromPo(savedReminderPo))
        .willReturn(savedReminder);

    var actual = underTest.createReminder(reminder);

    assertThat(actual)
        .isEqualTo(savedReminder);
  }

  @Test
  void updateReminder() {
    var reminder = mock(Reminder.class);
    var updatedReminder = mock(Reminder.class);
    var reminderPo = mock(ReminderPo.class);
    var toUpdatePo = mock(ReminderPo.class);
    var updatedReminderPo = mock(ReminderPo.class);

    given(reminderAssembler.toPo(reminder))
        .willReturn(toUpdatePo);
    given(reminderDao.findById(ID))
        .willReturn(Optional.of(reminderPo));
    given(reminderDao.update(reminderPo, toUpdatePo))
        .willReturn(updatedReminderPo);
    given(reminderAssembler.fromPo(updatedReminderPo))
        .willReturn(updatedReminder);

    var actual = underTest.updateReminder(
        ID,
        reminder
    );

    assertThat(actual)
        .isEqualTo(updatedReminder);

  }

  @Test
  void updateReminderHandlesOptionalEmpty() {
    var reminder = mock(Reminder.class);

    given(reminderDao.findById(ID))
        .willReturn(Optional.empty());

    var actual = underTest.updateReminder(
        ID,
        reminder
    );

    assertThat(actual)
        .isNull();

  }

  @Test
  void removeReminder() {
    var reminderPo = mock(ReminderPo.class);
    var removedReminderPo = mock(ReminderPo.class);
    var removedReminder = mock(Reminder.class);

    given(reminderDao.findById(ID))
        .willReturn(Optional.of(reminderPo));
    given(reminderDao.remove(reminderPo))
        .willReturn(removedReminderPo);
    given(reminderAssembler.fromPo(removedReminderPo))
        .willReturn(removedReminder);

    var actual = underTest.removeReminder(ID);

    assertThat(actual)
        .isEqualTo(removedReminder);
  }

  @Test
  void removeReminderHandlesOptionalEmpty() {
    given(reminderDao.findById(ID))
        .willReturn(Optional.empty());

    var actual = underTest.removeReminder(ID);

    assertThat(actual)
        .isNull();
  }
}