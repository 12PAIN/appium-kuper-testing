package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    public static String APIUM_URL = "http://127.0.0.1:4723/wd/hub";

    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appPackage", "ru.instamart");
        capabilities.setCapability("appActivity", "com.sbermarket.MainActivity");
//        capabilities.setCapability("app", "/Users/12pain/test/androidTesting/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL(APIUM_URL), capabilities);
    }

    protected void tearDown() throws Exception{
        driver.quit();
        super.tearDown();
    }

}
