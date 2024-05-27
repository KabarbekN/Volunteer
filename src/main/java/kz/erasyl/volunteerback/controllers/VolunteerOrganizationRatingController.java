package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.models.CreateRatingDto;
import kz.erasyl.volunteerback.models.VolunteerOrganizationRating;
import kz.erasyl.volunteerback.services.VolunteerOrganizationRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class VolunteerOrganizationRatingController {
    private final VolunteerOrganizationRatingService service;

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody CreateRatingDto rating) {
        return ResponseEntity.ok(service.createRating(rating));
    }
}
