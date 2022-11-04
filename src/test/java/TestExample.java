import common.Serializator;
import dto.ForecastDTO;
import dto.localForecast.LocalForecastDTO;
import org.junit.Test;

import java.io.InvalidObjectException;

import static common.DateController.getCurrentDate;
import static core.TestInitializer.getRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestExample {

    @Test
    public void verifyFiveDaysForecastTimezone() {
        ForecastDTO forecastDTO = given().spec(getRequestSpecification())
                .basePath("forecast/3hourly?")
                .queryParam("lat", "35.5")
                .queryParam("lon", "-78.5")
                .get()
                .then().extract().as(ForecastDTO.class);

        assertThat("Incorrect timezone!", forecastDTO.getTimezone().equals("America/New_York"));
    }

    @Test
    public void verifyCurrentLocationDate() {
        LocalForecastDTO localForecastDTO = given().spec(getRequestSpecification())
                .basePath("current?")
                .queryParam("lat", "38.5")
                .queryParam("lon", "-78.5")
                .get()
                .then().extract().as(LocalForecastDTO.class);

        String actualDate = localForecastDTO.getData().get(0).getDatetime();
        assertThat("Incorrect current date!", actualDate, equalTo(getCurrentDate()));
    }

    @Test
    public void verifyOneHourForecastStatusCode() {
        given().spec(getRequestSpecification())
                .basePath("forecast/minutely?")
                .queryParam("lat", "35.5")
                .queryParam("lon", "-78.5")
                .get()
                .then().assertThat().statusCode(200);
    }

    @Test
    public void verifyByHourForecast() {
        int expectedHours = 48;
        ForecastDTO forecastDTO = given().spec(getRequestSpecification())
                .basePath("forecast/hourly?")
                .queryParam("lat", "38.5")
                .queryParam("lon", "-78.5")
                .queryParam("hours", expectedHours)
                .get()
                .then().extract().as(ForecastDTO.class);

        assertThat("Expected hours value is different from actual!", expectedHours, equalTo(forecastDTO.getData().size()));
    }

    @Test
    public void verifySixteenthDaysForecastCountryCode() {
        ForecastDTO forecastDTO = given().spec(getRequestSpecification())
                .basePath("forecast/daily?")
                .queryParam("lat", "38.5")
                .queryParam("lon", "-78.5")
                .get()
                .then().extract().as(ForecastDTO.class);

        Serializator serializator = new Serializator();
        serializator.serialization(forecastDTO);

        ForecastDTO deserializedForecastDTO = null;
        try {
            deserializedForecastDTO = serializator.deserialization();
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        assertThat("Incorrect country code!", deserializedForecastDTO.getCountry_code().equals("US"));
    }
}
