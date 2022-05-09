package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "ValidUsers")
    public static Object[][] getValidCredentials() {
        String excelPath = "src/test/resources/testdata/LoginData.xlsx";
        return ReadFromExcel.getTableArray(excelPath, "ValidLoginData");
    }

    @DataProvider(name = "InValidUsers")
    public static Object[][] getInvalidCredentials() {
        String excelPath = "src/test/resources/testdata/LoginData.xlsx";
        return ReadFromExcel.getTableArray(excelPath, "InvalidLoginData");
    }
}
