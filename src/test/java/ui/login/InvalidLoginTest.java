package ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @Test(
            description = "Verify invalid login",
            groups = {"Regression", "Negative"}
    )
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage();

        loginPage
                .open()
                .loginExpectingFailure(
                        "invalid_user",
                        "invalid_password"
                );

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed."
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Invalid error message."
        );
    }
}