import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class connexion2Test extends Base_test {

    @Test(priority = 1,groups = {"connexion","panier"})
    public void connexionTest() {

        driver.get("https://www.saucedemo.com");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("login-button"));


        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");

        submitButton.click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("shopping_cart_container")).click();

        WebElement itemName = driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[1]"));
        Assert.assertEquals(itemName.getText(), "Sauce Labs Backpack");


        driver.findElement(By.id("checkout")).click();

        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));
        WebElement postalCode = driver.findElement(By.id("postal-code"));
        WebElement continueCheckoutButton = driver.findElement(By.id("continue"));


        firstName.sendKeys("Hadrien");
        lastName.sendKeys("Landemarre");
        postalCode.sendKeys("28500");

        continueCheckoutButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        WebElement spanTitle = driver.findElement(By.tagName("span"));

        Assert.assertEquals(spanTitle.getText(), "Checkout: Complete!");

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assert.assertNotNull(driver.findElement(By.id("login-button")));

    }


}
