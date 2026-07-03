package api.client;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import config.EnvironmentManager;

import java.util.HashMap;
import java.util.Map;

public final class ApiRequestManager {

    private static final ThreadLocal<Playwright> PLAYWRIGHT =
            new ThreadLocal<>();

    private static final ThreadLocal<APIRequestContext> REQUEST =
            new ThreadLocal<>();

    private ApiRequestManager() {
    }

    /**
     * Initialize API Context
     */
    public static void initialize() {

        if (REQUEST.get() != null) {
            return;
        }

        Playwright playwright = Playwright.create();

        PLAYWRIGHT.set(playwright);

        String baseUrl =
                EnvironmentManager.get("apiBaseUrl");

        Map<String, String> headers = new HashMap<>();

        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        APIRequestContext requestContext =
                playwright.request().newContext(

                        new APIRequest.NewContextOptions()

                                .setBaseURL(baseUrl)

                                .setExtraHTTPHeaders(headers)

                );

        REQUEST.set(requestContext);

        System.out.println("\n======================================");
        System.out.println("API Request Context Initialized");
        System.out.println("Base URL : " + baseUrl);
        System.out.println("======================================");
    }

    /**
     * Get API Context
     */
    public static APIRequestContext getRequestContext() {

        if (REQUEST.get() == null) {

            initialize();

        }

        return REQUEST.get();

    }

    /**
     * Close API Context
     */
    public static void close() {

        if (REQUEST.get() != null) {

            REQUEST.get().dispose();

            REQUEST.remove();

        }

        if (PLAYWRIGHT.get() != null) {

            PLAYWRIGHT.get().close();

            PLAYWRIGHT.remove();

        }

    }

}