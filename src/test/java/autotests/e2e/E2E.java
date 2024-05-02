package autotests.e2e;

import autotests.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static business.Data.CommonData.*;
import static business.Data.Messages.ORDER_COMPLETE_SUCCESS_MESSAGE;
import static business.Data.UserData.getPassword;
import static business.Data.UserData.getStandardUser;

public class E2E extends BaseTest {
    @Test
    @Description("check the app")
    public void checkApp(){
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage
                .clickOnAddToCartButton(getDriver(), BIKE_LIGHT_ITEM_FULL_TITLE.substring(11))
                .clickOnAddToCartButton(getDriver(), BACKPACK_ITEM_FULL_TITLE.substring(11))
                .clickOnAddToCartButton(getDriver(), FLEECE_JACKET_ITEM_FULL_TITLE.substring(11));
        inventoryPage.goToCart();
        cartPage.verifyItemVisibility(
                BIKE_LIGHT_ITEM_FULL_TITLE.substring(11),
                BACKPACK_ITEM_FULL_TITLE.substring(11),
                FLEECE_JACKET_ITEM_FULL_TITLE.substring(11));
        cartPage.goToCheckout(getDriver());
        yourInformationPage.setUserCredentials(
                "John",
                "Smith",
                "12345"
                ).clickOnContinueButton();
        Assert.assertTrue(checkoutOverviewPage
                .verifyItemTotal());
        Assert.assertEquals(checkoutOverviewPage.calculateTax(), checkoutOverviewPage.getTax());
        checkoutOverviewPage.clickOnFinish();
        Assert.assertEquals(checkoutOverviewPage.getSuccessMessage(), ORDER_COMPLETE_SUCCESS_MESSAGE);
    }
}
