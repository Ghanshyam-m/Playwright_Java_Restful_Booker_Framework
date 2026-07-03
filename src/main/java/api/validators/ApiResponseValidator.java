package api.validators;

import com.microsoft.playwright.APIResponse;
import org.testng.Assert;

public final class ApiResponseValidator {

    private ApiResponseValidator() {
    }

    /**
     * Validate HTTP Status Code
     */
    public static void validateStatusCode(
            APIResponse response,
            int expectedStatus) {

        Assert.assertEquals(
                response.status(),
                expectedStatus,
                "Unexpected Status Code");

    }

    /**
     * Validate Successful Response
     */
    public static void validateSuccess(
            APIResponse response) {

        Assert.assertTrue(
                response.ok(),
                "Request was not successful");

    }

    /**
     * Validate Content-Type
     */
    public static void validateContentType(
            APIResponse response,
            String expectedType) {

        String contentType =
                response.headers()
                        .getOrDefault(
                                "content-type",
                                "");

        Assert.assertTrue(
                contentType.contains(expectedType),
                "Invalid Content-Type : " + contentType);

    }

    /**
     * Validate Header
     */
    public static void validateHeader(
            APIResponse response,
            String headerName,
            String expectedValue) {

        Assert.assertEquals(

                response.headers().get(headerName),

                expectedValue,

                "Header Validation Failed"

        );

    }

    /**
     * Validate Response Time
     */
    public static void validateResponseTime(
            long responseTime,
            long maxTime) {

        Assert.assertTrue(

                responseTime <= maxTime,

                "Response Time exceeded : "
                        + responseTime + " ms"

        );

    }

}