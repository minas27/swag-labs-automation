package business.pages;

import business.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutPage extends BasePage {
    @FindBy(xpath = "//*[text()='Sauce Labs Platform for Test']")
    private WebElement sauceLabsTestHeading;

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnAboutPage(){
        return isOnPage(sauceLabsTestHeading);
    }


}
