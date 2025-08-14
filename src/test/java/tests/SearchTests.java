package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import screens.OnboardingScreen;
import screens.MainScreen;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;

@Tag("search_tests")
@Owner("dmitry_endo")
@DisplayName("Wikipedia android app tests")
public class SearchTests extends TestBase {

    MainScreen mainScreen = new MainScreen();
    SearchScreen searchScreen = new SearchScreen();
    OnboardingScreen onboardingScreen = new OnboardingScreen();

    @ValueSource(strings = {"Appium", "Selenide"})
    @ParameterizedTest(name = "Search query: {0}")
    @DisplayName("Search query without mistypes should have results")
    void successfulWikipediaSearchTest(String inputQuery) {
        step("Skip onboarding before test if present", onboardingScreen::skipOnboardingIfPresent);

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
        step("Skip onboarding before test if present", onboardingScreen::skipOnboardingIfPresent);

        step("Open the search and type: {0}", () -> {
            mainScreen.clickSearchIcon();
            searchScreen.enterSearchQuery(inputQuery);
        });

        step("Verify no content found", searchScreen::shouldHaveNoResults);
    }
}
