package config;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {

        try (FileInputStream fis =
                     new FileInputStream(
                             FrameworkConstants.CONFIG_FILE)) {

            properties.load(fis);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties", e);

        }

    }

    private ConfigReader() {
    }

    public static String getString(String key) {

        return properties.getProperty(key);

    }

    public static int getInt(String key) {

        return Integer.parseInt(
                properties.getProperty(key));

    }

    public static boolean getBoolean(String key) {

        return Boolean.parseBoolean(
                properties.getProperty(key));

    }

}