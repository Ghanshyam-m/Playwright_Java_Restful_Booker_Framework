package api.booking;

import api.base.ApiBaseTest;
import api.builders.BookingBuilder;
import api.models.request.BookingRequest;
import api.models.response.BookingIdResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBookingTest extends ApiBaseTest {

    @Test

    public void createBookingTest() {

        BookingRequest request =
                BookingBuilder.defaultBooking();

        BookingIdResponse booking =

                bookingService.createBooking(request);

        Assert.assertTrue(

                booking.getBookingid() > 0

        );

    }

}