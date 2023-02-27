package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
    WebDriver driver;

    public loginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement emailAddressField;

    @FindBy(xpath = "//input[@id='input-password'] ")
    private WebElement passWordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement logInButton;


    public WebElement logInButton(){
        return logInButton;
    }
    public WebElement passWordField(){
        return passWordField;
    }
    
    public WebElement emailAddressField(){
        return emailAddressField;
    }


}
