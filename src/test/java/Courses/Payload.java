package Courses;

public class Payload
{
    public static String getCoursePayload()
    {
        return "{\n" +
                "  \"dashboard\":\n" +
                "  {\n" +
                "    \"purchaseAmount\": 910,\n" +
                "    \"website\":\"http://www.google.com\"\n" +
                "  },\n" +
                "  \"courses\":\n" +
                "  [\n" +
                "      {\n" +
                "        \"title\":\"Selenium Python\",\n" +
                "        \"price\":200,\n" +
                "        \"copies\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\":\"Selenium JAVA\",\n" +
                "        \"price\":100,\n" +
                "        \"copies\": 3\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\":\"Selenium Python\",\n" +
                "        \"price\":105,\n" +
                "        \"copies\": 2\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }
}
