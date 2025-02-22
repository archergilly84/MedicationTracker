package com.medication_tracker.reminders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ReminderControllerTest {

  @InjectMocks
  private ReminderController underTest;
  @Mock
  private ReminderService reminderService;

  @Test
  void getReminders() {

    var reminderDto = mock(ReminderDto.class);

    given(reminderService.getAllReminders())
        .willReturn(List.of(reminderDto));

    var actual = underTest.getReminders();

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody())
        .hasSize(1)
        .containsExactlyInAnyOrder(reminderDto);
  }

  @Test
  void createReminder() {
    var reminderDto = mock(ReminderDto.class);
    var createdReminderDto = mock(ReminderDto.class);

    given(reminderService.createReminder(reminderDto))
        .willReturn(createdReminderDto);

    var actual = underTest.createReminder(reminderDto);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(actual.getBody()).isEqualTo(createdReminderDto);

  }

  @Test
  void updateReminder() {
    var reminderDto = mock(ReminderDto.class);
    var updatedReminderDto = mock(ReminderDto.class);
    var reminderId = UUID.randomUUID();

    given(reminderService.updateReminder(reminderId, reminderDto))
        .willReturn(updatedReminderDto);

    var actual = underTest.updateReminder(reminderId, reminderDto);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody()).isEqualTo(updatedReminderDto);
  }

  @Test
  void removeReminder() {
    var reminderDto = mock(ReminderDto.class);
    var reminderId = UUID.randomUUID();

    given(reminderService.removeReminder(reminderId))
        .willReturn(reminderId);

    var actual = underTest.removeReminder(reminderId);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody()).isEqualTo(reminderId);
  }

}