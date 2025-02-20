package com.medication_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = "com.medication_tracker"
)
public class MedicationTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicationTrackerApplication.class, args);
	}

}
