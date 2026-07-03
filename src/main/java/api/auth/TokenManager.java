package api.auth;

import api.services.AuthService;

import java.util.HashMap;
import java.util.Map;

public final class TokenManager {

    private static final ThreadLocal<String> TOKEN =
            new ThreadLocal<>();

    private TokenManager() {
    }

    /**
     * Returns a cached token or generates a new one.
     */
    public static String getToken() {

        if (TOKEN.get() == null) {

            AuthService authService =
                    new AuthService();

            TOKEN.set(
                    authService.generateToken()
            );

        }

        return TOKEN.get();

    }

    /**
     * Returns Authorization headers for authenticated requests.
     */
    public static Map<String, String> getAuthHeaders() {

        Map<String, String> headers =
                new HashMap<>();

        headers.put(
                "Cookie",
                "token=" + getToken()
        );

        headers.put(
                "Accept",
                "application/json"
        );

        headers.put(
                "Content-Type",
                "application/json"
        );

        return headers;

    }

    /**
     * Clears the cached token.
     */
    public static void clearToken() {

        TOKEN.remove();

    }

}