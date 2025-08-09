package helpers;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public class Attachments {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(AndroidDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page Source", type = "text/xml")
    public static String attachPageSource(AndroidDriver driver) {
        return driver.getPageSource();
    }

    @Attachment(value = "Device Logs", type = "text/plain")
    public static String attachLogs(String logs) {
        return logs;
    }

    @Attachment(value = "BrowserStack video", type = "text/html", fileExtension = ".html")
    public static String addVideo(String sessionId, String name, String pass) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + VideoLoader.videoUrl(sessionId, name, pass)
                + "' type='video/mp4'></video></body></html>";
    }
}
