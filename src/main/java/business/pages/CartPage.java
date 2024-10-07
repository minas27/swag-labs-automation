package business.pages;

import business.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static core.ActionHelper.click;
import static core.WaitHelper.waitUntilClickable;
import static core.WaitHelper.waitUntilVisibility;

public class CartPage extends BasePage {
    private LeftMenuComponent leftMenuComponent;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_item .inventory_item_name")
    private List<WebElement> cartItemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public LeftMenuComponent getLeftMenuComponent() {
        return leftMenuComponent;
    }

    public YourInformationPage goToCheckout(WebDriver driver){
        click(checkoutButton);
        return new YourInformationPage(driver);
    }

    public String getInventoryItemDescriptionByName(String itemName){
        for(WebElement el: cartItems){
            String currentItemTitle = el.findElement(By.className("inventory_item_name")).getText();
            if (currentItemTitle.equals(itemName)){
                return el.findElement(By.className("inventory_item_desc")).getText();
            }
        }
        return "Item description not found";
    }

    public InventoryPage clickOnContinueShopping(WebDriver driver){
        waitUntilVisibility(continueShoppingButton);
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }

    public boolean verifyItemVisibility(String... itemName){
        waitUntilVisibility(cartItemNames.get(0), 20);
        for (int i = 0; i < itemName.length; i++){
            if (!cartItemNames.get(i).getText().contains(itemName[i])) {
                return false;
            }
        }
        return true;
    }
}
