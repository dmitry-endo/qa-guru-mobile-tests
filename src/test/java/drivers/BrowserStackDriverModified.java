package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigReader;
import config.DeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class BrowserStackDriverModified implements WebDriverProvider {

    private static final DeviceConfig config = ConfigReader.getDeviceConfig();

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion(config.getPlatformVersion())
                .setDeviceName(config.getDeviceName())
                .setApp(config.getApp()) // BrowserStack app URL or ID
                .setAppPackage("org.wikipedia.alpha")
                .setAppActivity("org.wikipedia.main.MainActivity");

        // BrowserStack-specific options
        options.setCapability("bstack:options", getBrowserStackOptions());

        return new AndroidDriver(getBrowserStackUrl(), options);
    }

    private URL getBrowserStackUrl() {
        try {
            return new URL(config.getRemoteUrl()); // e.g., "https://USERNAME:ACCESS_KEY@hub.browserstack.com/wd/hub"
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack URL", e);
        }
    }

    private BrowserStackOptions getBrowserStackOptions() {
        BrowserStackOptions bstackOptions = new BrowserStackOptions();
        bstackOptions.setUserName(config.getUsername());
        bstackOptions.setAccessKey(config.getAccessKey());
        bstackOptions.setProjectName(config.getProjectName());
        bstackOptions.setBuildName(config.getBuildName());
        bstackOptions.setSessionName(config.getSessionName());
        bstackOptions.setVideo();
        return bstackOptions;
    }

    // A simple inner helper to avoid polluting DeviceConfig
    private static class BrowserStackOptions extends java.util.HashMap<String, Object> {
        void setUserName(String userName) { put("userName", userName); }
        void setAccessKey(String accessKey) { put("accessKey", accessKey); }
        void setProjectName(String projectName) { put("projectName", projectName); }
        void setBuildName(String buildName) { put("buildName", buildName); }
        void setSessionName(String sessionName) { put("sessionName", sessionName); }
        void setVideo() { put("video", true); }
    }

    private String getAppPath() {
        String appVersion = "app-alpha-universal-release.apk";
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia" +
                "/releases/download/latest/" + appVersion;
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}
