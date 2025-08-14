package screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class GettingStartedScreen {

    private final SelenideElement primaryTextView = $(id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement onboardingWrapper = $(id("org.wikipedia.alpha:id/fragment_onboarding_pager_container"));
    private final SelenideElement skipButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));
    private final SelenideElement continueButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final SelenideElement getStartedButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));

    public void primaryTextShouldHaveExpectedValue(String value) {
        primaryTextView.shouldHave(Condition.text(value));
    }

    public void skipOnboardingIfPresent() {
        if (onboardingWrapper.exists()) {
            skipButton.shouldBe(Condition.visible).click();
        }
    }

    public void clickContinue() {
        continueButton.shouldBe(Condition.visible).click();
    }

    public void clickGetStarted() {
        getStartedButton.shouldBe(Condition.visible).click();
    }
}
