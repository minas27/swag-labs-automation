package business.pages;

import business.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static core.ActionHelper.click;
import static core.WaitHelper.waitUntilVisibility;

public class ItemPage extends BasePage {
    @FindBy(id = "add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsBtn;

    @FindBy(id = "remove")
    private WebElement removeButton;

    @FindBy(css = ".inventory_details_img")
    private WebElement itemImage;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage clickOnAddToCartButton(){
        waitUntilVisibility(addToCartBtn, 50);
        addToCartBtn.click();
        return this;
    }

    public ItemPage removeFromCart(){
        waitUntilVisibility(removeButton, 25);
        removeButton.click();
        return this;
    }

    public ItemPage clickOnBackToProducts(){
        click(backToProductsBtn);
        return this;
    }

    public String getItemImageSrc(){
        return itemImage.getAttribute("src");
    }
}
