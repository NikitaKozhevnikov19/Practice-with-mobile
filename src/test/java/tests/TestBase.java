package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AndroidConfig;
import config.IosConfig;
import drivers.AndroidDriverProvider;
import drivers.IosDriverProvider;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    public static String deviceHost = System.getProperty("deviceHost", "android");

    public static AndroidConfig androidConfig = ConfigFactory.create(AndroidConfig.class, System.getProperties());
    public static IosConfig iosConfig = ConfigFactory.create(IosConfig.class, System.getProperties());

    @BeforeAll
    static void setup() {
        if (deviceHost.equals("ios")) {
            Configuration.browser = IosDriverProvider.class.getName();
        } else {
            Configuration.browser = AndroidDriverProvider.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void startDriver() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        open();
    }

    @AfterEach
    void tearDown() {

        String sessionId = ((RemoteWebDriver) com.codeborne.selenide.WebDriverRunner.getWebDriver()).getSessionId().toString();

        helpers.Attach.screenshotAs("Last screenshot");
        helpers.Attach.pageSource();

        com.codeborne.selenide.Selenide.closeWebDriver();

        if (deviceHost.equals("ios") || deviceHost.equals("android")) {
            helpers.Attach.addVideo(sessionId);
        }
    }
}
