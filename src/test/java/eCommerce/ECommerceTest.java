package eCommerce;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceTest
{
    @Test
    public void end_to_end_place_order_test()
    {
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();

        //Create the payload using Login class
        Login l1 = new Login();
        l1.setUserEmail("nikhilrao@test.com");
        l1.setUserPassword("Password1234");

        //Login to Application and fetch the access token
        LoginResponse loginResponse = given().spec(req).log().all().body(l1)
                .when().post("/api/ecom/auth/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().as(LoginResponse.class);

        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();
        System.out.println("Token: "+token);
        System.out.println("UserID: "+userId);

        //create the product
        RequestSpecification addProductReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        String addProdResponse = given().spec(addProductReq)
                .param("productName","Samsung Galaxy M52")
                .param("productAddedBy",userId)
                .param("productCategory","Electronics")
                .param("productSubCategory","Mobiles")
                .param("productPrice","21000")
                .param("productDescription","Samsung Galaxy M52")
                .param("productFor","SmartPhone")
                .multiPart("productImage",new File(System.getProperty("user.dir")+"\\src\\test\\resources\\samsung phone.png"))
                .log().all()
                .when()
                .post("/api/ecom/product/add-product")
                .then().log().all()
                .assertThat().statusCode(201).extract().response().asString();

        //Extract the Product ID
        JsonPath js1 = new JsonPath(addProdResponse);
        String productID = js1.get("productId");
        System.out.println("Product created and its ID is: "+productID);

        // Place the order
        RequestSpecification placeOrderReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();

        Orders O1 = new Orders();
        OrderDetails OD1 = new OrderDetails();
        List<OrderDetails> orderDetails = new ArrayList<>();

        OD1.setCountry("India");
        OD1.setProductOrderId(productID);
        orderDetails.add(OD1);
        O1.setOrderdetails(orderDetails);

        String placeResponse = given().spec(placeOrderReq).log().all()
                .body(O1)
                .when()
                .post("/api/ecom/order/create-order")
                .then()
                .log().all()
                .extract().response().asString();
        System.out.println("Place Order Response: "+placeResponse);

        //Delete the product
        RequestSpecification deleteOrderReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        String deleteResponse = given().spec(deleteOrderReq).log().all()
                .pathParam("productId",productID)
                .when()
                .delete("/api/ecom/product/delete-product/{productId}")
                .then()
                .log().all()
                .extract().response().asString();
        System.out.println("Delete Order Response: "+deleteResponse);

        //Validate whether product is deleted
        JsonPath js2 = new JsonPath(deleteResponse);
        String deleteMsg = js2.get("message");
        Assert.assertEquals(deleteMsg,"Product Deleted Successfully");
    }
}