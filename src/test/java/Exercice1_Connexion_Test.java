import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class Exercice1_Connexion_Test {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    @Test(groups = "connexion")
    @Parameters ({"username", "password"})
    public void test(String usernameWeb, String passwordWeb) throws InterruptedException {


        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        Thread.sleep(1000);

        WebElement login = driver.findElement(By.id("login-button"));
        login.click();

        WebElement logo = driver.findElement(By.className("app_logo"));
        boolean verif = (logo.getText().equals("Swag Labs"));
        Assert.assertTrue(verif, "Valide : le texte du logo est correct  !");
    }


    @Test(groups = "connexion")
    public void test2() throws InterruptedException {

        //WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//br[2]"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        Thread.sleep(1000);

        WebElement login = driver.findElement(By.id("login-button"));
        login.click();

        WebElement erreur = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"));
        boolean verif = erreur.getText().equals("Epic sadface: Username is required");
        Assert.assertTrue(verif, "Correct : Il n'y pas eu de connexion !");
    }

    @Test (groups = "Login")
    @Parameters({"username", "password"})
    public void login(String username, String password){


        driver.get("https://www.saucedemo.com/");


        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();


        // Attendre l'affichage du logo après connexion
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app_logo")));

        Assert.assertTrue(logo.isDisplayed(), "Erreur : Connexion échouée !");
        System.out.println(" Connexion réussie avec : " + username);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
