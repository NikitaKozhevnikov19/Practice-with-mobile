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
        String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        Selenide.closeWebDriver();

        // Проверка платформы для добавления видео
        if (deviceHost.equals("ios") || deviceHost.equals("android")) {
            Attach.addVideo(sessionId);
        }
    }
}
