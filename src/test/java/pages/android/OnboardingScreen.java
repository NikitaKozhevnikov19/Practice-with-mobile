package pages.android;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OnboardingScreen {


    private final String PRIMARY_TEXT = "org.wikipedia:id/primaryTextView";
    private final String FORWARD_BUTTON = "org.wikipedia:id/fragment_onboarding_forward_button";
    private final String DONE_BUTTON = "org.wikipedia:id/fragment_onboarding_done_button";
    private final String MAIN_SCREEN_WORDMARK = "org.wikipedia:id/main_toolbar_wordmark";

    private final SelenideElement primaryTextView = $(AppiumBy.id(PRIMARY_TEXT));
    private final SelenideElement forwardButton = $(AppiumBy.id(FORWARD_BUTTON));
    private final SelenideElement doneButton = $(AppiumBy.id(DONE_BUTTON));
    private final SelenideElement mainWordmark = $(AppiumBy.id(MAIN_SCREEN_WORDMARK));

    @Step("Проверить заголовок и нажать 'Continue' на экране onboarding")
    public void swipeForward() {
        primaryTextView.shouldBe(visible);
        forwardButton.shouldBe(visible).click();
    }

    @Step("Нажать 'get started' на последнем экране onboarding")
    public void finishOnboarding() {
        primaryTextView.shouldBe(visible);
        doneButton.shouldBe(visible).click();
    }

    @Step("Проверить, что основной экран Wikipedia открылся")
    public void checkMainScreen() {
        mainWordmark.shouldBe(visible);
    }
}
