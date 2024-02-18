package GoogleAPI;

import GoogleAPI.Payload.PayloadData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlace {
    @Test
    public void add_place_using_post() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().
                queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(PayloadData.getPlaceDetails())
                .when().
                post("maps/api/place/add/json")
                .then().log().all().
                assertThat()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json;charset=UTF-8"))
                .extract()
                .response().asString();
        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeID =js.get("place_id");
        System.out.println(placeID);

        //Put API call to modify the address
        String addrToBeModified = "City Road street";

        given().header("Content-Type","application/json")
                .body(PayloadData.putAddrDetails(placeID, addrToBeModified))
                .when().put("maps/api/place/update/json")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Get call to fetch the details of the place ID created above
        String getPlaceDetails = given().queryParam("key","qaclick123")
                .queryParam("place_id", placeID)
                .when()
                .get("maps/api/place/get/json")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println(getPlaceDetails);
        JsonPath js2 = new JsonPath(getPlaceDetails);
        String updatedAddr = js2.get("address");

        Assert.assertTrue(updatedAddr.equalsIgnoreCase(addrToBeModified));
    }
}
