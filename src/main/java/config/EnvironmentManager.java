package config;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class EnvironmentManager {

    private static final Properties envProperties =
            new Properties();

    static {

        String environment =
                ConfigReader.getString("environment");

        String path =
                FrameworkConstants.ENVIRONMENT_FOLDER
                        + environment
                        + ".properties";

        try (FileInputStream fis =
                     new FileInputStream(path)) {

            envProperties.load(fis);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load environment : "
                            + environment,
                    e);

        }

    }

    private EnvironmentManager() {
    }

    public static String get(String key) {

        return envProperties.getProperty(key);

    }

}