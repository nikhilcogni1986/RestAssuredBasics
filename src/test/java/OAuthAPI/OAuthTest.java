package OAuthAPI;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTest
{
    @Test
    public void Oauth_Test()
    {
        //form parameters
        //client_id: 692183103107-p0m7ent2hk7suguv4vq22hjcfhcr
        //client secret: erZOWM9g3UtwNRj340YYaK_W
        //grant_type: client_credentials
        //scope: trust
        //auth server: https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token
        //POST
        String response = given().
                formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .log().all()
                .when()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        System.out.println(js.getString("access_token"));
        String generatedAccessToken = js.getString("access_token");
        //Get course details
        //https:rahulshettyacademy.com/oauthapi/getCourseDetails
        //Get
        //Query Param: access_token:

        String courseResponse = given().
                queryParam("access_token",generatedAccessToken)
                .when().log().all()
                .get("https:rahulshettyacademy.com/oauthapi/getCourseDetails")
                .asString();
        System.out.println(courseResponse);
    }
}
