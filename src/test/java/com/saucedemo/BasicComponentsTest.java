package com.saucedemo;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utility.ExcelReader;
import utility.Utility;

public class BasicComponentsTest extends CommonAPI{
    ExcelReader excelReader = new ExcelReader(Utility.currentDir+"/data/test-data.xlsx", "data");

    @Test
    public void checkUserLandsOnTheRightWebsite() {
        String title = getPageTitle();
        System.out.println(title);
        Assert.assertEquals(excelReader.getDataForGivenHeaderAndKey("key", "login page title"), title);
    }

    @Test
    public void checkPresenceOfLoginPageComponents() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertTrue(loginPage.checkUsernameFieldPresence());
        Assert.assertTrue(loginPage.checkPasswordFieldPresence());
        Assert.assertTrue(loginPage.checkLoginButtonPresence());
    }

}
