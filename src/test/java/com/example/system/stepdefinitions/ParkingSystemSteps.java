package com.example.system.stepdefinitions;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.services.ParkingSystemService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.model.DataTable;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@CucumberContextConfiguration
@WithTag("regression")
public class ParkingSystemSteps {
    private List<ParkingSystemDTO> parkingSystems = new ArrayList<>();
    private List<ParkingSystemDTO> expected = new ArrayList<>();
    @Autowired
    private ParkingSystemService parkingSystemService;

    @Given("the parking systems exist in the database")
    public void parkingSystemsExistInTheDatabase() {
        ParkingSystemDTO parkingSystemDTO1 = Mockito.mock(ParkingSystemDTO.class);
        ParkingSystemDTO parkingSystemDTO2 = Mockito.mock(ParkingSystemDTO.class);
        parkingSystems.add(parkingSystemDTO1);
        parkingSystems.add(parkingSystemDTO2);
    }

    @When("I retrieve all parking systems")
    public void retrieveAllParkingSystems() {
        expected = parkingSystemService.getAllParkingSystems();
    }

    @Then("the list of parking systems should be returned")
    public void listOfParkingSystemsShouldBeReturned() {
        assertEquals(parkingSystems.size(), parkingSystems.size());
        for (int i = 0; i < parkingSystems.size(); i++) {
            assertEquals(parkingSystems.get(i), parkingSystems.get(i));
        }
    }

}
