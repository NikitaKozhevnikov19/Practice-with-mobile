package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.android.OnboardingScreen;
import static com.codeborne.selenide.Selenide.open;

@Tag("android")
public class OnboardingTests extends TestBase {

    OnboardingScreen onboarding = new OnboardingScreen();

    @Test
    @Owner("Kozherka")
    @DisplayName("Android: Проход onboarding Wikipedia")
    void wikipediaOnboardingTest() {
        open();
        onboarding.swipeForward();
        onboarding.swipeForward();
        onboarding.swipeForward();
        onboarding.finishOnboarding();
        onboarding.checkMainScreen();
    }
}
