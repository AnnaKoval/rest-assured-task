package dto.localForecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalWeatherDTO {

    String city_name;
    String datetime;

}
