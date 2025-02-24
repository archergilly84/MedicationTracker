package com.medication_tracker.medications;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicationRepositoryTest {

  @Mock
  private MedicationDao medicationDao;
  @Mock
  private MedicationAssembler medicationAssembler;
  @InjectMocks
  private MedicationRepository underTest;

  @Test
  void saveMedication() {

    var medication = mock(Medication.class);
    var medicationPo = mock(MedicationPo.class);
    var savedMedicationPo = mock(MedicationPo.class);
    var savedMedication = mock(Medication.class);

    given(medicationAssembler.toPo(medication))
        .willReturn(medicationPo);
    given(medicationDao.save(medicationPo))
        .willReturn(savedMedicationPo);
    given(medicationAssembler.fromPo(savedMedicationPo))
        .willReturn(savedMedication);

    var actual = underTest.saveMedication(medication);

    assertThat(actual).isEqualTo(savedMedication);
  }

  @Test
  void getAllMedications() {
  }

  @Test
  void updateMedication() {
  }

  @Test
  void removeMedication() {
  }
}