package business;

import business.data.CommonElements;
import business.pages.CartPage;
import core.ActionHelper;
import core.WaitHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static core.ActionHelper.click;
import static core.ActionHelper.isDisplayed;
import static core.WaitHelper.waitUntilVisibility;

public class BasePage {
    protected WebDriver driver;

    CommonElements commonElements;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenuBtn;

    @FindBy(id="react-burger-cross-btn")
    private WebElement closeBtn;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartItemCount;

    @FindBy(css = "[data-test=\"social-twitter\"]")
    private WebElement twitterSocialButton;

    @FindBy(css = "[data-test=\"social-facebook\"]")
    private WebElement facebookSocialButton;

    @FindBy(css = "[data-test=\"social-linkedin\"]")
    private WebElement linkedinSocialButton;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ActionHelper.setDriver(driver);
        WaitHelper.setDriver(driver);
        commonElements = new CommonElements(driver);
    }

    public CommonElements getCommonElements() {
        return commonElements;
    }

    public CartPage goToCart(){
        click(cartButton);
        return new CartPage(driver);
    }

    public BasePage openMenu(){
        click(hamburgerMenuBtn);
        return this;
    }

    public BasePage closeMenu(){
        click(closeBtn);
        return this;
    }

    public int getCartBadgeItemCount(){
        waitUntilVisibility(cartItemCount,15);
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

    public boolean isOnPage(WebElement element){
        waitUntilVisibility(element, 15);
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
