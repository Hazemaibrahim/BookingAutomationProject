package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By locationInput = By.id(":rh:");
    private final By calendarDateButton = By.xpath("//button[@data-testid='searchbox-dates-container']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By nextMonthButton = By.xpath("//button[@aria-label='Next month']");
    private final By monthHeader = By.xpath("//h3[contains(@class, 'e7addce19e') and contains(@class, 'af236b7586')]");

    public void enterLocation(String location) {
        findElement(locationInput).clear();
        findElement(locationInput).sendKeys(location);
    }

    public void selectDates(String checkInDay, String checkOutDay) {
        findElement(calendarDateButton).click();

        navigateToMonth("October 2025");
        clickDay(Integer.parseInt(checkInDay));

        navigateToMonth("October 2025");
        clickDay(Integer.parseInt(checkOutDay));
    }

    private void clickDay(int day) {
        findElement(By.xpath("//span[@data-date='2025-10-" + String.format("%02d", day) + "']")).click();
    }

    private void navigateToMonth(String targetMonthYear) {
        int safetyCounter = 0;
        int maxMonthsToCheck = 24; // avoid infinite loops

        while (safetyCounter < maxMonthsToCheck) {
            String currentMonthYear = findElement(monthHeader).getText().trim();
            if (currentMonthYear.equals(targetMonthYear)) {
                break; // Stop if month/year matches
            }
            findElement(nextMonthButton).click();
            safetyCounter++;
        }

        if (safetyCounter >= maxMonthsToCheck) {
            throw new RuntimeException("Target month '" + targetMonthYear + "' not found within range.");
        }
    }

    public SearchResultsPage clickOnSearchButton() {
        findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }
}
