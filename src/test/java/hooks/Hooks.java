package hooks;

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
        if (scenario.isFailed()) {
            ScreenshotUtil.capture(getDriver(), scenario.getName());
        }
        tearDown();
    }
}