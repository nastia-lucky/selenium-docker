package tests.flightReservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.AbstractTest;
import tests.flightReservation.model.FlightReservationTestData;
import utils.Config;
import utils.Constants;
import utils.JSONUtil;
import udemy.pages.flightReservation.*;

public class FlightReservationTest extends AbstractTest {

  private FlightReservationTestData flightReservationTestData;


  @BeforeTest
  @Parameters("testDataPath")
  public void setDriver(String testDataPath) {
    this.flightReservationTestData = JSONUtil.getTestData(testDataPath, FlightReservationTestData.class);
  }

  @Test
  public void userRegistrationTest() {
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
    Assert.assertTrue(registrationPage.isAt());
    registrationPage.enterUserDetails(flightReservationTestData.firstName(), flightReservationTestData.lastName());
    registrationPage.enterUserCredentials(flightReservationTestData.email(), flightReservationTestData.password());
    registrationPage.enterAddress(flightReservationTestData.street(), flightReservationTestData.city(), flightReservationTestData.zip());
    registrationPage.register();
  }

  @Test(dependsOnMethods = "userRegistrationTest")
  public void registrationConfirmationTest() {
    RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
    Assert.assertTrue(registrationConfirmationPage.isAt());
    Assert.assertEquals(registrationConfirmationPage.getFirstName(), flightReservationTestData.firstName());
    registrationConfirmationPage.goToFlightSearch();
  }

  @Test(dependsOnMethods = "registrationConfirmationTest")
  public void flightSearchTest() {
    FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
    Assert.assertTrue(flightSearchPage.isAt());
    flightSearchPage.selectPassengers(flightReservationTestData.passengersCount());
    flightSearchPage.searchFlight();
  }

  @Test(dependsOnMethods = "flightSearchTest")
  public void flightSelectionTest() {
    FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
    Assert.assertTrue(flightsSelectionPage.isAt());
    flightsSelectionPage.selectFlights();
    flightsSelectionPage.confirmFlight();
  }

  @Test(dependsOnMethods = "flightSelectionTest")
  public void flightConfirmationTest() {
    FlightsConfirmationPage flightsConfirmationPage = new FlightsConfirmationPage(driver);
    Assert.assertTrue(flightsConfirmationPage.isAt());
    Assert.assertEquals(flightsConfirmationPage.getPrice(), flightReservationTestData.expectedPrice());
  }
}
