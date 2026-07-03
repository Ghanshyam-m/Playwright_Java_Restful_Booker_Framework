package api.validators;

import api.models.response.BookingResponse;
import org.testng.Assert;

public final class BookingValidator {

    private BookingValidator() {
    }

    public static void validateFirstName(
            BookingResponse booking,
            String expected) {

        Assert.assertEquals(

                booking.getFirstname(),

                expected,

                "First Name mismatch"

        );

    }

    public static void validateLastName(
            BookingResponse booking,
            String expected) {

        Assert.assertEquals(

                booking.getLastname(),

                expected,

                "Last Name mismatch"

        );

    }

    public static void validatePrice(
            BookingResponse booking,
            int expectedPrice) {

        Assert.assertEquals(

                booking.getTotalprice(),

                expectedPrice,

                "Price mismatch"

        );

    }

    public static void validateDeposit(
            BookingResponse booking,
            boolean expected) {

        Assert.assertEquals(

                booking.isDepositpaid(),

                expected,

                "Deposit flag mismatch"

        );

    }

}