package toTest;

import base.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pageObjects.accountPage;
import pageObjects.homePageNinja;
import pageObjects.loginPage;

import java.io.IOException;

public class logIn extends base {
    Logger log = LogManager.getLogger(logIn.class.getName());

    @Test(dataProvider ="getLoginData")

    public void log(String email, String password, String expectedStatus) throws IOException {

        //log = LogManager.getLogger(logIn.class);
        homePageNinja HPN = new homePageNinja(driver);
        HPN.myAccountDropDown().click();
        log.info("Clicked on My Account dropdown");
        HPN.LoginOption().click();
        log.info("Clicked on login option");


        loginPage LogP = new loginPage(driver);
        LogP.emailAddressField().sendKeys(email);
        log.info("Email address entered successfully");
        LogP.passWordField().sendKeys(password);
        log.info("Password entered successfully");
        LogP.logInButton().click();
        log.info("Clicked on Login Button");

        accountPage ACP = new accountPage(driver);
        String actualResult = null;
        try {
            if (ACP.editAccountInformationOption().isDisplayed()) {
                log.info("User logged in successfully");
                actualResult = "Successfully";
            }
        }catch (Exception e){
            log.info("User didn't log in");
            actualResult = "Failure";
        }if(actualResult.equals(expectedStatus)) {

            log.info("Login Test got passed");

        }else {

            log.info("Login Test got failed");
        }
    }


}
