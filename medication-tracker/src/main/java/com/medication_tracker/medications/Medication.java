package com.medication_tracker.medications;

import java.util.Objects;
import java.util.UUID;

public class Medication {

  private final UUID id;
  private final String name;
  private final int quantity;
  private final String dose;
  private final Timings timings;

  public Medication(
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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Medication that = (Medication) o;
    return quantity == that.quantity && Objects.equals(
        id,
        that.id
    ) && Objects.equals(
        name,
        that.name
    ) && Objects.equals(
        dose,
        that.dose
    ) && timings == that.timings;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        quantity,
        dose,
        timings
    );
  }

  @Override
  public String toString() {
    return "Medication{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", quantity=" + quantity +
        ", dose='" + dose + '\'' +
        ", timings=" + timings +
        '}';
  }
}
