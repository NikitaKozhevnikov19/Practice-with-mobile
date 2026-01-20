package pages.android;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OnboardingScreen {

    private final SelenideElement primaryTextView = $(AppiumBy.id("primaryTextView"));
    private final SelenideElement forwardButton = $(AppiumBy.id("fragment_onboarding_forward_button"));
    private final SelenideElement acceptButton = $(AppiumBy.id("acceptButton"));
    private final SelenideElement doneButton = $(AppiumBy.id("fragment_onboarding_done_button"));
    private final SelenideElement mainWordmark = $(AppiumBy.id("main_toolbar_wordmark"));

    @Step("Проверить заголовок и нажать 'Continue'")
    public void swipeForward() {
        primaryTextView.shouldBe(visible);
        forwardButton.shouldBe(visible).click();
    }

    @Step("Нажать на кнопку завершения")
    public void finishOnboarding() {
        primaryTextView.shouldBe(visible);
        if (acceptButton.is(visible)) {
            acceptButton.click();
        } else {
            doneButton.shouldBe(visible).click();
        }
    }

    @Step("Проверить главный экран")
    public void checkMainScreen() {
        mainWordmark.shouldBe(visible);
    }
}
