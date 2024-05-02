package business.pages;

import business.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.ActionHelper.isDisplayed;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test=\"error-button\"]")
    private WebElement closeErrorMessageBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillInUsername(String userName){
        userNameInput.sendKeys(userName);
        return this;
    }

    public LoginPage fillInPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickOnLogin(){
        loginButton.click();
    }

    public void closeErrorMessage(){
        closeErrorMessageBtn.click();
    }

    public boolean isOnLoginPage() {
        return isOnPage(userNameInput);
    }
}
