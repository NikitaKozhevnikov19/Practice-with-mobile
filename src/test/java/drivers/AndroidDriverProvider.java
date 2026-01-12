package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AndroidConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AndroidDriverProvider implements WebDriverProvider {
    private final AndroidConfig config = ConfigFactory.create(AndroidConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(config.deviceName())
                .setPlatformVersion(config.osVersion())
                .setApp(config.appUrl());

        options.setCapability("bstack:options", Map.of(
                "userName", config.user(),
                "accessKey", config.key(),
                "projectName", "Wikipedia Android Project",
                "buildName", "android-build-2026"
        ));

        try {
            return new AndroidDriver(new URL(config.remoteUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

