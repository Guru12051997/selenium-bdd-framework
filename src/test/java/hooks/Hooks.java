package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;

public class Hooks extends BaseTest {

    @Before
    public void start() {
        setup();
    }

    @After
    public void end(Scenario scenario) {

        try {
            // ✅ Take screenshot only if failed
            if (scenario.isFailed() && getDriver() != null) {
                ScreenshotUtil.capture(getDriver(), scenario.getName());
            }
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        } finally {
            // ✅ Always quit driver safely
            if (getDriver() != null) {
                tearDown();
            }
        }
    }
}