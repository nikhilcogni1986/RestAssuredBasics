package LibraryAPI;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBookWithDataProvider
{
    @Test(dataProvider = "booksData")
    public void add_book_using_post(String isbn, String aisle )
    {
        RestAssured.baseURI ="http://216.10.245.166";
        String response = given().
                header("Content-Type","application/json")
                .body(Payload.getBookDetails(isbn,aisle))
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

    @DataProvider(name="booksData")
    public Object[][] getBooksData()
    {
        Faker faker = new Faker();
        return new Object[][] {{faker.name().lastName(),faker.idNumber().valid().split("-")[0]},
                {faker.name().lastName(),faker.idNumber().valid().split("-")[0]}};
    }
}