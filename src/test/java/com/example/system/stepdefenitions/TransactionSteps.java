package com.example.system.stepdefenitions;

import com.example.system.dto.TransactionDTO;
import com.example.system.enums.TransactionPaymentType;
import com.example.system.helperclasses.ParkingResponse;
import com.example.system.models.ParkingSystem;
import com.example.system.services.ParkingSystemService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TransactionSteps {
    @Autowired
    private ParkingSystemService parkingSystemService;
    private ParkingSystem parkingSystem;
    private TransactionDTO transactionDTO;
    private TransactionDTO parkingResponse;

    @Given("a parking system with ID {int}")
    public void givenParkingSystemWithId(int parkingSystemId) {
        // Initialize the parkingSystem using the provided ID
        parkingSystem = new ParkingSystem(); // You may need to retrieve it from your repository
        parkingSystem.setSystemId(parkingSystemId);
    }

    @And("a transaction with the following details:")
    public void andTransactionDetails(io.cucumber.datatable.DataTable transactionDataTable) {
        // Parse the transaction details from the Gherkin table and create a TransactionDTO
        List<Map<String, String>> transactionData = transactionDataTable.asMaps(String.class, String.class);
        Map<String, String> data = transactionData.get(0);

        transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionPaymentType(TransactionPaymentType.valueOf(data.get("Transaction Payment Type")));
        transactionDTO.setTransactionValue(Double.parseDouble(data.get("Transaction Value")));
        transactionDTO.setPlateNumber(data.get("Plate Number"));
    }

    @When("I save the transaction for the parking system")
    public void whenSaveTransaction() {
        parkingResponse = parkingSystemService.saveTransactionForParkingSystem(parkingSystem, transactionDTO);
    }

    @Then("the total money for the parking system should be {double}")
    public void thenTotalMoneyShouldBe(double expectedTotalMoney) {
        double actualTotalMoney = parkingSystem.getTotalMoney();
        Assert.assertEquals(expectedTotalMoney, actualTotalMoney, 0.01); // Adjust the delta as needed
    }

    @And("the exit time should be calculated as {string}")
    public void andExitTimeShouldBeCalculated(String expectedExitTime) {
    }


}

