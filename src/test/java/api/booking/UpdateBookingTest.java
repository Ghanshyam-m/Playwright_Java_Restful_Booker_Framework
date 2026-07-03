package api.booking;

import api.base.ApiBaseTest;
import api.builders.BookingBuilder;
import api.models.request.BookingRequest;
import api.models.response.BookingIdResponse;
import api.models.response.BookingResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateBookingTest extends ApiBaseTest {

    @Test

    public void updateBookingTest() {

        BookingIdResponse booking =

                bookingService.createBooking(

                        BookingBuilder.defaultBooking()

                );

        BookingRequest updated =

                new BookingRequest(

                        "David",

                        "Warner",

                        400,

                        true,

                        BookingBuilder
                                .defaultBooking()
                                .getBookingdates(),

                        "Lunch"

                );

        BookingResponse response =

                bookingService.updateBooking(

                        booking.getBookingid(),

                        updated

                );

        Assert.assertEquals(

                response.getFirstname(),

                "David"

        );

    }

}