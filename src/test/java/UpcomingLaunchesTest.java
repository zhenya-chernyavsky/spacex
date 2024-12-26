import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import com.example.core.Config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpcomingLaunchesTest {

    @Test
    @Step("Получить предстоящие запуски")
    public void testGetUpcomingLaunches() {
        RestAssured.baseURI = "https://api.spacexdata.com/v5/";
        given()
                .when()
                .get("launches/upcoming")
                .then()
                .statusCode(200)
                .body(is(not(emptyArray())))
                .body("[0].name", is("USSF-44"))
                .body("[0].date_utc", is("2022-11-01T13:41:00.000Z")); // свежих дат нет, или не нашел)
    }
}