package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadFromProperties {

    public static String readProperty(String key) {

        String value = "";
        try (InputStream input = new FileInputStream("./src/test/resources/configuration/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            value = prop.getProperty(key);

        } catch (Exception e) {

        }
        return value;
    }
}
