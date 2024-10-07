package autotests.e2e;

import autotests.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static business.data.CommonData.*;
import static business.data.Messages.ORDER_COMPLETE_SUCCESS_MESSAGE;
import static business.data.UserData.getPassword;
import static business.data.UserData.getStandardUser;

public class E2E extends BaseTest {
    @Test
    @Description("check the app")
    public void checkApp() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage
                .clickOnAddToCartButton(BIKE_LIGHT_ITEM_FULL_TITLE.substring(11))
                .clickOnAddToCartButton(BACKPACK_ITEM_FULL_TITLE.substring(11))
                .clickOnAddToCartButton(FLEECE_JACKET_ITEM_FULL_TITLE.substring(11));
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
