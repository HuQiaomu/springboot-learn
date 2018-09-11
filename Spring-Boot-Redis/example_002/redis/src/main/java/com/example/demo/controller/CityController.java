package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex_hu
 * @date 18-8-27 下午1:20
 */

@RestController
public class CityController {

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "saveCity")
    public String saveCity(int cityId, String cityName) {
        City city = new City(cityId, cityName);
        redisService.setEx(cityId + "", city, 3);
//        redisService.set(cityId + "", city);
        return "success";
    }

    @GetMapping(value = "getCityById")
    public City getCity(int cityId) {
        City city = (City) redisService.get(cityId + "");
        return city;
    }

}
