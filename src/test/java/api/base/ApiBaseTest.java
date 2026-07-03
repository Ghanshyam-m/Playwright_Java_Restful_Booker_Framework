package api.base;

import api.auth.TokenManager;
import api.client.ApiRequestManager;
import api.services.BookingService;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ApiBaseTest {

    protected BookingService bookingService;

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        ApiRequestManager.initialize();

        bookingService = new BookingService();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        TokenManager.clearToken();

        ApiRequestManager.close();

    }

}