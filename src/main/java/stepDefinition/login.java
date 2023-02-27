package stepDefinition;
import base.base;
import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.accountPage;
import pageObjects.homePageNinja;
import pageObjects.loginPage;

import java.io.IOException;

public class login extends base{
    loginPage LogP;
    homePageNinja HPN;
    accountPage ACP;


        @Given("^Open any Browser$")
        public void open_any_browser() throws IOException {
            driver = initializeDriver();
            driver.manage().window().maximize();
        }

        @And("^Navigate to Login page$")
        public void navigate_to_login_page(){
            driver.get(prop.getProperty("url"));
            HPN = new homePageNinja(driver);
            HPN.myAccountDropDown().click();
            HPN.LoginOption().click();

    }
        @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
        public void user_enters_username_as_something_and_password_as_something_into_the_fields(String email, String password){
            LogP = new loginPage(driver);
            LogP.emailAddressField().sendKeys(email);
            LogP.passWordField().sendKeys(password);

        }
         @And("^User clicks on Login button$")
         public void user_clicks_on_login_button(){
             LogP.logInButton().click();
         }

        @Then("^Verify user is able to successfully login$")
        public void verify_user_is_able_to_successfully_login(){
            ACP = new accountPage(driver);
            Assert.assertTrue(ACP.editAccountInformationOption().isDisplayed());
        }
        @After
        public void closeBrowser(){
            driver.close();
        }


    }

