package HashmapToJSON;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreateNestedJsonArrayFromMap
{
    @Test
    public void create_payload_from_map()
    {
        Map<String, Object> emp1Details = new LinkedHashMap<>();
        emp1Details.put("id","101");
        emp1Details.put("first_name","Prakash");
        emp1Details.put("last_name","Mehra");
        emp1Details.put("Salary","123.45");

        Map<String, Object> emp2Details = new LinkedHashMap<>();
        emp2Details.put("id","2");
        emp2Details.put("first_name","De");
        emp2Details.put("last_name","Worcester");
        emp2Details.put("Salary","1256");

        List<Map<String, Object>> allEmployees = new ArrayList<>();
        allEmployees.add(emp1Details);
        allEmployees.add(emp2Details);

        RestAssured.
                    given()
                    .log().all()
                    .body(allEmployees).get();
    }
}