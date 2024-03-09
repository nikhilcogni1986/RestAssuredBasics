package HashmapToJSON;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateJsonFromMap
{
    @Test
    public void create_payload_from_map()
    {
        Map<String, String> payload = new HashMap<>();
        payload.put("id","101");
        payload.put("first_name","Prakash");
        payload.put("last_name","Mehra");
        payload.put("Salary","123.45");

        RestAssured.
                    given()
                    .log().all()
                    .body(payload).get();
    }

    @Test
    public void create_payload_from_map_using_generics()
    {
        //using generics allows any object values
        //Using linked Hashmap allows in maintaining order when JSON is received.
        Map<Object, Object> payload = new LinkedHashMap<>();
        payload.put("id",101);
        payload.put("first_name","Prakash");
        payload.put("last_name","Mehra");
        payload.put("Salary",123.45);

        RestAssured.
                given()
                .log().all()
                .body(payload).get();
    }
}