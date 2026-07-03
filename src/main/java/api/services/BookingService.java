package api.services;

import api.auth.TokenManager;
import api.client.ApiClient;
import api.endpoints.BookingEndpoints;
import api.models.request.BookingRequest;
import api.models.response.BookingIdResponse;
import api.models.response.BookingResponse;
import api.utils.JsonUtils;

import com.microsoft.playwright.APIResponse;

public class BookingService {

    private final ApiClient api = new ApiClient();

    /**
     * Create Booking
     */
    public BookingIdResponse createBooking(BookingRequest request) {

        try {

            APIResponse response = api.post(
                    BookingEndpoints.BOOKINGS,
                    request
            );

            return JsonUtils.mapper()
                    .readValue(
                            response.text(),
                            BookingIdResponse.class
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to create booking",
                    e);

        }

    }

    /**
     * Get Booking
     */
    public BookingResponse getBooking(int bookingId) {

        try {

            APIResponse response = api.get(
                    BookingEndpoints.BOOKING + bookingId
            );

            return JsonUtils.mapper()
                    .readValue(
                            response.text(),
                            BookingResponse.class
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to fetch booking",
                    e);

        }

    }

    /**
     * Get Raw API Response
     */
    public APIResponse getBookingResponse(int bookingId) {

        return api.get(
                BookingEndpoints.BOOKING + bookingId
        );
    }

    /**
     * Update Booking
     */
    public BookingResponse updateBooking(
            int bookingId,
            BookingRequest request) {

        try {

            APIResponse response = api.put(
                    BookingEndpoints.BOOKING + bookingId,
                    request,
                    TokenManager.getAuthHeaders()
            );

            return JsonUtils.mapper()
                    .readValue(
                            response.text(),
                            BookingResponse.class
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to update booking",
                    e);

        }

    }

    /**
     * Partial Update
     */
    public BookingResponse partialUpdateBooking(
            int bookingId,
            BookingRequest request) {

        try {

            APIResponse response = api.patch(
                    BookingEndpoints.BOOKING + bookingId,
                    request,
                    TokenManager.getAuthHeaders()
            );

            return JsonUtils.mapper()
                    .readValue(
                            response.text(),
                            BookingResponse.class
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to patch booking",
                    e);

        }

    }

    /**
     * Delete Booking
     */
    public APIResponse deleteBooking(int bookingId) {

        return api.delete(
                BookingEndpoints.BOOKING + bookingId,
                TokenManager.getAuthHeaders()
        );

    }

}