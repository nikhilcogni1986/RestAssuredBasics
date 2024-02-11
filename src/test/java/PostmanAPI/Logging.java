package PostmanAPI;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class Logging
{
    @Test
    public void log_at_request_and_response()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key","PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                log().all().
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void log_if_validationFails()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key","PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                log().ifValidationFails().
                when().
                get("/workspaces").
                then().
                log().ifValidationFails().
                assertThat().statusCode(201);
    }

    @Test
    public void log_blacklist_headers()
    {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-key","PMAK-6217db1b99c8737dca29d714-04ba0929f83569bd0d782e0d75ab006772").
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-key"))).
                log().all().
                when().
                get("/workspaces").
                then().
                log().ifValidationFails().
                assertThat().statusCode(200);
    }
}