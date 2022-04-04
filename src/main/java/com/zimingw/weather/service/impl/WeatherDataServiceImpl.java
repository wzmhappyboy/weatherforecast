package com.zimingw.weather.service.impl;

import com.alibaba.fastjson.JSON;

import com.zimingw.weather.constant.StatusCodeConstant;
import com.zimingw.weather.vo.WeatherResponseVO;
import com.zimingw.weather.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Ziming Wang
 * @create 2022-04-03
 */
@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URL = "https://devapi.qweather.com/v7/weather/now?key=8796ad13f8514d4e98222fbbe201be7d&location=";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponseVO getDataByCityId(String cityId) {
        //String uri = WEATHER_URL + cityId;

        return doGetWeather(cityId);
    }



    private WeatherResponseVO doGetWeather(String cityId) {
        WeatherResponseVO weatherResponseVO= new WeatherResponseVO();
        String strBody = null;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(WEATHER_URL+cityId, String.class);
        if (StatusCodeConstant.OK == responseEntity.getStatusCodeValue()) {
            strBody = responseEntity.getBody();
            JSONObject obj= JSON.parseObject(strBody);
           System.out.print("obj:"+obj);
            JSONObject nowWeather=JSON.parseObject(obj.getString("now"));
            weatherResponseVO.setWeather(nowWeather.getString("text"));
            switch (cityId){
                case "1BABF":
                    weatherResponseVO.setCity("sydney");
                    break;
                case "F7F3":
                    weatherResponseVO.setCity("Wollongong");
                    break;
                case "754D9":
                    weatherResponseVO.setCity("Melbourne");
                    break;
            }
            weatherResponseVO.setUpdateTime(obj.getString("updateTime"));
            weatherResponseVO.setTemp(Integer.parseInt(nowWeather.getString("temp")));
            weatherResponseVO.setWind(Integer.parseInt(nowWeather.getString("windSpeed")));

        }
        return weatherResponseVO;
    }



}