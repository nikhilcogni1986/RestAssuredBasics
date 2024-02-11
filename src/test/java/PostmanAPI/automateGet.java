package PostmanAPI;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class automateGet {
    @Test
    public void automate_get_status_code() {
        given().baseUri("https://api.getpostman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().
                get("/workspaces").
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void validate_response_body() {
            given().baseUri("https://api.getpostman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("My Workspace", "Team Workspace"),
                            "workspaces[0].name",is(equalTo("My Workspace")),
                            "workspaces.size", is(equalTo(3)));

    }

    @Test
    public void extract_response() {
        Response res = (Response) given().baseUri("https://api.getpostman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract();
        System.out.println("Response is => "+res.asString());
    }
}
