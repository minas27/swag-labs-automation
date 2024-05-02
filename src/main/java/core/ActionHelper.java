package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ActionHelper {

    private static Select select;

    public static void selectByVisibleText(WebElement element, String visibleText){
        select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public static void selectByValue(WebElement element, String value){
        select = new Select(element);
        select.selectByValue(value);
    }

    public static boolean isDisplayed(WebElement element){
        try{
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void scrollDown(WebDriver driver, int pixel){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.scrollBy(0, %s)", pixel));
    }
}
