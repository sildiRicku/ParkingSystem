package com.example.system.stepdefenitions;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class AddTransactionStepDefinition {
}
