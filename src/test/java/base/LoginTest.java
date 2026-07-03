package base;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import factory.BrowserFactory;

public class LoginTest extends BaseTest {

    @Test
    public void login() {

        Page page =
                BrowserFactory.getPage();

        page.navigate("https://www.saucedemo.com");

    }

}