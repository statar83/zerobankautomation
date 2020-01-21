package com.zerobank.stepdefinitions;

import com.zerobank.utilities.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public void setup (){
        System.out.println("Test setup!");
        Driver.get().manage().window().maximize();
    }
    @After
    public void teardown(Scenario scenario){
        //if test failed
        if(scenario.isFailed()){
            System.out.println("Test failed!");
            byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            //if test passed
        }else{
            System.out.println("Cleanup!");
            System.out.println("Test completed!");
        }
        Driver.close();
    }
}
