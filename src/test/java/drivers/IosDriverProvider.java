package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import tests.TestBase;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class IosDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        XCUITestOptions options = new XCUITestOptions();


        options.setDeviceName(TestBase.config.deviceName())
                .setPlatformVersion(TestBase.config.platformVersion())
                .setApp(TestBase.config.appUrl())
                .setAutomationName(TestBase.config.automationName());

        options.setCapability("bstack:options", Map.of(
                "userName", TestBase.config.bsUser(),
                "accessKey", TestBase.config.bsKey(),
                "projectName", "Sample iOS Project",
                "buildName", "ios-build-2026"
        ));

        try {
            return new IOSDriver(new URL(TestBase.config.remoteUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
