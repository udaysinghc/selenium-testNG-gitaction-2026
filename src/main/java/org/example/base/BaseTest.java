package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.ConfigReader;
import org.example.utils.JavaScriptUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class BaseTest {
    public WebDriver driver;
    public JavaScriptUtils js = new JavaScriptUtils();
    public static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void cleanAllureDirectories() {
        logger.info("Starting Before suite");
        deleteFolder(new File("allure-results"));
        deleteFolder(new File("allure-report"));
        deleteFolder(new File("screenshots"));
        deleteFolder(new File("writeExcelData"));
        deleteFolder(new File("logs"));
        logger.info("Before Method Executed");
    }

    private void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteFolder(f);
                    } else {
                        f.delete();
                    }
                }
            }
            folder.delete();
            System.out.println("Deleted: " + folder.getName());
        }
    }

    @BeforeMethod
    // @Parameters("browser")  //Parameterization
    public void setup() throws IOException {
        logger.info("Starting Before Method");
        String env = System.getProperty("env", "qa"); // default = qa
        ConfigReader.loadProperties(env);
        logger.info("Running tests on environment: " + env);
//        ConfigReader.loadProperties("qa");
        String browser = ConfigReader.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseurl"));
        logger.info("Before Method Executed");
    }

    @AfterMethod
    public void screenshotOfFailedTest(ITestResult result) {
        logger.info("Starting After Method");
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String screenshotDir = "screenshots";
            try {
                // Ensure the screenshots directory exists
                Files.createDirectories(Paths.get(screenshotDir));

                // Save screenshot
                Files.copy(src.toPath(), Paths.get(screenshotDir, result.getName() + ".png"));
                Allure.addAttachment("Page Screenshot", FileUtils.openInputStream(src));
                logger.info("Capturing the screenshot :: takeScreenshot");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info("After Method Executed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("After Class Executed");
    }


}
