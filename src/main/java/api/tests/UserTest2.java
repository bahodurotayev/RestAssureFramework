package api.tests;


import api.endpoints.UserEndPoints2;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest2 {
    Faker faker;
    User userPayload;
    public Logger logger;

    /***
     @UserTest2
     This is duplicate of @UserTest I used method from @UserEndPoints2 for testClasses.

     !!! also change in testng to run classes
     ***/

    @BeforeClass
    public void setUp(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setUsername(faker.name().username());

        //logs
        logger = LogManager.getLogger(this.getClass());
        // log debug
        logger.debug("adding debug");
    }
    @Test(priority = 1)
    public void testPostUser(){
        logger.info("**********Creating User**********");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**********User is created**********");
    }
    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("**********Reading user info**********");
        Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        //response.getStatusCode(); //other way
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**********User info is displayed**********");
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        logger.info("**********Updating User**********");
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
        //response.then().log().body().statusCode(200); //other way of validating data

        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after validation
        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().body().statusCode(200);
        logger.info("**********User updated**********");
    }
    @Test(priority = 4)
    public void testDeleteUser(){
        logger.info("**********Delete User**********");
        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**********User deleted**********");
    }
}
