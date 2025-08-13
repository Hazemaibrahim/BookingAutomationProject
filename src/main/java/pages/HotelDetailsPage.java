package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HotelDetailsPage extends BasePage {

    public HotelDetailsPage(WebDriver driver) {
        super(driver);
    }
    private final By checkInDate=By.xpath("//span[text()='Wed, Oct 1']");
    private final By checkOutDate=By.xpath("//span[text()='Tue, Oct 14']");
    private final By bedOptionRadioButton = By.xpath("//input[@type='radio' and @name='bedPreference_78883120' and @value='1']");
    private final By amountDropdown = By.name("nr_rooms_78883120_386871369_0_33_0_131741");
    private final By reserveButton = By.xpath("//button[contains(@class, 'js-reservation-button')]");

    public WebElement getCheckInDate(){
        return findElement(checkInDate);
    }
    public WebElement getCheckOutDate(){
        return findElement(checkOutDate);
    }

    public void selectBed() {
        scrollToElement(bedOptionRadioButton);
        findElement(bedOptionRadioButton).click();
    }

    public void selectAmount() {
        scrollToElement(amountDropdown);
        WebElement dropdown = findElement(amountDropdown);
        Select select = new Select(dropdown);
        select.selectByIndex(1);
    }


    public ReservationPage clickOnReserveButton() {
        waitUntilElementIsClickable(findElement(reserveButton));
        findElement(reserveButton).click();
        return new ReservationPage(driver);
    }

}