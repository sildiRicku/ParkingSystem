package com.example.system.stepdefinitions;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.models.ParkingSystem;
import com.example.system.repositories.ParkingSystemRepo;
import com.example.system.services.ParkingSystemService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class ParkingSystemSteps {
    private List<ParkingSystemDTO> result;
    private ParkingSystemRepo parkingSystemRepo = Mockito.mock(ParkingSystemRepo.class);
    @Autowired
    private ParkingSystemService parkingSystemService;


    @Given("the parking system repository is populated")
    public void populateParkingSystemRepository() {
        // Create mock data
        List<ParkingSystem> mockParkingSystems = new ArrayList<>();
        ParkingSystem parkingSystem1 = new ParkingSystem();
        parkingSystem1.setIdentifier("identifier1");
        ParkingSystem parkingSystem2 = new ParkingSystem();
        parkingSystem2.setIdentifier("identifier2");
        mockParkingSystems.add(parkingSystem1);
        mockParkingSystems.add(parkingSystem2);

        // Define the behavior of the mock repository
        when(parkingSystemRepo.findAll(any(Sort.class))).thenReturn(mockParkingSystems);
    }

    @When("the getAllParkingSystems method is called")
    public void callGetAllParkingSystems() {
        // Call the actual method and store the result in the 'result' variable
        result = parkingSystemService.getAllParkingSystems();
    }
    @Then("a list of parking system DTOs is returned")
    public void verifyParkingSystemDTOList() {
        // Add assertions to verify the returned DTOs.
        // For example, check if the 'result' is not null.
        assertEquals(2, result.size()); // Check the expected size.
    }

    @And("the list is sorted in ascending order by identifier")
    public void verifySortedDTOList() {
        // Extract identifiers from the DTOs in the result
        List<String> identifiers = result.stream()
                .map(ParkingSystemDTO::getIdentifier)
                .collect(Collectors.toList());

        // Create a sorted list to compare against
        List<String> sortedIdentifiers = new ArrayList<>(identifiers);
        Collections.sort(sortedIdentifiers);

        // Check if the two lists are equal (which means it's sorted)
        assertEquals(sortedIdentifiers, identifiers);
    }

}
