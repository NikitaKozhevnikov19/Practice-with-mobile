package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import tests.TestBase;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AndroidDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        switch (TestBase.deviceHost) {
            case "emulation":
                options.setDeviceName(TestBase.config.deviceName())
                        .setPlatformVersion(TestBase.config.platformVersion())
                        .setApp(TestBase.config.appUrl());
                break;

            case "real":
                options.setDeviceName(TestBase.config.deviceName())
                        .setPlatformVersion(TestBase.config.platformVersion())
                        .setAppPackage("org.wikipedia")
                        .setAppActivity("org.wikipedia.main.MainActivity")
                        .setNoReset(true)
                        .setCapability("udid", TestBase.config.deviceName());
                break;

            case "browserstack":
                options.setDeviceName(TestBase.config.deviceName())
                        .setPlatformVersion(TestBase.config.platformVersion())
                        .setApp(TestBase.config.appUrl())
                        .setCapability("bstack:options", Map.of(
                                "userName", TestBase.config.bsUser(),
                                "accessKey", TestBase.config.bsKey(),
                                "projectName", "Wikipedia Android Project",
                                "buildName", "android-build-2026"
                        ));
                break;

            default:
                throw new RuntimeException("Неизвестный deviceHost: " + TestBase.deviceHost);
        }

        options.setAutomationName(TestBase.config.automationName());

        try {
            return new AndroidDriver(new URL(TestBase.config.remoteUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
