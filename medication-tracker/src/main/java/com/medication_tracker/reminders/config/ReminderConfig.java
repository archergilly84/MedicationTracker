package com.medication_tracker.reminders.config;

import com.medication_tracker.reminders.ReminderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReminderConfig {

  @Bean
  public ReminderService reminderService() {
    return new ReminderService();
  }

}
