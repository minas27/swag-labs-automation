package business.pages;

import business.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.WaitHelper.waitUntilClickable;
import static core.WaitHelper.waitUntilVisibility;

public class YourInformationPage extends BasePage {
    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    public YourInformationPage setUserCredentials(String firstName, String lastName, String postalCode){
        waitUntilVisibility(firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        return this;
    }
    public YourInformationPage(WebDriver driver) {
        super(driver);
    }

    public YourInformationPage clickOnContinueButton(){
        waitUntilClickable(continueButton);
        continueButton.click();
        return this;
    }
}
