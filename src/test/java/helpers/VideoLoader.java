package helpers;

import static io.restassured.RestAssured.given;

public class VideoLoader {

    public static String videoUrl(String sessionId, String name, String pass) {

        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(name, pass)
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
