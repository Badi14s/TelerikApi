package tests;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by Bader_Bader on 10/24/2016.
 */
public class ApiTests {

    private static String baseURL = "http://api.everlive.com/v1/";
    private static String appID = "aey3rj2srna8wnsf";
    private static String userID = "8c5131a0-93d4-11e6-98ed-2ff1472beaf0";
    private static String usernameCheck = "test24";
    private static String myJson = "{\"name\": \"John\"}";


    @Test
    public void GetAllUsers() {
        int count =
                when().
                        get(baseURL + appID + "/Users").
                        then().
                        statusCode(200).
                        contentType("application/json").
                        extract().
                        path("Count");

        Assert.assertTrue("There are no users", count > 0);
    }


    @Test
    public void GetUserDetails() {
        when().
                get(baseURL + appID + "/Users/" + userID).
                then().
                statusCode(200).
                contentType("application/json").
                body("Result.Username", equalTo(usernameCheck));
    }

    @Test
    public void CreateUser() {
        given().
                contentType("application/json").
                body(myJson).
                when().
                        post(baseURL + appID + "/Users").
                        then().
                        statusCode(201);

    }

    @Test
    public void UpdateUser() {

    }


}
