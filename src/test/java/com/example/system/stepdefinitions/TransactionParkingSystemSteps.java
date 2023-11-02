package com.example.system.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransactionParkingSystemSteps {


    @Given("a parking system with ID {int} exists in the database")
    public void a_parking_system_with_id_exists_in_the_database(Integer parkingSystemId) {
    }

    @Given("a transaction with specific details is prepared")
    public void a_transaction_with_specific_details_is_prepared() {
    }

    @When("I save the transaction for parking system {int}")
    public void i_save_the_transaction_for_parking_system(Integer parkingSystemId) {
    }

    @Then("the transaction should be successfully saved")
    public void the_transaction_should_be_successfully_saved() {
    }

    @And("the total money for the parking system should be updated")
    public void the_total_money_for_the_parking_system_should_be_updated() {
    }

}
