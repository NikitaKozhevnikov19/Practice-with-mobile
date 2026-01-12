package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:ios.properties"})
public interface IosConfig extends Config {

    @Key("ios.device")
    String deviceName();

    @Key("ios.version")
    String osVersion();

    @Key("ios.app")
    String appUrl();

    @Key("bs.user")
    String user();

    @Key("bs.key")
    String key();

    @Key("remote.url")
    String remoteUrl();

    @Key("appium.automationName")
    String automationName();
}
