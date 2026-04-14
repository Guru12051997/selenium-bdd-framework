package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.SeleniumActions;

public class LoginPage {

    WebDriver driver;
    SeleniumActions actions;

    By username = By.xpath("//input[@name='email']");
    By password = By.xpath("//input[@name='pass']");
    By loginBtn = By.xpath("//span[text()='Log in']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        actions = new SeleniumActions(driver);
    }

    public void login(String user, String pass) throws InterruptedException {

        driver.findElement(username).sendKeys(user);
        Thread.sleep(2000);
        driver.findElement(password).sendKeys(pass);
        Thread.sleep(2000);
       // driver.findElement(loginBtn).click();
      //  Thread.sleep(10000);
//        actions.type(username, user);
//        actions.type(password, pass);
//        actions.click(loginBtn);
    }

    public void verifyforgotPassword() throws InterruptedException {
        // Locate the element using XPath
        WebElement forgotPasswordLink = driver.findElement(By.xpath("//span[text()='Forgotten password?']"));

        // Validate that the element is displayed
        Assert.assertTrue(forgotPasswordLink.isDisplayed(), "Forgotten password link is not visible");

        // Validate the text
        String actualText = forgotPasswordLink.getText();
        Assert.assertEquals("Forgotten password?", actualText);
        forgotPasswordLink.click();
        Thread.sleep(3000);


    }
}