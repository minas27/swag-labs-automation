package business.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.ActionHelper.click;
import static core.ActionHelper.isDisplayed;

public class CommonElements {
    private WebDriver driver;

    @FindBy(css = "[data-test=\"error\"]")
    private WebElement errorMessage;

    @FindBy(css = "[data-test=\"error-button\"]")
    private WebElement closeErrorMessageBtn;

    public CommonElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed(){
        return isDisplayed(errorMessage);
    }

    public void closeErrorMessage(){
        click(closeErrorMessageBtn);
    }
}
