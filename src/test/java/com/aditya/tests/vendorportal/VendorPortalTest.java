package com.aditya.tests.vendorportal;

import com.aditya.pages.vendorportal.DashboardPage;
import com.aditya.pages.vendorportal.LoginPage;
import com.aditya.tests.AbstractTest;
import com.aditya.tests.vendorportal.model.VendorPortalTestData;
import com.aditya.util.Config;
import com.aditya.util.Constants;
import com.aditya.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) throws IOException {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashBoardTest() {
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsContent(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashBoardTest")
    public void logoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
