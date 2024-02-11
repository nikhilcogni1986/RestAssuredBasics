package PostmanAPI;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationTest
{
    @Test
    public void validate_status_code()
    {
        RequestSpecification requestSpecification = given().
                baseUri("https://api.postman.com").
                header("X-Api-key","PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772");

        given().spec(requestSpecification).
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void extract_response() {
        RequestSpecification requestSpecification = given().
                baseUri("https://api.postman.com").
                header("X-Api-key","PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772");

        given().spec(requestSpecification).
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("My Workspace", "Team Workspace"),
                        "workspaces[0].name",is(equalTo("My Workspace")),
                        "workspaces.size", is(equalTo(3)));
    }
}