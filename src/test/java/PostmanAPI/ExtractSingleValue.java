package PostmanAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ExtractSingleValue {

    @Test
    public void extract_response_Using_Gpath() {
        Response res = (Response) given().baseUri("https://api.getpostman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract();
        System.out.println("Response is => "+res.path("workspaces[0].name"));
    }

    @Test
    public void extract_response_Using_jsonpath() {
        String res = given().baseUri("https://api.getpostman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().asString();
        JsonPath js = new JsonPath(res);
        System.out.println(js.getString("workspaces[0].name"));
        System.out.println("Number of Objects in the JSON: "+js.getInt("workspaces.size()"));
    }

    @Test
    public void extract_response_Using_jsonpath_from() {
        String res = given().baseUri("https://api.getpostman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().response().asString();
        System.out.println(JsonPath.from(res).getString("workspaces[0].name"));
        System.out.println("Number of Objects in the JSON: "+JsonPath.from(res).getInt("workspaces.size()"));
    }
}
