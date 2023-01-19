package dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeatherDTO implements Serializable {

    String icon;
    String description;
    int code;
//stupid comment
    //2
}
