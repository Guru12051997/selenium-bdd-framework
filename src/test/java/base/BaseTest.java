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
        String headless = System.getProperty("headless", "true");
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
        }

        // ✅ MUST for GitHub Actions (Linux stability)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-software-rasterizer");  // 🔥 fix crash
        options.addArguments("--single-process");               // 🔥 extra stability

        // ✅ Fix element visibility
        options.addArguments("--window-size=1920,1080");

        driver.set(new ChromeDriver(options));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}