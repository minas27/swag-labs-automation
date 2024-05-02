package business.pages;

import business.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
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

    public YourInformationPage goToCheckout(WebDriver driver){
        waitHelper.waitUntilClickable(checkoutButton);
        checkoutButton.click();
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
        waitHelper.waitUntilVisibility(continueShoppingButton);
        continueShoppingButton.click();
        return new InventoryPage(driver);
    }

    public boolean verifyItemVisibility(String... itemName){
        waitHelper.waitUntilVisibility(cartItemNames.get(0), 20);
        for (int i = 0; i < itemName.length; i++){
            if (!cartItemNames.get(i).getText().contains(itemName[i])) {
                return false;
            }
        }
        return true;
    }
}
