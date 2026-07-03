package api.booking;

import api.base.ApiBaseTest;
import api.builders.BookingBuilder;
import api.models.request.BookingRequest;
import api.models.response.BookingIdResponse;
import api.models.response.BookingResponse;
import api.validators.BookingValidator;

import org.testng.annotations.Test;

public class GetBookingTest extends ApiBaseTest {

    @Test

    public void getBookingTest() {

        BookingRequest request =
                BookingBuilder.defaultBooking();

        BookingIdResponse created =

                bookingService.createBooking(request);

        BookingResponse booking =

                bookingService.getBooking(

                        created.getBookingid()

                );

        BookingValidator.validateFirstName(

                booking,

                "John"

        );

        BookingValidator.validateLastName(

                booking,

                "Smith"

        );

    }

}