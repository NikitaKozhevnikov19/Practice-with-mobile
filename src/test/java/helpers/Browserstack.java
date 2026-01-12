package helpers;

import tests.TestBase;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static String videoUrl(String sessionId) {
        String url = String.format("api.browserstack.com", sessionId);

        String user = TestBase.deviceHost.equals("ios") ? TestBase.iosConfig.bsUser() : TestBase.androidConfig.bsUser();
        String key = TestBase.deviceHost.equals("ios") ? TestBase.iosConfig.bsKey() : TestBase.androidConfig.bsKey();

        return given()
                .auth().basic(user, key)
                .get(url)
                .then()
                .log().all() // Добавлено для отладки в Jenkins (можно убрать после настройки)
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
