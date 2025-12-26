package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    public static void loadProperties(String env) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/" + env + ".properties");
        prop.load(fis);
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


}
