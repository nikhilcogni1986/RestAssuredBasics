package Jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest
{
    @Test
    public void login_to_Jira_Post()
    {
        SessionFilter loginSession = new SessionFilter();
        String response = RestAssured.baseURI = "http://localhost:8080";
        given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"username\": \"nikhilcogni\",\n" +
                        "    \"password\": \"Password1234\"\n" +
                        "}")
                .when()
                .filter(loginSession)
                .post("/rest/auth/1/session")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response();
        System.out.println(response);

        given()
                .pathParam("key","10100")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"body\": \"This is a comment that only administrators can see.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .when()
                .filter(loginSession)
                .post("/rest/api/2/issue/{key}/comment")
                .then()
                .assertThat()
                .statusCode(201);

    }
}
