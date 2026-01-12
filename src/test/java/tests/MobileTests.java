package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("mobile")
public class MobileTests extends TestBase {

    // -------------------- ANDROID --------------------
    @Test
    @Owner("Kozherka")
    @DisplayName("Android: Проверка поиска в Wikipedia")
    void androidSearchTest() {
        if (!deviceHost.equals("android")) return;

        String query = "Appium";
        pages.android.SearchScreen screen = new pages.android.SearchScreen();

        screen.executeSearch(query);
        screen.checkResultsExist();
    }

    @Test
    @Owner("Kozherka")
    @DisplayName("Android: Открытие статьи в Wikipedia и проверка заголовка")
    void androidOpenArticleTest() {
        if (!deviceHost.equals("android")) return;

        String query = "Java";
        pages.android.SearchScreen screen = new pages.android.SearchScreen();

        screen.executeSearch(query);
        screen.clickFirstResult();
        screen.checkArticleOpened();
    }

    // -------------------- IOS --------------------
    @Test
    @Owner("Kozherka")
    @DisplayName("iOS: Проверка перехода в UI Elements и наличия кнопок")
    void checkUiElementsTest() {
        if (!deviceHost.equals("ios")) return;

        pages.ios.SearchScreen screen = new pages.ios.SearchScreen();

        screen.clickUIElements();
        screen.checkTextButtonVisible();
        screen.clickTextButton();
        screen.checkTextInputVisible();
    }

    @Test
    @Owner("Kozherka")
    @DisplayName("iOS: Проверка возврата на главный экран")
    void testBackNavigation() {
        if (!deviceHost.equals("ios")) return;

        pages.ios.SearchScreen screen = new pages.ios.SearchScreen();

        screen.clickUIElements();
        screen.goBack();
        screen.clickUIElements();
    }
}
