package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AndroidConfig;
import config.IosConfig;
import drivers.AndroidDriverProvider;
import drivers.IosDriverProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    public static final String deviceHost = System.getProperty("deviceHost", "android");

    public static final AndroidConfig androidConfig = ConfigFactory.create(AndroidConfig.class, System.getProperties());
    public static final IosConfig iosConfig = ConfigFactory.create(IosConfig.class, System.getProperties());

    @BeforeAll
    static void setup() {
        if (deviceHost.equals("ios")) {
            Configuration.browser = IosDriverProvider.class.getName();
        } else {
            Configuration.browser = AndroidDriverProvider.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        Configuration.remoteConnectionTimeout = 10000;
        Configuration.remoteReadTimeout = 60000;
    }

    @BeforeEach
    void startDriver() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    void tearDown() {
        String sessionId = "";
        if (WebDriverRunner.hasWebDriverStarted()) {
            sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
        }


        try {
            Attach.screenshotAs("Last screenshot");
        } catch (Exception ignored) {
        }
        try {
            Attach.pageSource();
        } catch (Exception ignored) {
        }

        Selenide.closeWebDriver();


        if (!sessionId.isEmpty() && (deviceHost.equals("ios") || deviceHost.equals("android"))) {
            try {
                Attach.addVideo(sessionId);
            } catch (Exception e) {
                System.err.println("Не удалось прикрепить видео: " + e.getMessage());
            }
        }
    }
}
