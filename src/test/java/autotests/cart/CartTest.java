package autotests.cart;

import autotests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static business.data.Messages.FIRST_NAME_REQUIRED_ERROR_MESSAGE;
import static business.data.UserData.getPassword;
import static business.data.UserData.getStandardUser;

public class CartTest extends BaseTest {
    @Test
    public void checkPurchaseWithoutFillingUserCredentials() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.goToCart();
        cartPage.goToCheckout(getDriver());
        yourInformationPage.clickOnContinueButton();
        Assert.assertEquals(yourInformationPage.getCommonElements().getErrorMessage(), FIRST_NAME_REQUIRED_ERROR_MESSAGE);
    }
}
