package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:android.properties"})
public interface AndroidConfig extends Config {

    @Key("android.device")
    String deviceName();

    @Key("android.version")
    String osVersion();

    @Key("android.app")
    String appUrl();

    @Key("bs.user")
    String user();

    @Key("bs.key")
    String key();

    @Key("remote.url")
    String remoteUrl();
}
