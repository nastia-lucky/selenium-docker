package udemy.pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import udemy.pages.AbstractPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {

  @FindBy(name = "departure-flight")
  private List<WebElement> departureFlights;

  @FindBy(name = "arrival-flight")
  private List<WebElement> arrivalFlight;


  @FindBy(id = "confirm-flights")
  private WebElement confirmFlightsButton;

  public FlightsSelectionPage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isAt() {
    this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
    return this.confirmFlightsButton.isDisplayed();
  }

  public void selectFlights(){
    int random= ThreadLocalRandom.current().nextInt(0, departureFlights.size());
    this.departureFlights.get(random).click();
    this.arrivalFlight.get(random).click();

  }

  public void confirmFlight(){
    this.confirmFlightsButton.click();
  }
}
