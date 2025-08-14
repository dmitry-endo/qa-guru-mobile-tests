package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import screens.GettingStartedScreen;
import screens.MainScreen;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;

public class WikipediaTests extends TestBase {

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
    @DisplayName("Successful pass Getting Started screen test")
    void passGettingStartedScreenTest() {
        String[] expectedTexts = {
                "The Free Encyclopedia\n…in over 300 languages",
                "New ways to explore",
                "Reading lists with sync",
                "Data & Privacy"
        };

        for (int i = 0; i < expectedTexts.length; i++) {
            int stepIndex = i; // effectively final for lambda
            step("Check onboarding screen text: " + expectedTexts[stepIndex], () -> {
                gettingStartedScreen.primaryTextShouldHaveExpectedValue(expectedTexts[stepIndex]);
            });

            if (i < expectedTexts.length - 1) {
                step("Click Continue", gettingStartedScreen::clickContinue);
            } else {
                step("Click Get Started", gettingStartedScreen::clickGetStarted);
            }
        }

        step("Verify that main screen has opened", mainScreen::verifyMainScreenHasOpened);
    }
}
