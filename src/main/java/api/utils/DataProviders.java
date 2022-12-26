package api.utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
   @DataProvider(name = "Data")
    public static Object[][] getAllData() throws IOException, IOException {
       String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";
       XLUtility xlUtility = new XLUtility(path);
       int rowNum = xlUtility.getRowCount("Sheet1");
       int calCount = xlUtility.getCellCount("Sheet1", 1);
       String[][] apiData = new String[rowNum][calCount];

       for (int i = 1; i <rowNum ; i++) {
           for (int j = 0; j <calCount ; j++) {
               apiData[i - 1][j] = xlUtility.getCellData("Sheet1", i, j);
           }
       }
       return apiData;
   }

   @DataProvider(name = "UserNames")
   public static String[] getUserName() throws IOException {
       String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";
       XLUtility xlUtility = new XLUtility(path);
       int rowNum = xlUtility.getRowCount("Sheet1");
       String[] apiData = new String[rowNum];
       for (int i = 1; i <rowNum ; i++) {
           apiData[i - 1] = xlUtility.getCellData("Sheet1", i, 1);
       }
       return apiData;
   }
}
