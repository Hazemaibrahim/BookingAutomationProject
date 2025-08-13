package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Set;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    private final By seeAvailabilityButton = By.xpath(
            "//div[contains(@data-testid,'property-card')]//div[contains(text(),'Tolip Hotel Alexandria')]" +
                    "/ancestor::div[contains(@data-testid,'property-card')]//span[text()='See availability']/ancestor::a"
    );

    public HotelDetailsPage clickSeeAvailabilityButton() {
        String originalTab = driver.getWindowHandle();

        // Reuse BasePage method
        scrollToElementAndClick(seeAvailabilityButton);

        wait.until(driver -> driver.getWindowHandles().size() > 1);

        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        return new HotelDetailsPage(driver);
    }
}
