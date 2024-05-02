package autotests.cart;

import autotests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static business.Data.Messages.FIRST_NAME_REQUIRED_ERROR_MESSAGE;
import static business.Data.UserData.getPassword;
import static business.Data.UserData.getStandardUser;

public class CartTest extends BaseTest {
    @Test
    public void checkPurchaseWithoutFillingUserCredentials(){
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage.goToCart();
        cartPage.goToCheckout(getDriver());
        yourInformationPage.clickOnContinueButton();
        Assert.assertEquals(yourInformationPage.getErrorMessage(), FIRST_NAME_REQUIRED_ERROR_MESSAGE);
    }
}
