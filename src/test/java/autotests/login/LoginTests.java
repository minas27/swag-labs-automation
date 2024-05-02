package autotests.login;

import autotests.BaseTest;
import business.Data.Messages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static business.Data.UserData.*;

public class LoginTests extends BaseTest {

    @Test
    public void checkLoginForStandardUser(){
        loginPage
                .fillInUsername(getStandardUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
    }

    @Test
    public void checkLoginForLockedUser(){
        loginPage
                .fillInUsername(getLockedUser())
                .fillInPassword(getPassword())
                .clickOnLogin();
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                Messages.LOCKED_USER_ERROR_MESSAGE);
    }

    @Test
    public void checkUsernameRequiredMessage(){
        loginPage
                .clickOnLogin();
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                Messages.USERNAME_REQUIRED_ERROR_MESSAGE);
    }

    @Test
    public void checkCloseErrorMessageButton(){
        loginPage.clickOnLogin();

        loginPage.closeErrorMessage();

        Assert.assertFalse(loginPage.isErrorMessageDisplayed());
    }
    @Test
    public void checkLogoutForStandardUser(){
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
