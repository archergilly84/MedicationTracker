package com.medication_tracker.medications;

import java.util.UUID;

public class MedicationPo {

  private final UUID id;
  private final String name;
  private final int quantity;
  private final String dose;
  private final Timings timings;

  public MedicationPo(
      UUID id,
      String name,
      int quantity,
      String dose,
      Timings timings
  ) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.dose = dose;
    this.timings = timings;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getDose() {
    return dose;
  }

  public Timings getTimings() {
    return timings;
  }
}
