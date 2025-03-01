package com.medication_tracker.medications;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicationServiceTest {

  private static final UUID ID = UUID.randomUUID();

  @InjectMocks
  private MedicationService underTest;
  @Mock
  private MedicationAssembler medicationAssembler;
  @Mock
  private MedicationRepository medicationRepository;

  @Test
  void createMedication() {

    var medicationDto = mock(MedicationDto.class);
    var medication = mock(Medication.class);
    var savedMedication = mock(Medication.class);
    var savedMedicationDto = mock(MedicationDto.class);
    given(medicationAssembler.toDomain(medicationDto))
        .willReturn(medication);
    given(medicationRepository.saveMedication(medication))
        .willReturn(savedMedication);
    given(medicationAssembler.toDto(savedMedication))
        .willReturn(savedMedicationDto);

    var actual = underTest.createMedication(medicationDto);

    assertThat(actual)
        .isEqualTo(savedMedicationDto);
  }

  @ParameterizedTest
  @NullSource
  void createMedicationHandlesNull(MedicationDto medicationDto) {
    assertThat(underTest.createMedication(medicationDto))
        .isNull();
  }

  @Test
  void getAllMedications() {

    var firstMedication = mock(Medication.class);
    var secondMedication = mock(Medication.class);
    var firstMedicationDto = mock(MedicationDto.class);
    var secondMedicationDto = mock(MedicationDto.class);
    given(medicationRepository.getAllMedications()).willReturn(
        List.of(
            firstMedication,
            secondMedication
        )
    );
    given(medicationAssembler.toDto(firstMedication)).willReturn(firstMedicationDto);
    given(medicationAssembler.toDto(secondMedication)).willReturn(secondMedicationDto);

    var actual = underTest.getAllMedications();

    assertThat(actual)
        .hasSize(2)
        .containsExactlyInAnyOrder(
            firstMedicationDto,
            secondMedicationDto
        );
  }

  @Test
  void getAllMedications_returnsEmptyList() {

    given(medicationRepository.getAllMedications()).willReturn(emptyList());

    var actual = underTest.getAllMedications();

    assertThat(actual).isEmpty();
  }


  @Test
  void updateMedication() {

    var medicationDto = mock(MedicationDto.class);
    var updatedMedicationDto = mock(MedicationDto.class);
    var updatedMedication = mock(Medication.class);
    var medication = mock(Medication.class);

    given(medicationAssembler.toDomain(medicationDto))
        .willReturn(medication);
    given(medicationRepository.updateMedication(ID, medication))
        .willReturn(updatedMedication);
    given(medicationAssembler.toDto(updatedMedication))
        .willReturn(updatedMedicationDto);

    var actual = underTest.updateMedication(ID, medicationDto);

    assertThat(actual).isEqualTo(updatedMedicationDto);
  }

  public static Stream<Arguments> nullArguments() {
    return Stream.of(
        Arguments.of(null, mock(MedicationDto.class)),
        Arguments.of(null, null),
        Arguments.of(ID, null)
    );
  }

  @ParameterizedTest
  @MethodSource("nullArguments")
  void updateMedication_handlesNull(UUID id, MedicationDto medicationDto) {
    assertThat(underTest.updateMedication(id, medicationDto))
        .isNull();
  }

  @Test
  void removeMedication() {

    var medication = mock(Medication.class);
    var medicationDto = mock(MedicationDto.class);
    given(medicationRepository.removeMedication(ID))
        .willReturn(medication);
    given(medicationAssembler.toDto(medication))
        .willReturn(medicationDto);
    given(medicationDto.id()).willReturn(ID);

    var actual = underTest.removeMedication(ID);

    assertThat(actual).isEqualTo(ID);

  }
  @ParameterizedTest
  @NullSource
  void removeMedication_handlesNull(UUID id) {
    assertThat(underTest.removeMedication(id)).isNull();
  }
}