package utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadFromProperties {

    public synchronized static String readProperty(String key) {

        String value = "";
        try (InputStream input = Files.newInputStream(Paths.get("./src/test/resources/configuration/config.properties"))) {
            Properties prop = new Properties();
            prop.load(input);
            value = prop.getProperty(key);

        } catch (Exception e) {

        }
        return value;
    }
}
