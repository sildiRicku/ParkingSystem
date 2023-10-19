package com.example.system.stepdefenitions;

import com.example.system.dto.TransactionDTO;
import com.example.system.enums.TransactionPaymentType;
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
        private TransactionDTO savedTransaction;

        @Given("a parking system with ID {int}")
        public void givenParkingSystemWithId(int parkingSystemId) {
            // Initialize the parkingSystem using the provided ID
            parkingSystem = new ParkingSystem(); // You may need to retrieve it from your repository
            parkingSystem.setSystemId(parkingSystemId);
        }

        @And("a transaction with the following details:")
        public void andTransactionDetails(String transactionDataTable) {
            // Parse the transaction details from the Gherkin table and create a TransactionDTO
            // You may need to use a library like DataTable to convert the table to an object
            transactionDTO = convertTableToTransactionDTO(Collections.singletonList(transactionDataTable));
        }
    private TransactionDTO convertTableToTransactionDTO(List transactionDataTable) {
        DataTable table = DataTable.create(transactionDataTable); // You may need to use the appropriate DataTable class
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> transactionData = rows.get(0); // Assuming there's only one row in the table

        // Create a TransactionDTO from the table data
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionPaymentType(TransactionPaymentType.valueOf(transactionData.get("Transaction Payment Type")));
        transactionDTO.setTransactionValue(Double.parseDouble(transactionData.get("Transaction Value")));
        transactionDTO.setPlateNumber(transactionData.get("Plate Number"));

        return transactionDTO;
    }

        @When("I save the transaction for the parking system")
        public void whenSaveTransaction() {
            savedTransaction = parkingSystemService.saveTransactionForParkingSystem(parkingSystem, transactionDTO);
        }

        @Then("the total money for the parking system should be {double}")
        public void thenTotalMoneyShouldBe(double expectedTotalMoney) {
            double actualTotalMoney = parkingSystem.getTotalMoney();
            Assert.assertEquals(expectedTotalMoney, actualTotalMoney, 0.01); // Adjust the delta as needed
        }

        @And("a transaction with the same details should be saved")
        public void andTransactionShouldBeSaved() {
            // You can retrieve the saved transaction from your repository and compare it with the expected details
            TransactionDTO expectedTransaction = (transactionDTO);
            // Retrieve the saved transaction and compare it with expectedTransaction
            Assert.assertEquals(expectedTransaction, savedTransaction);
        }


}

