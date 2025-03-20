import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ConnexionTest extends Base_test {

    @Test(priority = 1,groups = {"connexion"}, dataProvider = "ExcelData", dataProviderClass = ExcelTestDataProvider.class)
    @Parameters({"username","password"})
    public void connexionTest(String username, String password) {

        test = extent.createTest("test connexion PAS OK", "This is a sample test");
        driver.get("https://www.saucedemo.com");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("login-button"));


        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        submitButton.click();

        WebElement product = driver.findElement(By.xpath("//span[@class=\"title\"]"));

        Assert.assertEquals(product.getText(), "Products");
    }

    @Test(priority = 2,groups = {"connexion"})
    public void connexionTestFailed() {
        driver.get("https://www.saucedemo.com");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("login-button"));


        usernameInput.sendKeys("locked_out_user");
        passwordInput.sendKeys("secret_sauce");

        submitButton.click();

        WebElement logo = driver.findElement(By.tagName("h3"));

        Assert.assertEquals(logo.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
       return new Object[][]{
               {"standard_user","secret_sauce"},
               {"visual_user","secret_sauce"},
               {"performance_glitch_user","secret_sauce"},
               {"problem_user","secret_sauce"},
       };
    }


}
