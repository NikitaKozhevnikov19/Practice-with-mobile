package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:ios.properties"})
public interface IosConfig extends Config {

    @Key("ios.device")
    String iosDevice();

    @Key("ios.version")
    String iosVersion();

    @Key("ios.app")
    String iosApp();

    @Key("bs.user")
    String bsUser();

    @Key("bs.key")
    String bsKey();

    @Key("remote.url")
    String remoteUrl();

    @Key("appium.automationName")
    String appiumAutomationName();
}
