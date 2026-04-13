package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("target/screenshots"));
            Files.copy(src.toPath(), Paths.get("target/screenshots/" + name + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}