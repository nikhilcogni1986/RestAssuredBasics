package GoogleAPI.Payload;

public class PayloadData {
    public static String getPlaceDetails() {
        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Treharris street\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"Green park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String putAddrDetails(String placeID, String addrToBeModified) {
        return "{\n" +
                "    \"place_id\":\""+placeID+"\",\n" +
                "    \"address\": \""+addrToBeModified+"\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}";
    }
}
