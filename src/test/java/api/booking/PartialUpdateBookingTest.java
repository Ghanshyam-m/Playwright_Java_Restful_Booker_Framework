package api.booking;

import api.base.ApiBaseTest;
import api.builders.BookingBuilder;
import api.models.response.BookingIdResponse;

import org.testng.annotations.Test;

public class PartialUpdateBookingTest extends ApiBaseTest {

    @Test

    public void partialUpdateBookingTest() {

        BookingIdResponse booking =

                bookingService.createBooking(

                        BookingBuilder.defaultBooking()

                );

        bookingService.partialUpdateBooking(

                booking.getBookingid(),

                BookingBuilder.defaultBooking()

        );

    }

}