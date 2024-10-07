package business.pages;

import business.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static core.WaitHelper.waitUntilVisibility;

public class CheckoutOverviewPage extends BasePage {
    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemsPrices;

    @FindBy(css = "[data-test=\"inventory-item\"]")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(css = "[data-test=\"subtotal-label\"]")
    private WebElement itemTotalPrice;

    @FindBy(css = "[data-test=\"tax-label\"]")
    private WebElement taxPrice;

    @FindBy(css = "[data-test=\"total-label\"]")
    private WebElement totalPrice;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".complete-header")
    private WebElement thankYouMessage;

    @FindBy(css = ".complete-text")
    private WebElement thankYouCompleteMessage;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutOverviewPage clickOnFinish(){
        finishButton.click();
        return this;
    }

    public double getTotal(){
        return Double.parseDouble(totalPrice.getText().replaceAll("[^\\d.]", ""));
        // replaces all non-digits with emptiness
    }

    public double getTax(){
        return Double.parseDouble(taxPrice.getText().replaceAll("[^\\d.]", ""));
        // replaces all non-digits with emptiness
    }

    public int getItemQtyByName(String itemName){
        for (WebElement inventoryItem : inventoryItems) {
            String currentItemName = getItemName(inventoryItem).getText().trim();

            if (currentItemName.equals(itemName)) {
                return Integer.parseInt(inventoryItem.findElement(By.cssSelector(".cart_quantity")).getText().trim());
            }
        }
        return -1;
    }

    public String getSuccessMessage(){
        waitUntilVisibility(thankYouMessage);
        return thankYouMessage.getText();
    }

    public String getSuccessCompleteMessage(){
        waitUntilVisibility(thankYouCompleteMessage);
        return thankYouCompleteMessage.getText();
    }

    public double calculateTax() {
        BigDecimal total = BigDecimal.valueOf(getTotal());
        BigDecimal itemPricesTotal = BigDecimal.valueOf(calculateItemPricesTotal());
        BigDecimal tax = total.subtract(itemPricesTotal);
        BigDecimal roundedTax = tax.setScale(1, RoundingMode.HALF_UP); // Set scale to 1 decimal place
        return roundedTax.doubleValue(); // Convert BigDecimal to double
    }

    public InventoryPage clickOnBackToProducts(WebDriver driver){
        waitUntilVisibility(backToProductsButton);
        backToProductsButton.click();
        return new InventoryPage(driver);
    }

    public boolean verifyInventoryItemNameIsCorrect(String itemName){
        boolean exists = false;
        for (WebElement item: inventoryItemNames){
            if (item.getText().equals(itemName)){
                exists = true;
                break;
            }
        }
        return exists;
    }

    public boolean verifyItemTotal(){
        return itemTotalPrice
                .getText()
                .endsWith(String.valueOf(calculateItemPricesTotal()));
    }

    private WebElement getItemName(WebElement inventoryItem) {
        return inventoryItem.findElement(By.cssSelector(".inventory_item_name"));
    }

    private double calculateItemPricesTotal(){
        waitUntilVisibility(itemsPrices.get(0));
        double total = 0;

        for (WebElement itemPrice: itemsPrices){
            total += Double.parseDouble(itemPrice.getText().substring(1));
        }
        return total;
    }
}
