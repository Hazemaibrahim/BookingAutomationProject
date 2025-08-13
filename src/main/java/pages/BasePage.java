package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilTextIsPresent(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void scrollToElementAndClick(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int scrollStep = 600;   // smaller steps to not skip hotels
        int maxScrolls = 30;    // enough to go through the list

        for (int i = 0; i < maxScrolls; i++) {
            try {
                WebElement element = driver.findElement(locator);
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                element.click();
                return; // clicked successfully
            } catch (NoSuchElementException e) {
                js.executeScript("window.scrollBy(0, arguments[0]);", scrollStep);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {} // small delay for load
            } catch (ElementClickInterceptedException e) {
                WebElement element = driver.findElement(locator);
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                element.click();
                return;
            }
        }
        throw new RuntimeException("Element not found after scrolling: " + locator);
    }



    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
}