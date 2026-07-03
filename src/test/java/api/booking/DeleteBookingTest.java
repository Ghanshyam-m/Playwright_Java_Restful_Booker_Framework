package api.booking;

import api.base.ApiBaseTest;
import api.builders.BookingBuilder;
import api.models.response.BookingIdResponse;

import com.microsoft.playwright.APIResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends ApiBaseTest {

    @Test

    public void deleteBookingTest() {

        BookingIdResponse booking =

                bookingService.createBooking(

                        BookingBuilder.defaultBooking()

                );

        APIResponse response =

                bookingService.deleteBooking(

                        booking.getBookingid()

                );

        Assert.assertEquals(

                response.status(),

                201

        );

    }

}