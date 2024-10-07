package autotests.login;

import autotests.BaseTest;
import business.data.Messages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static business.data.UserData.*;

public class LoginTests extends BaseTest {

    @Test
    public void checkLoginForStandardUser() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
    }

    @Test
    public void checkLoginForLockedUser() throws InterruptedException {
        loginPage
                .fillInUsername(getLockedUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        Assert.assertEquals(
                loginPage.getCommonElements().getErrorMessage(),
                Messages.LOCKED_USER_ERROR_MESSAGE);
    }

    @Test
    public void checkUsernameRequiredMessage(){
        loginPage
                .clickOnLogin();
        Assert.assertEquals(
                loginPage.getCommonElements().getErrorMessage(),
                Messages.USERNAME_REQUIRED_ERROR_MESSAGE);
    }

    @Test
    public void checkCloseErrorMessageButton(){
        loginPage.clickOnLogin();

        loginPage.getCommonElements().closeErrorMessage();

        Assert.assertFalse(loginPage.getCommonElements().isErrorMessageDisplayed());
    }
    @Test
    public void checkLogoutForStandardUser() throws InterruptedException {
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        inventoryPage
                .openMenu();
        Assert.assertTrue(
                leftMenuComponent
                        .logOut(getDriver())
                        .isOnLoginPage());

    }

}
