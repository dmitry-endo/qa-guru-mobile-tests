package screens;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MainScreen {
    public void clickSearchIcon() {
        $(accessibilityId("Search Wikipedia")).click();
    }
}