package JSonSchemaValidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JsonSchemaTest {
    @Test
    public void validate_Json_Schema() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
        given().
                contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Tim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 101,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2017-01-01\",\n" +
                        "        \"checkout\" : \"2018-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log().all()
                .when()
                .post()
                .then().log().all ()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("bookerResponse.json"));
    }

    @Test
    public void validate_Json_Schema_Using_Filepath() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
        given().
                contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Tim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 101,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2017-01-01\",\n" +
                        "        \"checkout\" : \"2018-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .log().all()
                .when()
                .post()
                .then().log().all ()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir")+"//src//test//resources//bookerResponse.json")));
    }
}
