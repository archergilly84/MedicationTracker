package com.medicaion_tracker.medications;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void GivenListOfMedications_WhenGetMedicationCalled_ThenReturnAllMedications() throws Exception {

        var response = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/medication"));

        response.andExpect(status().isOk());
    }

    @Test
    void createMedication() {
    }

    @Test
    void updateMedication() {
    }

    @Test
    void removeMedication() {
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