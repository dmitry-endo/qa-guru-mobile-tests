package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;

public class SearchPage {

    private final SelenideElement searchInput = $(id("org.wikipedia.alpha:id/search_src_text"));

    public void enterSearchQuery(String query) {
        searchInput.sendKeys(query);
    }

    public void shouldHaveResults() {
        $$(className("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
    }
}