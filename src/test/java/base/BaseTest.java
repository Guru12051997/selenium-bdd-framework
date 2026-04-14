package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URL;
import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void setup() {

        try {

            // 👉 SWITCH: -Dbrowserstack=true
            boolean isBrowserStack = "true".equalsIgnoreCase(System.getProperty("browserstack"));

            if (isBrowserStack) {

                // ✅ Get from ENV (recommended)
                String username = System.getenv("BROWSERSTACK_USERNAME");
                String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

                // 👉 Fallback (your credentials)
                if (username == null || accessKey == null) {
                    username = "guruprasaddas_eJaPEZ";
                    accessKey = "Ci2H1d66yqe4vJ1bdJsr";
                }

                // 🔥 BrowserStack Capabilities
                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("browserName", "Chrome");
                caps.setCapability("browserVersion", "latest");

                MutableCapabilities bsOptions = new MutableCapabilities();
                bsOptions.setCapability("os", "Windows");
                bsOptions.setCapability("osVersion", "11");
                bsOptions.setCapability("buildName", "BDD Framework");
                bsOptions.setCapability("sessionName", "Cucumber Test");

                caps.setCapability("bstack:options", bsOptions);

                WebDriver bsDriver = new RemoteWebDriver(
                        new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"),
                        caps
                );

                driver.set(bsDriver);
                System.out.println("🚀 Running on BrowserStack");

            } else {

                // ✅ LOCAL execution
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // Headless optional
                if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                    options.addArguments("--headless=new");
                }

                // Stability fixes
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080");

                driver.set(new ChromeDriver(options));
                System.out.println("💻 Running Locally");
            }

            // Common wait
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}