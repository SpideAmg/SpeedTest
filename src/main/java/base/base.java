package base;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;


public class base {

    public Logger log;
    public Properties prop = new Properties();
    public WebDriver driver;
    String duration = prop.getProperty("implicit.wait", "10");

    @BeforeMethod

    public void SetUp() throws IOException {
        log = LogManager.getLogger(base.class.getName());
        driver = initializeDriver();
        log.info("BeforeMethod Execution");
        log.info("Browser launched");
        driver.get(prop.getProperty("url"));
        log.info("Navigated to application URL");
        driver.manage().window().maximize();
        log.info("Windows got maximized");
        log.info("BeforeMethod Executed");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(duration)));
    }

    public WebDriver initializeDriver() throws IOException {
        String propertiesPath = System.getProperty("user.dir") + "\\src\\config.properties";
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("Microsoft Edge")){
            driver = new EdgeDriver();
        }
        return driver;
    }


    @DataProvider
    public Object[][] getLoginData(){
        Object [][] data = {{"speedy@gmail.com","Speedy777", "Successfully"}};
        return data;
    }

    @AfterMethod
    public void close(){
        driver.quit();
        log.info("Browser quit");
    }

    public String takeScreenshot(String testName, WebDriver driver) throws IOException {

        File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
        FileUtils.copyFile(SourceFile,new File(destinationFilePath));

        return destinationFilePath;
    }
}