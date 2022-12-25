package api.tests;


import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setUsername(faker.name().username());
    }
    @Test(priority = 1)
    public void testPostUser(){
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        //response.getStatusCode(); //other way
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        //response.then().log().body().statusCode(200); //other way of validating data

        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after validation
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().body().statusCode(200);
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
