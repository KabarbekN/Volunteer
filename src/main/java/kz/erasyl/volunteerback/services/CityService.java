package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.models.enums.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    public List<City> getAllCities(){
        return List.of(City.values());
    }
}
