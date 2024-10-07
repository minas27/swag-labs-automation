package business.pages;

import business.BasePage;
import business.data.CommonElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.ActionHelper.click;
import static core.ActionHelper.isDisplayed;
import static core.WaitHelper.pause;
import static core.WaitHelper.waitUntilVisibility;

public class LoginPage {
    private WebDriver driver;

    CommonElements commonElements;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.commonElements = new CommonElements(driver);
        PageFactory.initElements(driver, this);
    }

    public CommonElements getCommonElements() {
        return commonElements;
    }

    public LoginPage fillInUsername(String userName) throws InterruptedException {
        waitUntilVisibility(userNameInput);
        userNameInput.sendKeys(userName);
        return this;
    }

    public LoginPage fillInPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickOnLogin(){
        click(loginButton);
    }

    public boolean isOnLoginPage(){
        waitUntilVisibility(userNameInput);
        return userNameInput.isDisplayed();
    }
}
