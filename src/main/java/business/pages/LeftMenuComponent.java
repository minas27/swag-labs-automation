package business.pages;

import business.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.ActionHelper.click;
import static core.WaitHelper.waitUntilVisibility;

public class LeftMenuComponent extends BasePage {
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsButton;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetButton;


    public LeftMenuComponent(WebDriver driver) {
        super(driver);
    }

    public LoginPage logOut(WebDriver driver) {
        waitUntilVisibility(logoutButton, 20);
        logoutButton.click();
        return new LoginPage(driver);
    }

    public InventoryPage clickOnAllItems(WebDriver driver) {
        waitUntilVisibility(allItemsButton, 20);
        allItemsButton.click();
        return new InventoryPage(driver);
    }

    public AboutPage goToAboutPage(WebDriver driver) {
        waitUntilVisibility(aboutButton, 20);
        aboutButton.click();
        return new AboutPage(driver);
    }

    public void clickOnReset(){
        click(resetButton);
    }
}
