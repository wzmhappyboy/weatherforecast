package com.zimingw.weather.vo;

import lombok.Data;

import java.io.Serializable;

/**

 * @author Ziming Wang
 * @create 2022-04-03
 */
@Data
public class WeatherResponseVO implements Serializable {
    private static final long serialVersionUID = -8483256225271502962L;
    private String City;
    private String updateTime;
    private String Weather;
    private int Temp;
    private int Wind;
}