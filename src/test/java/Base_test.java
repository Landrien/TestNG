import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Base_test {

   static WebDriver driver;
   static WebDriverWait wait;
   static ExtentReports extent;
   static ExtentTest test;
   @BeforeTest
    public void setupTest() {

       String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
       ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
       extent = new ExtentReports();
       extent.attachReporter(sparkReporter);

       // Ajouter des informations au rapport
       extent.setSystemInfo("Tester", "QA Engineer");
       extent.setSystemInfo("OS", System.getProperty("os.name"));
       extent.setSystemInfo("Java Version", System.getProperty("java.version"));
   }

    @BeforeMethod
    public void setup(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String description = result.getMethod().getDescription();

        test = extent.createTest(testName, description != null ? description : "Aucune description");

        test.info("Démarrage du test : " + testName);
        test.info("Classe de test : " + className);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }




    @AfterMethod
    public void teardown(ITestResult result) throws IOException {

        long duration = result.getEndMillis() - result.getStartMillis();

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test réussi en " + duration + " ms");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test échoué en " + duration + " ms");
            test.fail("Erreur : " + result.getThrowable().getMessage());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotName = "../screenshots/" + result.getMethod().getMethodName();
            //String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + result.getMethod().getMethodName() + ".png";
            String screenshotPath = "../screenshots/" + result.getMethod().getMethodName() + ".png";
            File destination = new File(screenshotPath);
            FileUtils.copyFile(screenshot, destination);
            test.addScreenCaptureFromPath(screenshotPath);

        } /*else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test ignoré : " + result.getThrowable().getMessage());
        }*/


        driver.quit();
    }

    @AfterTest
    public void teardownTest() {
        extent.flush();
    }
}
