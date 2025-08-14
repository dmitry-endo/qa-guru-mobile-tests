package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import screens.GettingStartedScreen;
import screens.MainScreen;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;

@Owner("dmitry_endo")
@DisplayName("Wikipedia android app tests")
public class WikipediaTests extends TestBase {

    MainScreen mainScreen = new MainScreen();
    SearchScreen searchScreen = new SearchScreen();
    GettingStartedScreen gettingStartedScreen = new GettingStartedScreen();

    @ValueSource(strings = {"Appium", "Selenide"})
    @ParameterizedTest(name = "Search query: {0}")
    @DisplayName("Search query without mistypes should have results")
    void successfulWikipediaSearchTest(String inputQuery) {
        step("Skip onboarding before test if present", gettingStartedScreen::skipOnboardingIfPresent);

        step("Open the search and type: {0}", () -> {
            mainScreen.clickSearchIcon();
            searchScreen.enterSearchQuery(inputQuery);
        });

        step("Verify content found", searchScreen::shouldHaveResults);
    }

    @ValueSource(strings = {"agfvszdffdsgv", "ilahgnoij iuehngroa"})
    @ParameterizedTest(name = "Search query: {0}")
    @DisplayName("Gibberish search query should have no results")
    void unsuccessfulWikipediaSearchTest(String inputQuery) {
        step("Skip onboarding before test if present", gettingStartedScreen::skipOnboardingIfPresent);

        step("Open the search and type: {0}", () -> {
            mainScreen.clickSearchIcon();
            searchScreen.enterSearchQuery(inputQuery);
        });

        step("Verify no content found", searchScreen::shouldHaveNoResults);
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
