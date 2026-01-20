package pages.android;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OnboardingScreen {


    private final SelenideElement primaryTextView =
            $(AppiumBy.xpath("//*[contains(@resource-id, 'primaryTextView')]"));

    private final SelenideElement forwardButton =
            $(AppiumBy.xpath("//*[contains(@resource-id, 'forward_button')]"));

    private final SelenideElement acceptButton =
            $(AppiumBy.xpath("//*[contains(@resource-id, 'acceptButton')]"));

    private final SelenideElement doneButton =
            $(AppiumBy.xpath("//*[contains(@resource-id, 'done_button')]"));

    private final SelenideElement mainWordmark =
            $(AppiumBy.xpath("//*[contains(@resource-id, 'main_toolbar_wordmark')]"));

    @Step("Проверить заголовок и нажать 'Continue' на экране onboarding")
    public void swipeForward() {
        primaryTextView.shouldBe(visible);
        forwardButton.shouldBe(visible).click();
    }

    @Step("Нажать на кнопку завершения на последнем экране onboarding")
    public void finishOnboarding() {
        primaryTextView.shouldBe(visible);
        // Универсальная логика: нажимаем либо Accept (для F-Droid), либо Done (для Alpha/Prod)
        if (acceptButton.is(visible)) {
            acceptButton.click();
        } else {
            doneButton.shouldBe(visible).click();
        }
    }

    @Step("Проверить, что основной экран Wikipedia открылся")
    public void checkMainScreen() {
        mainWordmark.shouldBe(visible);
    }
}
