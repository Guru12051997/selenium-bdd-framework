package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // ✅ Always run headless in CI
        if (System.getProperty("headless") != null) {
            options.addArguments("--headless=new");
        }

        // ✅ Stability fixes (VERY IMPORTANT)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // ✅ Fix element visibility issue
        options.addArguments("--window-size=1920,1080");

        driver.set(new ChromeDriver(options));

        // ✅ Implicit wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ❌ Don't use maximize in headless
    }

    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}