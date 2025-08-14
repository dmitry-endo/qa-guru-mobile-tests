package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.DeviceConfig;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    private static final DeviceConfig config = ConfigReader.getDeviceConfig();

    @Override
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("app", config.getApp());
        caps.setCapability("deviceName", config.getDeviceName());
        caps.setCapability("platformName", config.getPlatformName());
        caps.setCapability("platformVersion", config.getPlatformVersion());
        caps.setCapability("browserstackLocal", true);

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", config.getUsername());
        bstackOptions.setCapability("accessKey", config.getAccessKey());
        bstackOptions.setCapability("projectName", config.getProjectName());
        bstackOptions.setCapability("buildName", config.getBuildName());
        bstackOptions.setCapability("sessionName", config.getSessionName());
        bstackOptions.setCapability("video", true);

        caps.setCapability("bstack:options", bstackOptions);
        try {
            return new AndroidDriver(new URL(config.getRemoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for BrowserStack", e);
        }
    }
}
