package PostmanAPI;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchers
{
    @Test
    public void validate_using_contains()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                body("workspaces.name",contains("My Workspace","Team Workspace","myFirstWorkspace"));
    }

    @Test
    public void validate_using_containsInOrder()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                //have shuffled the order of workspace
                body("workspaces.name",containsInAnyOrder("Team Workspace","My Workspace","myFirstWorkspace"));
    }

    @Test
    public void validate_using_hasItem()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                //have shuffled the order of workspace
               body("workspaces.name",hasItem("Team Workspace"),
       "workspaces.name", hasItems("Team Workspace","My Workspace","myFirstWorkspace"));
    }

    @Test
    public void validate_using_isEmpty()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                //have shuffled the order of workspace
                        body("workspaces.name",is(not(empty())),
                        "workspaces.name", is(not(emptyArray())));
    }

    @Test
    public void validate_using_everyItemStartsWith()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                //have shuffled the order of workspace
                        body("workspaces.name", everyItem(startsWith("My")));
    }

    @Test
    public void validate_using_hashMapMethods()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                when().get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                //have shuffled the order of workspace
                        body("workspaces[0]",hasKey("name"),
                        "workspaces[0]", hasValue("My Workspace"),
                        "workspaces[0]", hasEntry("id","ae9fdbd2-ea34-474c-8108-62687bd9ab39"));
    }
}