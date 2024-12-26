import io.restassured.RestAssured;
import org.testng.annotations.Test;
import com.example.core.Config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpaceXApiTest {

    @Test
    public void testGetLaunches() {
        RestAssured.baseURI = Config.LAUNCHES_ENDPOINT;

        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("size()", is(greaterThan(0)));
    }

    @Test
    public void testGetFirstLaunchName() {
        String expectedLaunchName = "FalconSat";
        RestAssured.baseURI = Config.PAST_LAUNCHES_ENDPOINT;
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("[0].name", is(expectedLaunchName));
    }
}
