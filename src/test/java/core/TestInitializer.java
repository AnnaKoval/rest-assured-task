package core;

import common.PropertiesController;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestInitializer {

    private static PropertiesController propertiesController = new PropertiesController();
    private static Map<String, String> keyProperties = propertiesController.getKeyProperties();
    private static final String WEATHER_BIT_PATH = "https://weatherbit-v1-mashape.p.rapidapi.com/";
    private static final String USER_MAIL = "user.mail";
    private static final String USER_PASSWORD = "user.password";

    @Before
    public static RequestSpecification getRequestSpecification() {
        return given().contentType(ContentType.JSON)
                .baseUri(WEATHER_BIT_PATH)
                .auth().basic(propertiesController.getProperty(USER_MAIL), propertiesController.getProperty(USER_PASSWORD))
                .headers(keyProperties);
    }
}