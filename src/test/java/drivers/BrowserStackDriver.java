package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackConfig;
import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {
    private static final BrowserStackConfig config = ConfigReader.getBrowserStackConfig();

    @Override
    @Nonnull
    public WebDriver createDriver(org.openqa.selenium.Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("app", config.app());
        caps.setCapability("deviceName", config.deviceName());
        caps.setCapability("platformName", config.platformName());
        caps.setCapability("platformVersion", config.platformVersion());
        caps.setCapability("browserstackLocal", true);

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", config.username());
        bstackOptions.setCapability("accessKey", config.accessKey());
        bstackOptions.setCapability("projectName", config.projectName());
        bstackOptions.setCapability("buildName", config.buildName());
        bstackOptions.setCapability("sessionName", config.sessionName());
        bstackOptions.setCapability("video", true);

        caps.setCapability("bstack:options", bstackOptions);
        try {
            return new AndroidDriver(new URL(config.remoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for BrowserStack", e);
        }
    }
}
