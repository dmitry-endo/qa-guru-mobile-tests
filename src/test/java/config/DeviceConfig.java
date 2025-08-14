package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
//        "system:properties",
        "classpath:config/${env}.properties",
//        "classpath:config/local.properties",
//        "classpath:config/local.properties"
})
public interface DeviceConfig extends Config {

    @Key("isRemote")
    @DefaultValue("false")
    Boolean isRemote();

    @Key("username")
    String getUsername();

    @Key("accessKey")
    String getAccessKey();

    @Key("app")
    String getApp();

    @Key("deviceName")
    @DefaultValue("Pixel_4")
    String getDeviceName();

    @Key("platformVersion")
    @DefaultValue("11.0")
    String getPlatformVersion();

    @Key("projectName")
    String getProjectName();

    @Key("buildName")
    String getBuildName();

    @Key("sessionName")
    String getSessionName();

    @Key("remoteUrl")
    @DefaultValue("http://localhost:4723/wd/hub")
    String getRemoteUrl();
}
