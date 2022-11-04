package dto.localForecast;

import lombok.Data;

import java.util.List;

@Data
public class LocalForecastDTO {

    int count;
    List<LocalWeatherDTO> data;

}
