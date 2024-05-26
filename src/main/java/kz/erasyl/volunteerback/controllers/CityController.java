package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.models.enums.City;
import kz.erasyl.volunteerback.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/")
    public List<City> getCityList() {
        return cityService.getAllCities();
    }
}
