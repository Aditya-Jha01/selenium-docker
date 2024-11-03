package com.aditya.pages.flightreservation;

import com.aditya.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOption;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }

    public void selectFlights() {
        int random = ThreadLocalRandom.current().nextInt(0,departureFlightsOptions.size());
        this.departureFlightsOptions.get(random).click();
        actions.moveToElement( this.arrivalFlightsOption.get(random)).click().build().perform();
    }

    public void confirmFlights() {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.confirmFlightButton).click().build().perform();
    }
}
