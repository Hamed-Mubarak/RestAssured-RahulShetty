package files;

public class PayLoad {

    public static String addPlace()
    {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": 29.9891544,\n" +
                "    \"lng\": 31.3063091\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Hamed Home\",\n" +
                "  \"phone_number\": \"(+2)01090074666\",\n" +
                "  \"address\": \"113, Refaat Hassan St, Hadaba Wosta, Mukkatam\",\n" +
                "  \"types\": [\n" +
                "    \"Home\",\n" +
                "    \"Work\"\n" +
                "  ],\n" +
                "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                "  \"language\": \"Arabic\"\n" +
                "}\n";
    }

    public static String coursePrice()
    {
        return "{\n" +
                "  \"dashboard\" : {\n" +
                "    \"purchaseAmount\": 910,\n" +
                "    \"website\" : \"www.google.com\"\n" +
                "  },\n" +
                "\n" +
                "  \"courses\" :[\n" +
                "    {\n" +
                "      \"title\" : \"Selenium Java\",\n" +
                "      \"price\" : 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\" : \"Cypress\",\n" +
                "      \"price\" : 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\" : \"Shaft\",\n" +
                "      \"price\" : 45,\n" +
                "      \"copies\": 10\n" +
                "    }\n" +
                "     \n" +
                "  ]\n" +
                "}";
    }
}
