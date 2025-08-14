package screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class GettingStartedScreen {

    private final SelenideElement primaryTextView = $(id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement continueButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final SelenideElement getStartedButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));

    public GettingStartedScreen primaryTextShouldHaveExpectedValue(String value) {
        primaryTextView.shouldHave(Condition.text(value));

        return this;
    }
    
    public void clickContinue() {
        continueButton.shouldBe(Condition.visible).click();
    }

    public void clickGetStarted() {
        getStartedButton.shouldBe(Condition.visible).click();
    }
}
