import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class Exercice1_Connexion_Test extends Base_test {

    private static final Logger logger = LogManager.getLogger(Exercice1_Connexion_Test.class);

    @Test(groups = "connexion")
    public void Connexion() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        logger.info("Initialisation des champs de connexion");
        logger.info("Saisie de l'identifiant et du mot de passe");
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        Thread.sleep(1000);

        WebElement login = driver.findElement(By.id("login-button"));
        login.click();

        WebElement logo = driver.findElement(By.className("app_logo"));
        boolean verif = (logo.getText().equals("Swag Labs"));
        Assert.assertTrue(verif, "Valide : le texte du logo est correct  !");
        logger.trace("Validation de la connexion");
        long endTime = System.currentTimeMillis();
        test.info("Temps d'exécution intermédiaire : " + (endTime - startTime) + " ms");

    }


    @Test(priority = 2,groups = {"connexion"})
    public void ConnexionFail() {
        driver.get("https://www.saucedemo.com");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("login-button"));


        usernameInput.sendKeys("locked_out_user");
        passwordInput.sendKeys("secret_sauce");

        submitButton.click();

        WebElement logo = driver.findElement(By.tagName("h3"));

        Assert.assertEquals(logo.getText(), "Epic sadface: Sorry, this user has been locked out.");
        logger.trace("Validation de la connexion échouée");
    }
/*
    @Test (groups = "Login")
    @Parameters({"username", "password"})
    public void login(String username, String password){

        test = extent.createTest("test connexion OK", "This is a sample test");
        driver.get("https://www.saucedemo.com/");


        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();


        // Attendre l'affichage du logo après connexion
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app_logo")));

        Assert.assertTrue(logo.isDisplayed(), "Valide : Connexion réussi !");
        System.out.println(" Connexion réussie avec : " + username);
    }

    @DataProvider(name = "LoginData")
    public Object[][] ProvideLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user","secret_sauce"},
                {"problem_user","secret_sauce"},
                {"performance_glitch_user","secret_sauce"}
        };
    }

    @Test(dataProvider = "ExcelData", dataProviderClass = ExcelTestDataProvider.class)
    public void testProvider(String username, String password) {

        test = extent.createTest("test connexion OK", "This is a sample test");
        System.out.println("Test avec les identifiants : " + username + " / " + password);
        Assert.assertNotNull(username);
        Assert.assertNotNull(password);
    }

*/
    @Test(dataProvider = "LoginData", groups = "Login")
    public void loginProvider(String username, String password) throws InterruptedException {
        test = extent.createTest("test connexion OK", "This is a sample test");
        driver.get("https://www.saucedemo.com/");


        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(1000);

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        login.click();

        WebElement logo = driver.findElement(By.className("app_logo"));
        boolean verif = (logo.getText().equals("Swag Labs"));
        Assert.assertTrue(verif, "Valide : le texte du logo est correct  !");
    }


}
