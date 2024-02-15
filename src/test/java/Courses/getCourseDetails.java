package Courses;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getCourseDetails
{
    @Test
    public void parseCourseJSON()
    {
        JsonPath jp = new JsonPath(Payload.getCoursePayload());
        int totalPrice =0;

        //print the number of courses from the above mock data
        int courseSize= jp.getInt("courses.size()");
        System.out.println("Number of courses in the list are: "+courseSize);

        //print the purchase amount
        int purchaseAmount = jp.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount for all courses is: "+purchaseAmount);

        //print all course titles and there prices
        for(int i=0 ; i<courseSize; i++)
        {
            String courseName = jp.getString("courses["+i+"].title");
            int noOfCopies = jp.getInt("courses["+i+"].copies");
            int coursePrice = jp.getInt("courses["+i+"].price");

            totalPrice = totalPrice + (coursePrice*noOfCopies);

            System.out.println("Course Name -->"+courseName+" and its price is -->"+coursePrice);
        }
        System.out.println("Total price for all courses -->"+totalPrice);

        //compare if purchase amount is equal to total price
        Assert.assertEquals(totalPrice,purchaseAmount);
    }
}