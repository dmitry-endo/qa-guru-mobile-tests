package config;

import org.aeonbits.owner.Config;

@Config.Sources({
//        "classpath:config/${env}.properties",
//        "classpath:config/local.properties",
        "classpath:config/local.properties"
})
public interface DeviceConfig extends Config {

    @Key("env")
    @DefaultValue("local")
    String getEnv();

    @Key("username")
    @DefaultValue("${BROWSERSTACK_USERNAME}")
    String getUsername();

    @Key("accessKey")
    @DefaultValue("${BROWSERSTACK_ACCESS_KEY}")
    String getAccessKey();

    @Key("app")
    String getApp();

    @Key("deviceName")
    String getDeviceName();

    @Key("platformName")
    String getPlatformName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("projectName")
    String getProjectName();

    @Key("buildName")
    String getBuildName();

    @Key("sessionName")
    String getSessionName();

    @Key("remoteUrl")
    String getRemoteUrl();
}
