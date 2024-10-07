package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static void waitUntilVisibility(WebElement element, int sec){
        new WebDriverWait(driver, sec)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilVisibility(WebElement element){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebElement element, int sec){
        new WebDriverWait(driver, sec)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilClickable(WebElement element){
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void pause(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }
}
