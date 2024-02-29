package Jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

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
                        "    \"username\": \"XXXXX\",\n" +
                        "    \"password\": \"XXXXXX\"\n" +
                        "}")
                .when()
                .filter(loginSession)
                .post("/rest/auth/1/session")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
        System.out.println(response);

        String comment = "This is a validation for comments for an issue";

        String commentResponse = given()
                .pathParam("key","10201")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"body\": \""+comment+"\",\n" +
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
                .statusCode(201)
                .extract().response().asString();

        JsonPath js1 = new JsonPath(commentResponse);
        String commentID = js1.getString("id");
        System.out.println("ID for added comment -->"+commentID);

        //Add an attachment to an issue
        given()
                .header("X-Atlassian-Token","nocheck")
                .filter(loginSession)
                .pathParam("key","10201")
                .header("Content-Type","multipart/form-data")
                .multiPart("file",new File("10200,SS.txt"))
                .log().all()
                .when()
                .post("/rest/api/2/issue/{key}/attachments")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

        //Get the issue details
        String getIssueResponse = given()
                .filter(loginSession)
                .pathParam("key","10201")
                .queryParam("fields","comment")
                .when()
                .get("/rest/api/2/issue/{key}")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println(getIssueResponse);

        JsonPath js2 = new JsonPath(getIssueResponse);
        int commentCount = js2.getInt("fields.comment.comments.size()");

        for(int i=0; i< commentCount; i++)
        {
            String commentIDIssue = js2.get("fields.comment.comments["+i+"]").toString();
            System.out.println(commentIDIssue);

            if(commentIDIssue.equalsIgnoreCase(commentID))
            {
                String actualComment = js2.get("fields.comment.comments["+i+"].body");
                System.out.println(actualComment);
                Assert.assertEquals(actualComment,comment);
            }
        }
    }
}
