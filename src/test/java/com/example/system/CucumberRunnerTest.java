package com.example.system;

 import io.cucumber.junit.CucumberOptions;
 import org.junit.jupiter.api.parallel.Execution;
 import org.junit.jupiter.api.parallel.ExecutionMode;
 import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@Execution(ExecutionMode.CONCURRENT)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources" , tags = "",
        glue = {"com.example.system.stepdefinitions", "steps"}
)
public class CucumberRunnerTest {

}

