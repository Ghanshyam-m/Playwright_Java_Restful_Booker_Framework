package ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LockedUserTest extends BaseTest {

    @Test(
            description = "Verify locked user cannot login",
            groups = {"Regression", "Negative"}
    )
    public void verifyLockedUserLogin() {

        LoginPage loginPage = new LoginPage();

        loginPage
                .open()
                .loginExpectingFailure(
                        "locked_out_user",
                        "secret_sauce"
                );

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed."
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Locked user error message mismatch."
        );
    }
}