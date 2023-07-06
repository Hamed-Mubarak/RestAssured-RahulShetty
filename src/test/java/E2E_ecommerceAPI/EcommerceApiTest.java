package E2E_ecommerceAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class EcommerceApiTest {
    @Test
    public static void ecommerceE2ETest(){

        LoginRequest login = new LoginRequest();
        login.setUserEmail("abdelaziz.hamed.m77@gmail.com");
        login.setUserPassword("Hamed1992");

        RequestSpecification loginBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://www.rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();
        RequestSpecification loginReq = given().spec(loginBaseReq).body(login);
        Response loginBaseResp = loginReq
                .when().post("/api/ecom/auth/login")
                .then().extract().response();
        Assert.assertEquals(loginBaseResp.statusCode(),200);
        Assert.assertTrue(loginBaseResp.getTime()<1000);

        LoginResponse loginBojoResp = loginBaseResp.as(LoginResponse.class);
        String token = loginBojoResp.getToken();
        String userId  = loginBojoResp.getUserId();
        System.out.println("Token is:  " + token);
        System.out.println("userId is:  " + userId);

        // Add product
        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://www.rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();
        RequestSpecification addProductReq = given().spec(addProductBaseReq)
                .formParam("productName","HamedShirtTest")
                .formParam("productAddedBy",userId)
                .formParam("productCategory","fashion")
                .formParam("productSubCategory","shirts")
                .formParam("productDescription","testAddProduct")
                .formParam("productFor","men")
                .formParam("productPrice","100")
                .multiPart("productImage", new File("/Applications/1-Rabbit/Documents/Hamed.jpeg"));
        Response addProductBaseResp = addProductReq
                .when().post("/api/ecom/product/add-product")
                .then().extract().response();
        Assert.assertEquals(addProductBaseResp.statusCode(),201);
        AddProductResponse addProductBojoResp = addProductBaseResp.as(AddProductResponse.class);
        String productId = addProductBojoResp.getProductId();
        System.out.println("ProductId is: "+productId);
        System.out.println(addProductBojoResp.getMessage());

        //Create Order
        // Take a new object instance of class has array elements
        OrderDetails orderDetails = new OrderDetails();
        // set the elements inside one index of the array
        orderDetails.setCountry("Egypt");
        orderDetails.setProductOrderedId(productId);
        // Take a new object of the List of array elements
        // and instate a new array list as expected
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        // add the object which containing elements of the 1st array index to the new array list object
        orderDetailsList.add(orderDetails);
        // take a new object instance of the payload body
        CreateOrderRequest order = new CreateOrderRequest();
        // set the payload body with the new array list object
        order.setOrders(orderDetailsList);

        RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://www.rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
        RequestSpecification createOrderReq = given().spec(createOrderBaseReq).body(order);
        Response createOrderBaseResp = createOrderReq
                .when().post("/api/ecom/order/create-order")
                .then().extract().response();
        String orderStringResp = createOrderBaseResp.asString();
        JsonPath js = new JsonPath(orderStringResp);
        String orderId =js.getString("orders[0]");
        System.out.println(orderId);
        /*
        CreateOrderResponse createOrderBojoResp = createOrderBaseResp.as(CreateOrderResponse.class);
        for(int i=0; i<createOrderBojoResp.getOrders().size();i++)
        {
            System.out.println("Order " + (i+1) + ":/t" +createOrderBojoResp.getOrders().indexOf(i+1));
            System.out.println("ProductOrder " + (i+1) + ":/t" +createOrderBojoResp.getProductOrderId().indexOf(i+1) );
        }
        */

        //Get Order Details
        RequestSpecification getOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://www.rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
        RequestSpecification getOrderReq = given().spec(getOrderBaseReq)
                .queryParam("id",orderId);
        Response getOrderBaseResp= getOrderReq
                .when().get("/api/ecom/order/get-orders-details")
                .then().extract().response();
        GetOrderResponse getOrderBojoResp = getOrderBaseResp.as(GetOrderResponse.class);
        String orderBy = getOrderBojoResp.getData().getOrderBy();
        System.out.println("The Order Owner's: "+ orderBy);

        //Delete Order
        RequestSpecification deleteOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://www.rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
        RequestSpecification deleteOrderReq = given().spec(deleteOrderBaseReq)
                .pathParam("orderId",orderId);

        Response deleteOrderBaseResp = deleteOrderReq
                .when().delete("/api/ecom/order/delete-order/{orderId}")
                .then().extract().response();
        DeleteResponse deleteOrderBojoResponse = deleteOrderBaseResp.as(DeleteResponse.class);
        String orderMessage = deleteOrderBojoResponse.getMessage();
        System.out.println(orderMessage);


        //Delete Product
        RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://www.rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
        RequestSpecification deleteProductReq = given().spec(deleteProductBaseReq)
                .pathParam("productId",productId);

        Response deleteProductBaseResp = deleteProductReq
                .when().delete("/api/ecom/product/delete-product/{productId}")
                .then().extract().response();
        DeleteResponse deleteProductBojoResponse = deleteProductBaseResp.as(DeleteResponse.class);
        String productMessage = deleteProductBojoResponse.getMessage();
        System.out.println(productMessage);

        }

    }



