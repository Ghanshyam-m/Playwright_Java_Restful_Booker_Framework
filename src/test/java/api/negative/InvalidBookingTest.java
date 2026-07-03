package api.negative;

import api.base.ApiBaseTest;

import com.microsoft.playwright.APIResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidBookingTest extends ApiBaseTest {

    @Test

    public void invalidBookingIdTest() {

        APIResponse response =

                bookingService.getBookingResponse(

                        99999999

                );

        Assert.assertEquals(

                response.status(),

                404

        );

    }

}