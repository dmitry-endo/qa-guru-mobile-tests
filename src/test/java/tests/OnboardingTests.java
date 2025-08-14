package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import screens.OnboardingScreen;
import screens.MainScreen;

import static io.qameta.allure.Allure.step;

@Tag("onboarding_tests")
@Owner("dmitry_endo")
@DisplayName("Wikipedia android app tests")
public class OnboardingTests {

    MainScreen mainScreen = new MainScreen();
    OnboardingScreen onboardingScreen = new OnboardingScreen();

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
                onboardingScreen.primaryTextShouldHaveExpectedValue(expectedTexts[stepIndex]);
            });

            if (i < expectedTexts.length - 1) {
                step("Click Continue", onboardingScreen::clickContinue);
            } else {
                step("Click Get Started", onboardingScreen::clickGetStarted);
            }
        }

        step("Verify that main screen has opened", mainScreen::verifyMainScreenHasOpened);
    }
}
