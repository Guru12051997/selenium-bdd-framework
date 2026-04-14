package stepdefs;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.JsonUtil;

public class LoginSteps extends BaseTest {

    LoginPage loginPage;

    @Given("user is on login page")
    public void openPage() {
        getDriver().get("https://facebook.com");
        loginPage = new LoginPage(getDriver());
    }

    @When("user enters username and password")
    public void enterData() throws InterruptedException {
        String user = JsonUtil.get("username");
        String pass = JsonUtil.get("password");
        System.out.println("username---->"+user);
        System.out.println("password---->"+pass);

        loginPage.login(user, pass);
    }

    @Then("user should see dashboard")
    public void verify() throws InterruptedException {
       loginPage.verifyforgotPassword();
        System.out.println("Verified");
    }
}