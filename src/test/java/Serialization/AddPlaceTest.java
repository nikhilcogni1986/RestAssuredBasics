package Serialization;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlaceTest
{
    @Test
    public void add_place_using_serialization()
    {
        PlaceDetails P1 = new PlaceDetails();
        Location L1 = new Location();
        List<String> types = new ArrayList<>();

        L1.setLattitude(-38.383494);
        L1.setLongitude(33.427362);
        P1.setLocation(L1);

        P1.setAccuracy(50);
        P1.setName("Frontline house");
        P1.setPhone_number("(+91) 983 893 3937");
        P1.setAddress("29, side layout, cohen 09");

        types.add("shoe park");
        types.add("shop");
        P1.setTypes(types);

        P1.setWebsite("http://google.com");
        P1.setLanguage("French-IN");

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().
                queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(P1)
                .when().log().all().
                post("maps/api/place/add/json")
                .then().log().all().
                assertThat()
                .statusCode(200)
                .extract()
                .response().asString();
        System.out.println("Response:"+response);
    }
}