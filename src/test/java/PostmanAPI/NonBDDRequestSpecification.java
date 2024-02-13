package PostmanAPI;

import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class NonBDDRequestSpecification
{
    RequestSpecification requestSpecification;

    @BeforeClass
    public void initialize()
    {
        requestSpecification  = given().
                baseUri("https://api.postman.com").
                config(config().logConfig(LogConfig.logConfig().blacklistHeader("X-Api-key"))).
                headers("X-Api-key", "PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                log().all();
    }

    @Test
    public void validate_status_code_NonBDD()
    {
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
        assertThat(response.path("workspaces[0].name"),equalTo("My Workspace"));
    }
}
