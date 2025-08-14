package screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;

public class MainScreen {

    private final SelenideElement mainPageWrapper = $(id("org.wikipedia.alpha:id/action_bar_root"));
    private final SelenideElement searchWikiWrapper = $(id("Search Wikipedia"));

    public void checkMainScreenOpened() {
        mainPageWrapper.shouldBe(Condition.visible);
    }

    public void clickSearchIcon() {
        searchWikiWrapper.click();
    }
}