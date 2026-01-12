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

    private final SelenideElement permissionDenyButton =
            $(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button"));

    private final SelenideElement allowButton =
            $(AppiumBy.id("android:id/button1"));

    private final SelenideElement searchContainer =
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container"));

    private final SelenideElement searchInput =
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));

    private final ElementsCollection searchResults =
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));

    private final SelenideElement firstResult = searchResults.first();


    @Step("Android: Найти статью '{query}'")
    public void executeSearch(String query) {
        if (permissionDenyButton.is(visible)) {
            permissionDenyButton.click();
        }

        if (allowButton.is(visible)) {
            allowButton.click();
        }

        searchContainer.shouldBe(visible, Duration.ofSeconds(30)).click();
        searchInput.shouldBe(visible, Duration.ofSeconds(15)).sendKeys(query);
    }

    @Step("Android: Проверить результаты")
    public void checkResultsExist() {
        searchResults.shouldHave(sizeGreaterThan(0), Duration.ofSeconds(20));
    }

    @Step("Android: Открыть первый результат")
    public void clickFirstResult() {
        firstResult.click();
    }
}
