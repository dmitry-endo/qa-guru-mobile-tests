package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import screens.GettingStartedScreen;
import screens.MainScreen;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    MainScreen mainScreen = new MainScreen();
    SearchScreen searchScreen = new SearchScreen();
    GettingStartedScreen gettingStartedScreen = new GettingStartedScreen();

    @ValueSource(strings = {"Appium", "Selenide"})
    @ParameterizedTest(name = "Search query: {0}")
    @DisplayName("Search query should have results")
    void successfulWikipediaSearchTest(String inputQuery) {
        step("Open the search and type: {0}", () -> {
            mainScreen.clickSearchIcon();
            searchScreen.enterSearchQuery(inputQuery);
        });

        step("Verify content found", searchScreen::shouldHaveResults);
    }

    @Test
    void passGettingStartedWikipediaScreenTest() {

        gettingStartedScreen
                .primaryTextShouldHaveExpectedValue("The Free Encyclopedia\n…in over 300 languages")
                .clickContinue();

        gettingStartedScreen
                .primaryTextShouldHaveExpectedValue("New ways to explore")
                .clickContinue();

        gettingStartedScreen
                .primaryTextShouldHaveExpectedValue("Reading lists with sync")
                .clickContinue();

        gettingStartedScreen
                .primaryTextShouldHaveExpectedValue("Data & Privacy")
                .clickGetStarted();
    }
}
