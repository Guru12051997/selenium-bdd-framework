package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // ✅ Run headless in CI (GitHub Actions)
        if (System.getProperty("headless") != null) {
            options.addArguments("--headless=new");
        }

        // ✅ Important for CI stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver.set(new ChromeDriver(options));
        getDriver().manage().window().maximize();
    }

    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}