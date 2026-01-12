package helpers;

import tests.TestBase;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static String videoUrl(String sessionId) {
        String url = String.format("api.browserstack.com", sessionId);

        String user = TestBase.deviceHost.equals("ios") ? TestBase.iosConfig.user() : TestBase.androidConfig.user();
        String key = TestBase.deviceHost.equals("ios") ? TestBase.iosConfig.key() : TestBase.androidConfig.key();

        return given()
                .auth().basic(user, key)
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
