package LibraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBook
{
    @Test
    public void add_book_using_post()
    {
        RestAssured.baseURI ="http://216.10.245.166";
        String response = given().
                header("Content-Type","application/json")
                .body(Payload.getBookDetailsPayload())
                .when()
                .post("/Library/Addbook.php")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        Assert.assertEquals(js.get("Msg"),"successfully added");
    }
}
