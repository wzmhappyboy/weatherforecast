package com.zimingw.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 *      在启动时，RestTemplate会在classpath中有哪些依赖，已添加HttpClient依赖，
 *      所以，RestTemplate会默认把它作为默认的实现
 * @author zimingw
 * @create 2022-04-05
 */
@Configuration
public class RestConfig {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
}
