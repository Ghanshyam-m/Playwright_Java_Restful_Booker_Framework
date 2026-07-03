package ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class EmptyPasswordTest extends BaseTest {

    @Test(
            description = "Verify login without entering password",
            groups = {"Regression", "Negative"}
    )
    public void verifyEmptyPassword() {

        // Arrange
        LoginPage loginPage = new LoginPage();

        // Act
        loginPage
                .open()
                .loginExpectingFailure(
                        "standard_user",
                        ""
                );

        // Assert
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed."
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Incorrect error message displayed."
        );
    }
}