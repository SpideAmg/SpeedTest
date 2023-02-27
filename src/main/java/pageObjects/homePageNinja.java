package pageObjects;

import base.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePageNinja extends base {

WebDriver driver;

    public homePageNinja(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement myAccountDropDown;

    public WebElement myAccountDropDown(){
        return myAccountDropDown;
    }

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    public WebElement LoginOption(){
        return loginOption;
    }

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")
    private WebElement ElementIcon;

    @FindBy(xpath = "//div[@class='card-up'][2]")
    private WebElement FormsIcon;

    @FindBy(xpath = "//h5[contains(text(),'Alerts')]")
    private WebElement AlertFrameWindowIcon;

    @FindBy(xpath = "//h5[contains(text(),'Widgets')]")
    private WebElement WidgetsIcon;

    @FindBy(xpath = "//h5[contains(text(),'Interactions')]")
    private WebElement InteractionIcon;










}
