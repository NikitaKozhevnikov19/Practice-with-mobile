package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.MobileConfig;
import drivers.AndroidDriverProvider;
import drivers.IosDriverProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {


    public static final String deviceHost = System.getProperty("deviceHost", "emulation");


    public static final MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    @BeforeAll
    static void setup() {
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void startDriver() {

        if (deviceHost.toLowerCase().contains("ios") || getClass().getName().contains("Ios")) {
            Configuration.browser = IosDriverProvider.class.getName();
        } else {
            Configuration.browser = AndroidDriverProvider.class.getName();
        }

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


        if ((deviceHost.contains("_bs") || deviceHost.equals("browserstack")) && !sessionId.isEmpty()) {
            try {
                Attach.addVideo(sessionId);
            } catch (Exception e) {
                System.err.println("Не удалось прикрепить видео: " + e.getMessage());
            }
        }
    }
}
