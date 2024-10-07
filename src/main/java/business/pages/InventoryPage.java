package business.pages;

import business.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static core.ActionHelper.isDisplayed;
import static core.ActionHelper.selectByValue;
import static core.WaitHelper.waitUntilVisibility;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItemsBoxes;

    @FindBy(css = ".inventory_item .inventory_item_name")
    private List<WebElement> inventoryItems;

    @FindBy(css = "img.inventory_item_img")
    private List<WebElement> inventoryItemsImages;

    @FindBy(css = ".inventory_item .btn_secondary")
    private List<WebElement> inventoryItemsWithRemoveButton;

    @FindBy(css = ".product_sort_container")
    private WebElement filterElementButton;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddToCartButton(String name){
        return driver.findElement(By.id("add-to-cart-sauce-labs-" + name));
    }

    public WebElement getRemoveButton(String name){
        return driver.findElement(By.id("remove-sauce-labs-" + name));
    }

    public InventoryPage clickOnAddToCartButton(String name){
        waitUntilVisibility(inventoryItems.get(0));
        getAddToCartButton(name.toLowerCase().replace(' ', '-')).click();
        return this;
    }

    public InventoryPage clickOnRemoveButton(String name){
        waitUntilVisibility(inventoryItems.getFirst());
        getRemoveButton(name.toLowerCase().replace(' ', '-')).click();
        return this;
    }

    public InventoryPage clickOnItemByName(String itemName){
        waitUntilVisibility(inventoryItems.getFirst(), 30);
        for(WebElement item: inventoryItems){
            if(item.getText().contains(itemName)){
                item.click();
                break;
            }
        }
        return this;
    }

    public String getInventoryItemDescriptionByName(String itemName){
        for(WebElement el: inventoryItemsBoxes){
            String currentItemTitle = el.findElement(By.className("inventory_item_name")).getText();
            if (currentItemTitle.equals(itemName)){
                return el.findElement(By.className("inventory_item_desc")).getText();
            }
        }
        return "Item description not found";
    }

    public WebElement getItemImageByName(String itemName){
        for(WebElement itemImage: inventoryItemsImages){
            if(itemImage.getAttribute("alt").contains(itemName)){
                return itemImage;
            }
        }
        return null;
    }

    public boolean isRemoveButtonDisplayed(){
        waitUntilVisibility(inventoryItemsWithRemoveButton.get(0));
        return isDisplayed(inventoryItemsWithRemoveButton.get(0));
    }

    public boolean verifyRemoveButtonVisibility(String itemName){
        for(WebElement el: inventoryItemsWithRemoveButton){
            for(WebElement cartBox: inventoryItemsBoxes) {
                String currentItemTitle = cartBox.findElement(By.className("inventory_item_name")).getText();
                if (currentItemTitle.equals(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyAddToCartButtonVisibility(WebDriver driver, String itemName) {
        try {
            if (getAddToCartButton(itemName.substring(11)) != null) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }


    public boolean isOnInventoryPage() {
        for (WebElement item : inventoryItems) {
            if (!isOnPage(item)) {
                return false;
            }
        }
        return true;
    }

    public void filterAlphabeticalAtoZ(){
        selectByValue(filterElementButton, "az");
    }

    public void filterAlphabeticalZtoA(){
        selectByValue(filterElementButton, "za");
    }

    public void filterByPriceLowToHigh(){
        selectByValue(filterElementButton, "lohi");
    }

    public void filterByPriceHighToLow(){
        selectByValue(filterElementButton, "hilo");
    }

    public boolean isFilterAtoZapplied(){
        return (new Select(filterElementButton)
                .getFirstSelectedOption()
                .getAttribute("value")
                .equals("az"));
    }

    public boolean isFilterZtoAapplied(){
        return (new Select(filterElementButton)
                .getFirstSelectedOption()
                .getAttribute("value")
                .equals("za"));
    }

    public boolean isFilterLowToHighApplied(){
        return (new Select(filterElementButton)
                .getFirstSelectedOption()
                .getAttribute("value")
                .equals("lohi"));
    }

    public boolean isFilterHighToLowApplied(){
        return (new Select(filterElementButton)
                .getFirstSelectedOption()
                .getAttribute("value")
                .equals("hilo"));
    }

}
