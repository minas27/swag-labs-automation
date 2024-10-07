package autotests.TestScenarios;

import autotests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static business.data.CommonData.*;
import static business.data.UserData.*;

public class VisualUserTest extends BaseTest {
    @BeforeMethod
    public void loginAsVisualUser() throws InterruptedException {
        loginPage
                .fillInUsername(getVisualUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.isOnInventoryPage();
    }

    @Test
    // TC-9
    public void checkCartItemCountNumberAndResetOption(){
        inventoryPage
                .clickOnAddToCartButton(BACKPACK_ITEM_FULL_TITLE.substring(11))
                .clickOnAddToCartButton(FLEECE_JACKET_ITEM_FULL_TITLE.substring(11));
        Assert.assertEquals(inventoryPage.getCartBadgeItemCount(), 2);
        inventoryPage.openMenu();
        leftMenuComponent.clickOnReset();
        Assert.assertFalse(inventoryPage.isCartItemCountDisplayed());
    }

    @Test
    // TC-10
    public void checkSocialIconUrls(){
        Assert.assertEquals(inventoryPage.getTwitterSocialUrl(), twitterPageUrl);
        Assert.assertEquals(inventoryPage.getFacebookSocialUrl(), facebookPageUrl);
        Assert.assertEquals(inventoryPage.getLinkedinSocialUrl(), linkedinPageUrl);
    }

    @Test
    // TC-11
    public void checkAddToCartResetFunctionality(){
        inventoryPage
                .clickOnAddToCartButton(BACKPACK_ITEM_FULL_TITLE.substring(11))
                .clickOnAddToCartButton(FLEECE_JACKET_ITEM_FULL_TITLE.substring(11));
        Assert.assertEquals(inventoryPage.getCartBadgeItemCount(), 2);
        inventoryPage.openMenu();
        leftMenuComponent.clickOnReset();
        Assert.assertTrue(inventoryPage.verifyRemoveButtonVisibility(BACKPACK_ITEM_FULL_TITLE));
        Assert.assertTrue(inventoryPage.verifyRemoveButtonVisibility(FLEECE_JACKET_ITEM_FULL_TITLE));
    }

    @Test
    // TC-12
    public void checkRemoveButtonIsDisplayedInInventoryPageAfterAddingItToCartFromItemPage(){
        inventoryPage
                .clickOnItemByName(BACKPACK_ITEM_FULL_TITLE);
        itemPage
                .clickOnAddToCartButton()
                .openMenu();
        leftMenuComponent.clickOnAllItems(getDriver());
        Assert.assertTrue(inventoryPage.verifyRemoveButtonVisibility(BACKPACK_ITEM_FULL_TITLE));
        Assert.assertFalse(inventoryPage.verifyAddToCartButtonVisibility(getDriver(), BACKPACK_ITEM_FULL_TITLE));
        inventoryPage.openMenu();
        leftMenuComponent.clickOnReset();
    }
}
