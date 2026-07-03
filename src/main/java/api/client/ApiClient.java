package api.client;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import java.util.Map;

public class ApiClient {

    public APIResponse get(String endpoint) {

        long start = System.currentTimeMillis();

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .get(endpoint);

        logResponse("GET", endpoint, null, response, start);

        return response;
    }

    public APIResponse get(String endpoint, Map<String, String> headers) {

        long start = System.currentTimeMillis();

        RequestOptions options = RequestOptions.create();

        if (headers != null) {
            headers.forEach(options::setHeader);
        }

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .get(endpoint, options);

        logResponse("GET", endpoint, null, response, start);

        return response;
    }

    public APIResponse post(String endpoint, Object body) {

        long start = System.currentTimeMillis();

        logRequest("POST", endpoint, body);

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .post(
                        endpoint,
                        RequestOptions.create()
                                .setData(body)
                );

        logResponse("POST", endpoint, body, response, start);

        return response;
    }

    public APIResponse post(String endpoint,
                            Object body,
                            Map<String, String> headers) {

        long start = System.currentTimeMillis();

        logRequest("POST", endpoint, body);

        RequestOptions options = RequestOptions.create()
                .setData(body);

        if (headers != null) {
            headers.forEach(options::setHeader);
        }

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .post(endpoint, options);

        logResponse("POST", endpoint, body, response, start);

        return response;
    }

    public APIResponse put(String endpoint,
                           Object body,
                           Map<String, String> headers) {

        long start = System.currentTimeMillis();

        logRequest("PUT", endpoint, body);

        RequestOptions options = RequestOptions.create()
                .setData(body);

        if (headers != null) {
            headers.forEach(options::setHeader);
        }

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .put(endpoint, options);

        logResponse("PUT", endpoint, body, response, start);

        return response;
    }

    public APIResponse patch(String endpoint,
                             Object body,
                             Map<String, String> headers) {

        long start = System.currentTimeMillis();

        logRequest("PATCH", endpoint, body);

        RequestOptions options = RequestOptions.create()
                .setMethod("PATCH")
                .setData(body);

        if (headers != null) {
            headers.forEach(options::setHeader);
        }

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .fetch(endpoint, options);

        logResponse("PATCH", endpoint, body, response, start);

        return response;
    }

    public APIResponse delete(String endpoint,
                              Map<String, String> headers) {

        long start = System.currentTimeMillis();

        RequestOptions options = RequestOptions.create();

        if (headers != null) {
            headers.forEach(options::setHeader);
        }

        APIResponse response = ApiRequestManager
                .getRequestContext()
                .delete(endpoint, options);

        logResponse("DELETE", endpoint, null, response, start);

        return response;
    }

    private void logRequest(String method,
                            String endpoint,
                            Object body) {

        System.out.println("\n======================================");
        System.out.println(method + " Request");
        System.out.println("Endpoint : " + endpoint);

        if (body != null) {
            System.out.println("Request Body :");
            System.out.println(body);
        }

        System.out.println("======================================");
    }

    private void logResponse(String method,
                             String endpoint,
                             Object body,
                             APIResponse response,
                             long startTime) {

        long executionTime = System.currentTimeMillis() - startTime;

        System.out.println("\n======================================");
        System.out.println(method + " Response");
        System.out.println("Endpoint : " + endpoint);
        System.out.println("Status : " + response.status());
        System.out.println("Status Text : " + response.statusText());

        System.out.println("\nHeaders:");

        response.headers().forEach((k, v) ->
                System.out.println(k + " : " + v));

        System.out.println("\nResponse Body:");
        System.out.println(response.text());

        System.out.println("\nExecution Time : " + executionTime + " ms");
        System.out.println("======================================");
    }
}