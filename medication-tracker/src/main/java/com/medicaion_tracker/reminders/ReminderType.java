package com.medicaion_tracker.reminders;

public enum ReminderType {
    PERSCRIPTION_PICK_UP("Time to pick up your ready medication"),
    PERSCRIPTION_ORDER("Time to Order new Medication"),
    TAKE("Time to take your medication");

    private final String description;

    ReminderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
