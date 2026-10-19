package com.saucedemo.stepdef;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class LoginStepdefs {

    WebDriver driver;
    LoginPage loginPage;

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Given("User is on login page")
    public void userIsOnLoginPage() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        sleep(1500);
    }

    @When("User enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) throws InterruptedException {
        loginPage.enterUsername(username);
        sleep(1000);
        loginPage.enterPassword(password);
        sleep(1000);
    }

    @And("User clicks the login button")
    public void userClicksTheLoginButton() throws InterruptedException {
        loginPage.clickLogin();
        sleep(1500);
    }

    @Then("User should be redirected to homepage")
    public void userShouldBeRedirectedToHomepage() throws InterruptedException {
        Assert.assertTrue(
                loginPage.getCurrentUrl().contains("inventory")
        );
        sleep(1000);
    }

    @Then("User should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String expectedMessage) throws InterruptedException {
        Assert.assertEquals(expectedMessage, loginPage.getErrorMessage());
        sleep(1000);
    }

    @After
    public void tearDown(){
        if (driver != null){
            driver.quit(); //menutup browser ketika setiap skenario selesai
        }
    }


}
