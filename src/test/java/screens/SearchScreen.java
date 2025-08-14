package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;

public class SearchScreen {

    private final SelenideElement searchInput = $(id("org.wikipedia.alpha:id/search_src_text"));
//    private final ElementsCollection resultsCollection = $$(id("android.widget.TextView"));
    private final ElementsCollection resultsCollection = $$(id("org.wikipedia.alpha:id/search_results_list"));

    public void enterSearchQuery(String query) {
        searchInput.sendKeys(query);
    }

    public void shouldHaveResults() {
        resultsCollection.shouldHave(sizeGreaterThan(0));
//        $$(className("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
    }
}