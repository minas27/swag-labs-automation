package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ActionHelper {

    private static Select select;
    private static WebDriver driver;

    // Set the WebDriver instance globally in ActionHelper
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // Select by visible text
    public static void selectByVisibleText(WebElement element, String visibleText) {
        select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    // Select by value
    public static void selectByValue(WebElement element, String value) {
        select = new Select(element);
        select.selectByValue(value);
    }

    // Check if an element is displayed
    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Scroll down the page by a number of pixels
    public static void scrollDown(int pixel) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.scrollBy(0, %s)", pixel));
    }

    public static void click(WebElement element) {
        WaitHelper.waitUntilVisibility(element); // Wait for visibility of the element
        element.click(); // Then click on the element
    }
}
