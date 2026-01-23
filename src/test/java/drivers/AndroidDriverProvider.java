package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import tests.TestBase;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AndroidDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        String appPackage = "org.wikipedia";
        String appActivity = "org.wikipedia.main.MainActivity";

        switch (TestBase.deviceHost) {
            case "android_bs":
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

            case "emulation":
            case "real":
                String appPath = TestBase.config.appUrl();
                if (appPath.startsWith("src/test/resources")) {
                    File app = new File(appPath);
                    if (!app.exists()) {
                        throw new RuntimeException("APK файл не найден по пути: " + app.getAbsolutePath());
                    }
                    appPath = app.getAbsolutePath();
                }

                options.setDeviceName(TestBase.config.deviceName())
                        .setPlatformVersion(TestBase.config.platformVersion())
                        .setApp(appPath)
                        .setAppPackage(appPackage)
                        .setAppActivity(appActivity);

                if (TestBase.deviceHost.equals("emulation")) {
                    options.setFullReset(true);
                } else {
                    options.setFullReset(true);
                }
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
