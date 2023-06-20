package basic;

import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayLoad.coursePrice());

        int count = js.getInt("courses.size()");
        Assert.assertEquals(count, 3);
        System.out.println("courseCount is: " + count);

        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(purchaseAmount, 910);
        System.out.println("PurchaseAmount is: " + purchaseAmount);

        String firstCourseTitle = js.getString("courses[0].title");
        Assert.assertEquals(firstCourseTitle, "Selenium Java");
        System.out.println("firstCourseTitle is: " + firstCourseTitle);

        for (int i = 0; i < count; i++)
        {
            System.out.println("courseTitle is: " + js.getString("courses[" + i + "].title")
                    + "\t" + " It's Price is: " + js.getInt("courses[" + i + "].price"));
        }

        for (int i = 0; i < count; i++)
        {
        String  courseTitles = js.getString("courses[" + i + "].title");
            if (courseTitles.equalsIgnoreCase("Shaft"))
            {
                System.out.println("Number of copies: " + js.get("courses[" + i + "].copies"));
                break;
            }

        }
    }
}
