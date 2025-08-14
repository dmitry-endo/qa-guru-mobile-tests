package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import screens.MainScreen;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    MainScreen mainScreen = new MainScreen();
    SearchScreen searchScreen = new SearchScreen();

    @ValueSource(strings = {"Appium", "Selenide"})
    @ParameterizedTest(name = "Search query: {0}")
    @DisplayName("Search query should have results")
    void successfulSearchTest(String inputQuery) {
        step("Open the search and type: {0}", () -> {
            mainScreen.clickSearchIcon();
            searchScreen.enterSearchQuery(inputQuery);
        });

        step("Verify content found", searchScreen::shouldHaveResults);
    }

    @Test
    void passOnboardingScreenTest() {

    }
}
