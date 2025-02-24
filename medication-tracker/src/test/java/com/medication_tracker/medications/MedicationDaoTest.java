package com.medication_tracker.medications;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class MedicationDaoTest {

  public static final UUID ID = UUID.randomUUID();
  private final MedicationDao underTest = new MedicationDao();

  @Test
  void save() {

    var medicationPo = new MedicationPo(
        ID,
        "Paracetomol",
        10,
        "500mg",
        Timings.THREE_TIMES_A_DAY
    );

    var actual = underTest.save(
        medicationPo
    );

    assertThat(actual)
        .isEqualTo(medicationPo);

  }

  @Test
  void getAllMedications() {
  }

  @Test
  void findById() {
  }

  @Test
  void update() {
  }

  @Test
  void remove() {
  }
}