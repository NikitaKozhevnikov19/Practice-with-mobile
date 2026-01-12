package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.IosConfig;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class IosDriverProvider implements WebDriverProvider {

    private final IosConfig config = ConfigFactory.create(IosConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(config.deviceName())
                .setPlatformVersion(config.osVersion())
                .setApp(config.appUrl())
                .setAutomationName(config.automationName()); // берем из пропертей

        options.setCapability("bstack:options", Map.of(
                "userName", config.user(),
                "accessKey", config.key(),
                "projectName", "Sample iOS Project",
                "buildName", "ios-build-2026"
        ));

        try {
            return new IOSDriver(new URL(config.remoteUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
