package ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class EmptyUsernameTest extends BaseTest {

    @Test(
            description = "Verify login without username",
            groups = {"Regression", "Negative"}
    )
    public void verifyEmptyUsername() {

        LoginPage loginPage = new LoginPage();

        loginPage
                .open()
                .loginExpectingFailure(
                        "",
                        "secret_sauce"
                );

        Assert.assertTrue(
                loginPage.isErrorDisplayed()
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required"
        );
    }
}