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

import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final DeviceConfig config = ConfigReader.getDeviceConfig();

    @BeforeAll
    static void baseConfigSetup() {
//        switch (config.getEnv()) {
//            case "local":
//                Configuration.browser = LocalDriver.class.getName();
//            case "browserStack":
//                Configuration.browser = BrowserStackDriver.class.getName();
//        }

        if (Objects.equals(config.getEnv(), "local")) {
            Configuration.browser = LocalDriver.class.getName();
        } else {
            Configuration.browser = BrowserStackDriver.class.getName();
        }

//        Configuration.browser = BrowserStackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    void allureListenerSetup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();

        Attachments.attachScreenshot(driver);
        Attachments.attachPageSource(driver);
        Attachments.attachLogs("Test finished on device: " +
                driver.getCapabilities().getCapability("deviceName"));

//        String sessionId = driver.getSessionId().toString();
//        Attachments.addVideo(sessionId,
//                config.getUsername(),
//                config.getAccessKey());
        closeWebDriver();
    }
}
