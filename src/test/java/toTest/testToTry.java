package toTest;
import base.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.homePageNinja;
import pageObjects.loginPage;

public class testToTry extends base {
    Logger log = LogManager.getLogger(testToTry.class.getName());

    @Test
    public void letsTest(){
        homePageNinja HPN = new homePageNinja(driver);
        HPN.myAccountDropDown().click();
        log.info("Clicked on My Account dropdown");
        HPN.LoginOption().click();
        log.info("Clicked on login option");


        loginPage LogP = new loginPage(driver);
        LogP.emailAddressField().sendKeys("Testfail@hle.dz");
        log.info("Email address entered successfully");
        LogP.passWordField().sendKeys("123456789");
        log.info("Password entered successfully");
        LogP.logInButton().click();
        log.info("Clicked on Login Button");
        String expectedText = driver.getTitle();
        Assert.assertEquals("My Account",expectedText);
        log.info(expectedText);
    }
}
