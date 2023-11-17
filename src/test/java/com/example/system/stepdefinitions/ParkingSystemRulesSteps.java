package com.example.system.stepdefinitions;

import com.example.system.dto.RuleDTO;
import com.example.system.services.ParkingSystemService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ParkingSystemRulesSteps {
    @Autowired
    private ParkingSystemService parkingSystemService;

    private int parkingSystemId;
    private List<RuleDTO> parkingSystemRules;

    @Given("a parking system exists in the database")
    public void parkingSystemExists() {
    }

    @When("I retrieve rules for the parking system with ID {int}")
    public void retrieveRulesForParkingSystem(int parkingSystemId) {
        this.parkingSystemId = parkingSystemId;
        parkingSystemRules = parkingSystemService.getRulesForParkingSystem(parkingSystemId);
    }

    @Then("a list of rules should be returned")
    public void verifyRulesReturned() {
        assertTrue(!parkingSystemRules.isEmpty());
    }

}