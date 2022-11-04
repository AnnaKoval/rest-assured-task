package dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ForecastDTO implements Serializable {

    String timezone;
    String city_name;
    double lat;
    String state_code;
    List<ParticularForecastDTO> data;
    String country_code;
    double lon;

}
