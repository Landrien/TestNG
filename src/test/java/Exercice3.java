import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercice3 extends Base_test {

    private static final Logger logger = LogManager.getLogger(Exercice3.class);
    @Test
    public void AchatProduit(){

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        //Thread.sleep(1000);

        WebElement login = driver.findElement(By.id("login-button"));
        login.click();
        logger.info("Validation de la connexion");

        WebElement logo = driver.findElement(By.className("app_logo"));
        boolean verif = (logo.getText().equals("Swag Labs"));
        Assert.assertTrue(verif, "Valide : le texte du logo est correct  !");

        WebElement addProduit = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addProduit.click();

        WebElement panier = driver.findElement(By.className("shopping_cart_link"));
        panier.click();
        WebElement produit = driver.findElement(By.className("cart_quantity"));
        boolean verif2 = produit.getText().equals("1");
        Assert.assertTrue(verif2, "Correct : Il y a bien un article");
        logger.info("Validation de la prise de l'article");

        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();
        WebElement firstname = driver.findElement(By.id("first-name"));
        firstname.sendKeys("Hadrien");
        WebElement lastname = driver.findElement(By.id("last-name"));
        lastname.sendKeys("landemarre");
        WebElement postal_code = driver.findElement(By.id("postal-code"));
        postal_code.sendKeys("28500");
        WebElement Continue = driver.findElement(By.id("continue"));
        Continue.click();
        logger.info("Validation du formulaire de commande");

        WebElement finale = driver.findElement(By.id("finish"));
        finale.click();
        WebElement valid = driver.findElement(By.className("complete-header"));
        boolean verif3 = valid.getText().equals("Thank you for your order!");
        Assert.assertTrue(verif3, "Correct : le texte du logo est correct !");
        logger.info("Validation de la commande");

        WebElement sideBar = driver.findElement(By.id("react-burger-menu-btn"));
        sideBar.click();
        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        logout.click();
        WebElement valid2 = driver.findElement(By.className("login_logo"));
        boolean verif4 = valid2.getText().equals("Swag Labs");
        Assert.assertTrue(verif4, "Correct : le texte du logo est correct !");
        logger.info("Validation de la déconnexion");

    }


}
