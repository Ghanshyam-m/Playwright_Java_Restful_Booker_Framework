package api.builders;

import api.models.request.BookingDates;
import api.models.request.BookingRequest;

public final class BookingBuilder {

    private BookingBuilder() {
        // Prevent instantiation
    }

    public static BookingRequest defaultBooking() {

        BookingDates bookingDates = new BookingDates(
                "2026-08-10",
                "2026-08-15"
        );

        return new BookingRequest(
                "John",
                "Smith",
                250,
                true,
                bookingDates,
                "Breakfast"
        );
    }
}