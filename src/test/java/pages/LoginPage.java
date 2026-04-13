package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(loginBtn).click();
        Thread.sleep(10000);
//        actions.type(username, user);
//        actions.type(password, pass);
//        actions.click(loginBtn);
    }
}