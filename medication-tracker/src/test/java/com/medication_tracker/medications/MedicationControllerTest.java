package com.medication_tracker.medications;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class MedicationControllerTest {

  @Mock
  private MedicationService medicationService;
  @InjectMocks
  private MedicationController underTest;

  @Test
  void GivenListOfMedications_WhenGetMedicationsCalled_ThenReturnAllMedications() {

    var firstMedicationDto = mock(MedicationDto.class);
    var secondMedicationDto = mock(MedicationDto.class);
    given(medicationService.getAllMedications())
        .willReturn(
            List.of(firstMedicationDto, secondMedicationDto)
        );

    var actual = underTest.getMedications();

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(actual.getBody())
        .hasSize(2)
        .containsExactlyInAnyOrder(
            firstMedicationDto,
            secondMedicationDto
        );
  }

  @Test
  void createMedication() {

    var medicationDto = mock(MedicationDto.class);
    var savedMedicationDto = mock(MedicationDto.class);
    given(medicationService.createMedication(medicationDto))
        .willReturn(savedMedicationDto);

    var actual = underTest.createMedication(medicationDto);

    assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(actual.getBody()).isEqualTo(savedMedicationDto);

  }

  @Test
  void updateMedication() {

    var id = UUID.randomUUID();
    var medicationDto = mock(MedicationDto.class);
    var updatedMedicationDto = mock(MedicationDto.class);

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

}