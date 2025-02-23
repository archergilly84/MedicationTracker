package com.medication_tracker.reminders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

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

    @Test
    void updateReminder() {

        var reminderDto = mock(ReminderDto.class);
        var reminder = mock(Reminder.class);

        var actual = underTest.updateReminder(ID, reminderDto);
    }

    @Test
    void removeReminder() {
    }
}