package business;

import core.WaitHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.ActionHelper.isDisplayed;

public class BasePage {
    public WaitHelper waitHelper;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartButton;

    @FindBy(css = "[data-test=\"error\"]")
    private WebElement errorMessage;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenuBtn;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartItemCount;

    @FindBy(css = "[data-test=\"social-twitter\"]")
    private WebElement twitterSocialButton;

    @FindBy(css = "[data-test=\"social-facebook\"]")
    private WebElement facebookSocialButton;

    @FindBy(css = "[data-test=\"social-linkedin\"]")
    private WebElement linkedinSocialButton;

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public BasePage goToCart(){
        waitHelper.waitUntilVisibility(cartButton);
        cartButton.click();
        return this;
    }

    public BasePage openMenu(){
        waitHelper.waitUntilVisibility(hamburgerMenuBtn);
        hamburgerMenuBtn.click();
        return this;
    }

    public int getCartBadgeItemCount(){
        waitHelper.waitUntilVisibility(cartItemCount,15);
        return Integer.parseInt(cartItemCount.getText());
    }

    public String getImageSrc(WebElement element){
        return element.getCssValue("src");
    }

    public String getTwitterSocialUrl(){
        return twitterSocialButton.getAttribute("href");
    }

    public String getFacebookSocialUrl(){
        return facebookSocialButton.getAttribute("href");
    }

    public String getLinkedinSocialUrl(){
        return linkedinSocialButton.getAttribute("href");
    }

    public boolean isErrorMessageDisplayed(){
        return isDisplayed(errorMessage);
    }

    public boolean isOnPage(WebElement element){
        waitHelper.waitUntilVisibility(element, 15);
        return element.isDisplayed();
    }

    public boolean isCartItemCountDisplayed() {
        try {
            return cartItemCount.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
