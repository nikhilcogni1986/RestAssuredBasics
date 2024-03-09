package HashmapToJSON;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateNestedJsonFromMap
{
    @Test
    public void create_payload_from_map()
    {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("id","101");
        payload.put("first_name","Prakash");
        payload.put("last_name","Mehra");
        payload.put("Salary","123.45");

        //Address is a nested JSON Object
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put("Hno",98);
        addressMap.put("city","BLR");
        addressMap.put("state","KA");
        payload.put("address", addressMap);

        RestAssured.
                    given()
                    .log().all()
                    .body(payload).get();
    }
}