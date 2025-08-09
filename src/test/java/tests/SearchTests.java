package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import pages.SearchPage;

import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @ValueSource(strings = {"Appium", "Selenide"})
    @ParameterizedTest(name = "Search query: {0}")
    @DisplayName("Search query should have results")
    void successfulSearchTest(String inputQuery) {
        step("Open the search and type: {0}", () -> {
            mainPage.clickSearchIcon();
            searchPage.enterSearchQuery(inputQuery);
        });

        step("Verify content found", searchPage::shouldHaveResults);
    }
}
