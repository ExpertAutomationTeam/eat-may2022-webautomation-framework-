package com.saucedemo;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utility.ConnectDB;
import utility.ExcelReader;
import utility.Utility;

public class LoginTest extends CommonAPI{
    ExcelReader excelReader = new ExcelReader(Utility.currentDir+"/data/test-data.xlsx", "data");
    ConnectDB connectDB = new ConnectDB();
    String username = Utility.decode(prop.getProperty("username"));
    String password = Utility.decode(prop.getProperty("password"));

    //@Test
    public void loginWithValidCred() throws Exception {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        String dbUsername = connectDB.readDataBase("cred", "username").get(0);
        String dbPassword = connectDB.readDataBase("cred", "password").get(0);

        loginPage.enterUsername(dbUsername);
        loginPage.enterPassword(dbPassword);
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(excelReader.getDataForGivenHeaderAndKey("key", "home page header"), homePage.getHeaderText());
    }
    @Test
    public void loginWithInvalidUsername(){
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterUsername("");
        loginPage.enterPassword(password);
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(excelReader.getDataForGivenHeaderAndKey("key", "invalid username error message"), loginPage.errorMessage());
        captureScreenshot();
    }
    //@Test
    public void loginWithInvalidPassword(){
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterUsername(username);
        loginPage.enterPassword("");
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(excelReader.getDataForGivenHeaderAndKey("key", "invalid password error message"), loginPage.errorMessage());
    }
}
