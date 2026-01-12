package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:android.properties"})
public interface AndroidConfig extends Config {

    @Key("android.device")
    String androidDevice();

    @Key("android.version")
    String androidVersion();

    @Key("android.app")
    String androidApp();

    @Key("bs.user")
    String bsUser();

    @Key("bs.key")
    String bsKey();

    @Key("remote.url")
    String remoteUrl();
}
