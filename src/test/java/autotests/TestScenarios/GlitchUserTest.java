package autotests.TestScenarios;

import autotests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static business.Data.CommonData.*;
import static business.Data.Messages.ORDER_COMPLETE_SUCCESS_MESSAGE_DESC;
import static business.Data.UserData.getPassword;
import static business.Data.UserData.getPerformanceGlitchUser;


public class GlitchUserTest extends BaseTest {
    @BeforeMethod
    public void loginAsPerformanceGlitchUser(){
        loginPage
                .fillInUsername(getPerformanceGlitchUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.isOnInventoryPage();
    }
    @Test
    // TC-1
    public void checkAddToCartFunctionalityFromProductPage(){
        inventoryPage.clickOnItemByName(BACKPACK_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .clickOnBackToProducts();
        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed());
        inventoryPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-2
    public void checkMultipleItemsAddToCartAreDisplayedInCart(){
        inventoryPage.clickOnItemByName(BACKPACK_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .clickOnBackToProducts();
        inventoryPage.clickOnItemByName(FLEECE_JACKET_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .clickOnBackToProducts()
                .goToCart();
        cartPage.verifyItemVisibility(BACKPACK_ITEM_FULL_TITLE, FLEECE_JACKET_ITEM_FULL_TITLE);
        cartPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-3
    public void checkRemoveFunctionality() {
        inventoryPage.clickOnItemByName(BACKPACK_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .clickOnBackToProducts();
        inventoryPage.clickOnItemByName(FLEECE_JACKET_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .clickOnBackToProducts();
        inventoryPage
                .clickOnRemoveButton(getDriver(), BACKPACK_ITEM_FULL_TITLE.substring(11))
                .goToCart();
        Assert.assertFalse(cartPage.verifyItemVisibility(BACKPACK_ITEM_FULL_TITLE, FLEECE_JACKET_ITEM_FULL_TITLE));
        cartPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-4
    public void checkItemsAddedToCartOneByOneAdding(){
        inventoryPage.clickOnItemByName(BACKPACK_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .goToCart();
        Assert.assertTrue(cartPage.verifyItemVisibility(BACKPACK_ITEM_FULL_TITLE));
        cartPage.clickOnContinueShopping(getDriver());
        inventoryPage.clickOnItemByName(FLEECE_JACKET_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .goToCart();
        Assert.assertTrue(cartPage.verifyItemVisibility(BACKPACK_ITEM_FULL_TITLE, FLEECE_JACKET_ITEM_FULL_TITLE));
        cartPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-5
    public void checkCartItemBadgeAndSuccessfulPaymentFlow(){
        inventoryPage.clickOnAddToCartButton(getDriver(), BACKPACK_ITEM_FULL_TITLE.substring(11));
        Assert.assertEquals(inventoryPage.getCartBadgeItemCount(), 1);
        inventoryPage.goToCart();
        Assert.assertTrue(cartPage.verifyItemVisibility(BACKPACK_ITEM_FULL_TITLE));
        cartPage.goToCheckout(getDriver());
        yourInformationPage
                .setUserCredentials(getFirstName(), getLastName(), getPostalCode())
                .clickOnContinueButton();
        Assert.assertEquals(checkoutOverviewPage.getItemQtyByName(BACKPACK_ITEM_FULL_TITLE), 1);
        Assert.assertTrue(checkoutOverviewPage.verifyInventoryItemNameIsCorrect(BACKPACK_ITEM_FULL_TITLE));
        checkoutOverviewPage.clickOnFinish();
        Assert.assertEquals(checkoutOverviewPage.getSuccessCompleteMessage(), ORDER_COMPLETE_SUCCESS_MESSAGE_DESC);
        checkoutOverviewPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-6
    public void checkCartItemCountIsNullifiedAfterThePurchase(){
        inventoryPage.clickOnAddToCartButton(getDriver(), BACKPACK_ITEM_FULL_TITLE.substring(11));
        Assert.assertEquals(inventoryPage.getCartBadgeItemCount(), 1);
        inventoryPage.goToCart();
        Assert.assertTrue(cartPage.verifyItemVisibility(BACKPACK_ITEM_FULL_TITLE));
        cartPage.goToCheckout(getDriver());
        yourInformationPage
                .setUserCredentials(getFirstName(), getLastName(), getPostalCode())
                .clickOnContinueButton();
        Assert.assertEquals(checkoutOverviewPage.getItemQtyByName(BACKPACK_ITEM_FULL_TITLE), 1);
        Assert.assertTrue(checkoutOverviewPage.verifyInventoryItemNameIsCorrect(BACKPACK_ITEM_FULL_TITLE));
        checkoutOverviewPage.clickOnFinish();
        Assert.assertEquals(checkoutOverviewPage.getSuccessCompleteMessage(), ORDER_COMPLETE_SUCCESS_MESSAGE_DESC);
        checkoutOverviewPage.clickOnBackToProducts(getDriver());
        Assert.assertFalse(inventoryPage.isCartItemCountDisplayed());
        inventoryPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-7
    public void checkItemDescriptionAcrossAllPages(){
        String inventoryPageItemDesc = inventoryPage.getInventoryItemDescriptionByName(BACKPACK_ITEM_FULL_TITLE);
        inventoryPage
                .clickOnAddToCartButton(getDriver(), BACKPACK_ITEM_FULL_TITLE.substring(11))
                .goToCart();
        Assert.assertEquals(inventoryPageItemDesc, cartPage.getInventoryItemDescriptionByName(BACKPACK_ITEM_FULL_TITLE));
        cartPage.openMenu();
        leftMenuComponent.clickOnReset();
    }

    @Test
    // TC-8
    public void checkLogOutLogInFunctionality(){
        inventoryPage.openMenu();
        leftMenuComponent.logOut(getDriver());
        Assert.assertTrue(loginPage.isOnLoginPage());
        loginPage
                .fillInUsername(getPerformanceGlitchUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.isOnInventoryPage();
        inventoryPage.openMenu();
        leftMenuComponent.clickOnReset();
    }
}
