package com.zimingw.weather.service;

import com.zimingw.weather.vo.WeatherResponseVO;

/**
 * @author Ziming Wang
 * @create 2022-04-03
 */
public interface WeatherDataService {
    /**
     * @param cityId
     * @return
     */
    WeatherResponseVO getDataByCityId(String cityId);



}
