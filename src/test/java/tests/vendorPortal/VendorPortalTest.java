package tests.vendorPortal;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.AbstractTest;
import utils.Config;
import utils.Constants;
import utils.JSONUtil;
import tests.vendorPortal.model.VendorPortalTestData;
import udemy.pages.vendorPortal.DashboardPage;
import udemy.pages.vendorPortal.LoginPage;

public class VendorPortalTest extends AbstractTest {

  private LoginPage loginPage;
  private DashboardPage dashboardPage;
  private VendorPortalTestData testData;


  @BeforeTest
  @Parameters("testDataPath")
  public void setDriver(String testDataPath) {
    this.loginPage = new LoginPage(driver);
    this.dashboardPage = new DashboardPage(driver);
this.testData= JSONUtil.getTestData(testDataPath, VendorPortalTestData.class);
  }

  @Test
  public void loginTest() {
    loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
    Assert.assertTrue(loginPage.isAt());
    loginPage.login(testData.username(), testData.password());
  }

  @Test(dependsOnMethods = "loginTest")
  public void dashboardTest() {
    Assert.assertTrue(dashboardPage.isAt());
    Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
    Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
    Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
    Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

    dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
    Assert.assertEquals(dashboardPage.getSearchResultCount(), testData.searchResultsCount());
    dashboardPage.logOut();
  }

  @Test(dependsOnMethods = "dashboardTest")
  public void logOutTest() {
    dashboardPage.logOut();
    Assert.assertTrue(loginPage.isAt());
  }


}
