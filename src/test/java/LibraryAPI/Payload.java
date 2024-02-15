package LibraryAPI;

public class Payload
{
    public static String getBookDetailsPayload()
    {
        return "{\n" +
                "    \"name\": \"Learn Appium Automation with Java\",\n" +
                "    \"isbn\": \"scd\",\n" +
                "    \"aisle\": \"211\",\n" +
                "    \"author\": \"ANN foe\"\n" +
                "}";
    }

    public static String getBookDetails(String isbn, String aisle)
    {
        return "{\n" +
                "    \"name\": \"Learn Appium Automation with Java\",\n" +
                "    \"isbn\": \""+isbn+"\",\n" +
                "    \"aisle\": \""+aisle+"\",\n" +
                "    \"author\": \"ANN foe\"\n" +
                "}";
    }
}
