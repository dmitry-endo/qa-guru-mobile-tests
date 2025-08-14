package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DeviceConfig;
import config.ConfigReader;
import drivers.BrowserStackDriver;
import drivers.LocalDriver;
import helpers.Attachments;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final DeviceConfig config = ConfigReader.getDeviceConfig();

    @BeforeAll
    static void baseConfigSetup() {
        if (config.isRemote()) {
            Configuration.browser = BrowserStackDriver.class.getName();
        } else {
            Configuration.browser = LocalDriver.class.getName();
        }
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    void allureListenerSetup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void shutdown() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();

        Attachments.attachScreenshot(driver);
        Attachments.attachPageSource(driver);
        Attachments.attachLogs("Test finished on device: " +
                driver.getCapabilities().getCapability("deviceName"));

        if (config.isRemote()) {
            String sessionId = driver.getSessionId().toString();
            Attachments.addVideo(sessionId,
                    config.getUsername(),
                    config.getAccessKey());
        }
        closeWebDriver();
    }
}
