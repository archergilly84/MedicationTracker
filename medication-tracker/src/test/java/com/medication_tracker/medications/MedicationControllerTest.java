package com.medication_tracker.medications;

import static com.medication_tracker.medications.Timings.DAILY;
import static com.medication_tracker.medications.Timings.THREE_TIMES_A_DAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class MedicationControllerTest {

  @Mock
  private MedicationService medicationService;
  @Mock
  private MedicationAssembler medicationAssembler;
  @InjectMocks
  private MedicationController underTest;

  @Test
  void GivenListOfMedications_WhenGetMedicationsCalled_ThenReturnAllMedications() {

    var firstMedication = new Medication(
        UUID.randomUUID(),
        "Topax",
        12,
        "10mg",
        DAILY
    );
    var secondMedication = new Medication(
        UUID.randomUUID(),
        "Paracetamol",
        52,
        "500mg",
        THREE_TIMES_A_DAY
    );
    var firstMedicationDto = new MedicationDto(
        firstMedication.getId(),
        firstMedication.getName(),
        firstMedication.getQuantity(),
        firstMedication.getDose(),
        firstMedication.getTimings()
    );
    var secondMedicationDto = new MedicationDto(
        secondMedication.getId(),
        secondMedication.getName(),
        secondMedication.getQuantity(),
        secondMedication.getDose(),
        secondMedication.getTimings()
    );
    given(medicationService.getAllMedications()).willReturn(
        List.of(
            firstMedication,
            secondMedication
        )
    );
    given(medicationAssembler.toDto(firstMedication))
        .willReturn(firstMedicationDto);
    given(medicationAssembler.toDto(secondMedication))
        .willReturn(secondMedicationDto);

    var actual = underTest.getMedications();

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody())
        .hasSize(2)
        .containsExactlyInAnyOrder(firstMedicationDto, secondMedicationDto);
  }

  @Test
  void createMedication() {

    var medicationDto = new MedicationDto(
        null,
        "Topax",
        12,
        "10mg",
        DAILY
    );

    var savedMedicationDto = new MedicationDto(
        UUID.randomUUID(),
        "Topax",
        12,
        "10mg",
        DAILY
    );

    given(medicationService.createMedication(medicationDto))
        .willReturn(savedMedicationDto);

    var actual = underTest.createMedication(medicationDto);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(actual.getBody()).isEqualTo(savedMedicationDto);

  }

  @Test
  void updateMedication() {

    var id = UUID.randomUUID();
    var medicationDto = new MedicationDto(
            null,
            "Topax",
            12,
            "10mg",
            DAILY
    );

    var updatedMedicationDto = new MedicationDto(
            id,
            "Topax",
            2,
            "10mg",
            DAILY
    );

    given(medicationService.updateMedication(id, medicationDto))
            .willReturn(updatedMedicationDto);

    var actual = underTest.updateMedication(id, medicationDto);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody()).isEqualTo(updatedMedicationDto);

  }

  @Test
  void removeMedication() {

    var id = UUID.randomUUID();

    given(medicationService.removeMedication(id)).willReturn(id);

    var actual = underTest.removeMedication(id);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody()).isEqualTo(id);

  }

  @Test
  void getReminders() {
  }

  @Test
  void setReminder() {
  }

  @Test
  void updateReminder() {
  }

  @Test
  void removeReminder() {
  }
}