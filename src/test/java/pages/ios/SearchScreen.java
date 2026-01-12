package pages.ios;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SearchScreen {

    private final SelenideElement uiElementsButton = $(AppiumBy.accessibilityId("UI Elements"));
    private final SelenideElement textButton = $(AppiumBy.accessibilityId("Text Button"));
    private final SelenideElement textInput = $(AppiumBy.accessibilityId("Text Input"));
    private final SelenideElement backButton = $(AppiumBy.accessibilityId("Back"));

    @Step("iOS: Перейти в раздел UI Elements")
    public void clickUIElements() {
        uiElementsButton.shouldBe(visible, Duration.ofSeconds(20)).click();
    }

    @Step("iOS: Проверить наличие кнопки Text Button")
    public void checkTextButtonVisible() {
        textButton.shouldBe(visible, Duration.ofSeconds(15));
    }

    @Step("iOS: Нажать на Text Button")
    public void clickTextButton() {
        textButton.click();
    }

    @Step("iOS: Проверить, что поле ввода текста отобразилось")
    public void checkTextInputVisible() {
        textInput.shouldBe(visible, Duration.ofSeconds(10));
    }

    @Step("iOS: Вернуться на главный экран")
    public void goBack() {
        if (backButton.isDisplayed()) {
            backButton.click();
        }
    }
}
