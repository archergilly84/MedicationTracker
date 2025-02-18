package com.medicaion_tracker.medications;

import java.util.UUID;

public record MedicationDto(

    UUID id,
    String name,
    Integer quantity,
    String dose,
    Timings timings
){}
