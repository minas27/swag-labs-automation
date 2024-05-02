package autotests.dashboard;

import autotests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static business.Data.CommonData.FLEECE_JACKET_ITEM_FULL_TITLE;
import static business.Data.UserData.getPassword;
import static business.Data.UserData.getStandardUser;

public class InventoryTests extends BaseTest {
    @Test
    public void checkTheItemAddToCartBecomeRemove(){
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.clickOnItemByName(FLEECE_JACKET_ITEM_FULL_TITLE.substring(11));
        itemPage
                .clickOnAddToCartButton()
                .clickOnBackToProducts();
        Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed());
    }

}
