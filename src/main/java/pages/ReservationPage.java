package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservationPage extends BasePage {
    public ReservationPage(WebDriver driver) {
        super(driver);
    }

    private final By hotelName= By.xpath("//h1[text()='Tolip Hotel Alexandria']");

    public WebElement getHotelName(){
        return findElement(hotelName);
    }
}
