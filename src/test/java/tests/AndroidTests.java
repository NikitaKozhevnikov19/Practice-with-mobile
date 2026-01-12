package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("android")
public class AndroidTests extends TestBase {
    pages.android.SearchScreen screen = new pages.android.SearchScreen();

    @Test
    @Owner("Kozherka")
    @DisplayName("Android: Проверка поиска в Wikipedia")
    void androidSearchTest() {
        screen.executeSearch("Appium");
        screen.checkResultsExist();
    }

    @Test
    @Owner("Kozherka")
    @DisplayName("Android: Открытие статьи в Wikipedia")
    void androidOpenArticleTest() {
        screen.executeSearch("Java");
        screen.clickFirstResult();
        screen.checkArticleOpened();
    }
}
