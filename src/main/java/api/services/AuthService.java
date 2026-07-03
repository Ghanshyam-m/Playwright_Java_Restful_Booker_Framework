package api.services;

import api.client.ApiClient;
import api.endpoints.BookingEndpoints;
import api.models.request.AuthRequest;
import api.models.response.AuthResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;

import config.EnvironmentManager;

public class AuthService {

    private final ApiClient apiClient = new ApiClient();

    private final ObjectMapper mapper = new ObjectMapper();

    public String generateToken() {

        try {

            AuthRequest request = new AuthRequest(
                    EnvironmentManager.get("username"),
                    EnvironmentManager.get("password")
            );

            APIResponse response =
                    apiClient.post(
                            BookingEndpoints.AUTH,
                            request
                    );

            if (response.status() != 200) {

                throw new RuntimeException(
                        "Authentication failed. Status Code : "
                                + response.status());

            }

            AuthResponse authResponse =
                    mapper.readValue(
                            response.text(),
                            AuthResponse.class
                    );

            return authResponse.getToken();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to generate authentication token",
                    e
            );

        }

    }

}