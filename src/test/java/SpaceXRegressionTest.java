import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static com.example.core.Config.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpaceXRegressionTest {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Description("Проверка всех запусков SpaceX")
    @Tag("regression")
    public void testGetAllLaunches() {
        given()
                .when()
                .get("launches")
                .then()
                .statusCode(200)
                .body(is(not(emptyArray())))
                .body("[0].name", is("FalconSat"))
                .body("[0].date_utc", is("2006-03-24T22:30:00.000Z"));
    }



    @Test
    public void testGetPastLaunches() {
        given()
                .when()
                .get("launches/past")
                .then()
                .statusCode(200)
                .body(is(not(emptyArray())))
                .body("[0].name", is("FalconSat"))
                .body("[0].success", is(anyOf(is(true), is(false))));
    }

    @Test
    public void testGetSpecificLaunch() {

        String launchId = "5eb87d09ffd86e000604b358";
        given()
                .when()
                .get("launches/" + launchId)
                .then()
                .statusCode(200)
                .body("name", is("Boeing X-37B OTV-5"))
                .body("date_utc", is("2017-09-07T13:50:00.000Z"))
                .body("rocket", is("5e9d0d95eda69973a809d1ec"));
    }
}
