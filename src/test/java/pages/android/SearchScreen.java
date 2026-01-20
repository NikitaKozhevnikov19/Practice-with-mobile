package pages.android;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchScreen {

    // Универсальные ID (без указания пакета org.wikipedia)
    private final SelenideElement skipButton =
            $(AppiumBy.id("fragment_onboarding_skip_button"));

    private final SelenideElement permissionDenyButton =
            $(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button"));

    private final SelenideElement allowButton =
            $(AppiumBy.id("android:id/button1"));

    private final SelenideElement searchContainer =
            $(AppiumBy.id("search_container"));

    private final SelenideElement searchInput =
            $(AppiumBy.id("search_src_text"));

    private final ElementsCollection searchResults =
            $$(AppiumBy.id("page_list_item_title"));

    private final SelenideElement firstResult = searchResults.first();

    private final SelenideElement articleTitle =
            $(AppiumBy.className("android.widget.TextView"));

    @Step("Android: Найти статью '{query}'")
    public void executeSearch(String query) {
        // Ждем появления кнопки Skip (onboarding) или контейнера поиска
        if (skipButton.is(visible)) {
            skipButton.click();
        }

        if (permissionDenyButton.is(visible)) {
            permissionDenyButton.click();
        }

        if (allowButton.is(visible)) {
            allowButton.click();
        }

        searchContainer.shouldBe(visible, Duration.ofSeconds(30)).click();

        // Для стабильности вводим текст в активное поле
        searchInput.shouldBe(visible, Duration.ofSeconds(15)).sendKeys(query);
    }

    @Step("Android: Проверить наличие результатов поиска")
    public void checkResultsExist() {
        searchResults.shouldHave(sizeGreaterThan(0), Duration.ofSeconds(20));
    }

    @Step("Android: Открыть первый результат в списке")
    public void clickFirstResult() {
        firstResult.shouldBe(visible).click();
    }

    @Step("Android: Проверить, что статья успешно открылась")
    public void checkArticleOpened() {
        articleTitle.shouldBe(visible, Duration.ofSeconds(30));
    }
}
