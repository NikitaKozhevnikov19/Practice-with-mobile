package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Tag("ios_bs")
public class IosTests extends TestBase {
    pages.ios.SearchScreen screen = new pages.ios.SearchScreen();

    @Test
    @Owner("Kozherka")
    @DisplayName("iOS: Проверка перехода в UI Elements")
    void checkUiElementsTest() {
        open();
        screen.clickUIElements();
        screen.checkTextButtonVisible();
    }

    @Test
    @Owner("Kozherka")
    @DisplayName("iOS: Проверка возврата на главный экран")
    void testBackNavigation() {
        open();
        screen.clickUIElements();
        screen.goBack();
        screen.clickUIElements();
    }
}
