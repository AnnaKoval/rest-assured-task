package common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesController {

    private final Properties properties = new Properties();
    private static final String PROPERTY_FILE_PATH = "src/main/resources/weather.properties";
    private static final String XRAPID_API_KEY = "X-RapidAPI-Key";
    private static final String XRAPID_API_HOST = "X-RapidAPI-Host";
    private static final String XRAPID_API_KEY_PROPERTY = "xrapid.api.key";
    private static final String XRAPID_API_HOST_PROPERTY = "xrapid.api.host";

    public PropertiesController() {
        File file = new File(PROPERTY_FILE_PATH);
        try {
            properties.load(new FileReader(file));
        } catch (final IOException e) {
            throw new IllegalStateException("Can't load properties", e);
        }
    }

    public Map<String, String> getKeyProperties() {
        Map<String, String> keyProp = new HashMap<>();
        keyProp.put(XRAPID_API_KEY, getProperty(XRAPID_API_KEY_PROPERTY));
        keyProp.put(XRAPID_API_HOST, getProperty(XRAPID_API_HOST_PROPERTY));
        return keyProp;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
