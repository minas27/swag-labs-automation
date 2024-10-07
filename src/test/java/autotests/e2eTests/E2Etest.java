package autotests.e2eTests;

import autotests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static business.data.CommonData.*;
import static business.data.UserData.*;

public class E2Etest extends BaseTest {
    @Test
    //Case 2:
    //1.Login by standard user
    //2.Click on the "Cart" icon
    //3.Click on "Continue Shopping" button
    //4.Make sure the Items page is displayed
    public void checkNavigationToCartAndBackToInventory() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.goToCart();
        cartPage.clickOnContinueShopping(getDriver());
        Assert.assertTrue(inventoryPage.isOnInventoryPage());
    }

    @Test
    //Case 3:
    //1.Login by standard user
    //2.Click on the "menu"
    //3.Click on the "About"
    //4.Opened page have sections and image
    public void checkAboutSection() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.openMenu();
        leftMenuComponent.goToAboutPage(getDriver());
        Assert.assertTrue(aboutPage.isOnAboutPage());
    }
    @Test
    //Case 4:
    //1.Login by standard user
    //2.Click on the filter icon on the right side
    //3.Select "price (low to high)"
    //4.Make sure prices are displayed from low to high
    public void checkInventoryPageFilterFunctionality() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.filterByPriceLowToHigh();
        Assert.assertTrue(inventoryPage.isFilterLowToHighApplied());
    }

    @Test
    //1.Login by visual_user
    //2.Add to cart Sauce Labs Backpack
    //3.In cart section click on Sauce Labs Backpack link
    //4.make sure that images are not equal
    public void checkThatImagesInInventoryPageAndItemPageDiffer() throws InterruptedException {
        loginPage
                .fillInUsername(getVisualUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        String inventoryPageItemImageURL = inventoryPage
                .getImageSrc
                (inventoryPage.getItemImageByName(BACKPACK_ITEM_FULL_TITLE));
        inventoryPage.clickOnItemByName(BACKPACK_ITEM_FULL_TITLE);
        String itemPageItemImageURL = itemPage.getItemImageSrc();
        Assert.assertNotEquals(inventoryPageItemImageURL, itemPageItemImageURL);
    }
}
