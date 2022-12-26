package api.tests;

import api.utils.DataProviders;
import org.testng.annotations.Test;

public class DDTests {
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String username, String fName, String lName, String email, String pwd, String phone){

    }
}
