package com.medication_tracker.medications;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicationRepositoryTest {

  public static final UUID ID = UUID.randomUUID();
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

    var firstMedication = mock(Medication.class);
    var secondMedication = mock(Medication.class);
    var thirdMedication = mock(Medication.class);
    var firstMedicationPo = mock(MedicationPo.class);
    var secondMedicationPo = mock(MedicationPo.class);
    var thirdMedicationPo = mock(MedicationPo.class);

    given(medicationAssembler.fromPo(firstMedicationPo))
        .willReturn(firstMedication);
    given(medicationAssembler.fromPo(secondMedicationPo))
        .willReturn(secondMedication);
    given(medicationAssembler.fromPo(thirdMedicationPo))
        .willReturn(thirdMedication);
    given(medicationDao.getAllMedications())
        .willReturn(
            List.of(firstMedicationPo, secondMedicationPo, thirdMedicationPo)
        );

    var actual = underTest.getAllMedications();

    assertThat(actual)
        .hasSize(3)
        .containsExactlyInAnyOrder(
            firstMedication,
            secondMedication,
            thirdMedication
        );

  }

  @Test
  void updateMedication() {

    var medication = mock(Medication.class);
    var savedMedicationPo = mock(MedicationPo.class);
    var toUpdateMedicationPo = mock(MedicationPo.class);
    var updatedMedication = mock(Medication.class);
    var updatedMedicationPo = mock(MedicationPo.class);

    given(medicationDao.findById(ID))
        .willReturn(Optional.of(savedMedicationPo));
    given(medicationAssembler.toPo(medication))
        .willReturn(toUpdateMedicationPo);
    given(medicationDao.update(savedMedicationPo, toUpdateMedicationPo))
        .willReturn(updatedMedicationPo);
    given(medicationAssembler.fromPo(updatedMedicationPo))
        .willReturn(updatedMedication);

    var actual = underTest.updateMedication(
        ID,
        medication
    );

    assertThat(actual)
        .isEqualTo(updatedMedication);
  }

  @Test
  void updateMedicationReturnsEmptyOptional() {

    given(medicationDao.findById(ID))
        .willReturn(Optional.empty());

    var actual = underTest.updateMedication(
        ID,
        mock(Medication.class)
    );

    assertThat(actual)
        .isNull();
  }

  @ParameterizedTest
  @NullSource
  void updateMedicationReturnsHandlesNull(Medication medication) {

    var actual = underTest.updateMedication(
        ID,
        medication
    );

    assertThat(actual)
        .isNull();
  }


  @Test
  void removeMedication() {

    var medicationPo = mock(MedicationPo.class);
    var removedMedication = mock(Medication.class);

    given(medicationDao.remove(ID))
        .willReturn(Optional.of(medicationPo));
    given(medicationAssembler.fromPo(medicationPo))
        .willReturn(removedMedication);

    var actual = underTest.removeMedication(ID);

    assertThat(actual)
        .isEqualTo(removedMedication);

  }

  @Test
  void removeMedicationHandlesOptionalEmpty() {
    given(medicationDao.remove(ID))
        .willReturn(Optional.empty());

    var actual = underTest.removeMedication(ID);

    assertThat(actual)
        .isNull();

  }
}