package kz.erasyl.volunteerback.services;

import kz.erasyl.volunteerback.exceptions.OperationNotPermittedException;
import kz.erasyl.volunteerback.models.*;
import kz.erasyl.volunteerback.repos.OrganizationRepository;
import kz.erasyl.volunteerback.repos.VolunteerOrganizationRatingRepository;
import kz.erasyl.volunteerback.repos.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VolunteerOrganizationRatingService {
    private final VolunteerOrganizationRatingRepository volunteerOrganizationRatingRepository;
    private final VolunteerService volunteerService;
    private final OrganizationService organizationService;
    private final OrganizationRepository organizationRepository;
    private final VolunteerRepository volunteerRepository;

    public boolean createRating(CreateRatingDto rating) {
        Organization organization = organizationService.findOrganizationById(rating.getOrganizationId());
        Volunteer volunteer = volunteerService.getVolunteerById(rating.getVolunteerId());
        if (volunteerOrganizationRatingRepository.findByOrganizationAndVolunteer(organization, volunteer).isPresent()){ /// update if we have such rating
            VolunteerOrganizationRating volunteerOrganizationRating = volunteerOrganizationRatingRepository.findByOrganizationAndVolunteer(organization, volunteer).get();
            if (rating.getVolunteerRating() != null){
                volunteerOrganizationRating.setVolunteerRating(rating.getVolunteerRating());
            }
            if (rating.getOrganizationRating() != null){
                volunteerOrganizationRating.setOrganizationRating(rating.getOrganizationRating());
            }
            if (rating.getVolunteerFeedback() != null){
                volunteerOrganizationRating.setVolunteerFeedback(rating.getVolunteerFeedback());
            }
            if (rating.getOrganizationFeedback() != null){
                volunteerOrganizationRating.setOrganizationFeedback(rating.getOrganizationFeedback());
            }

            volunteerOrganizationRatingRepository.save(volunteerOrganizationRating);
        }
        else{
            VolunteerOrganizationRating volunteerOrganizationRating = VolunteerOrganizationRating
                    .builder()
                    .organization(organization)
                    .volunteer(volunteer)
                    .organizationRating(rating.getOrganizationRating())
                    .volunteerRating(rating.getVolunteerRating())
                    .organizationFeedback(rating.getOrganizationFeedback())
                    .volunteerFeedback(rating.getVolunteerFeedback())
                    .build();
            volunteerOrganizationRatingRepository.save(volunteerOrganizationRating);

        }

        if (rating.getOrganizationRating() != null){
            calculateAverageRatingForOrganization(organization);
        }
        if (rating.getVolunteerRating() != null){
            calculateAverageRatingForVolunteer(volunteer);
        }
        return true;
    }

//    private void calculateAverageRatingForVolunteer(Volunteer volunteer) {
//        List<VolunteerOrganizationRating> ratings = volunteerOrganizationRatingRepository.findAllByVolunteer(volunteer);
//        Integer sum = ratings.stream().mapToInt(VolunteerOrganizationRating::getVolunteerRating).sum();
//        volunteer.setRating((float) (sum / ratings.size()));
//        volunteer.setNumberOfRates(ratings.size());
//        volunteerRepository.save(volunteer);
//    }
//
//    private void calculateAverageRatingForOrganization(Organization organization) {
//        List<VolunteerOrganizationRating> ratings = volunteerOrganizationRatingRepository.findAllByOrganization(organization);
//        Integer sum = ratings.stream().mapToInt(VolunteerOrganizationRating::getOrganizationRating).sum();
//        organization.setRating((float) (sum / ratings.size()));
//        organization.setNumberOfRates(ratings.size());
//        organizationRepository.save(organization);
//    }

    private void calculateAverageRatingForVolunteer(Volunteer volunteer) {
        List<VolunteerOrganizationRating> ratings = volunteerOrganizationRatingRepository.findAllByVolunteer(volunteer);
        int validRatingsCount = (int) ratings.stream().filter(r -> r.getVolunteerRating() != null).count();
        int sum = ratings.stream()
                .filter(r -> r.getVolunteerRating() != null)
                .mapToInt(VolunteerOrganizationRating::getVolunteerRating)
                .sum();
        float averageRating = validRatingsCount > 0 ? (float) sum / validRatingsCount : 0;
        volunteer.setRating(averageRating);
        volunteer.setNumberOfRates(validRatingsCount);
        volunteerRepository.save(volunteer);
    }

    private void calculateAverageRatingForOrganization(Organization organization) {
        List<VolunteerOrganizationRating> ratings = volunteerOrganizationRatingRepository.findAllByOrganization(organization);
        int validRatingsCount = (int) ratings.stream().filter(r -> r.getOrganizationRating() != null).count();
        int sum = ratings.stream()
                .filter(r -> r.getOrganizationRating() != null)
                .mapToInt(VolunteerOrganizationRating::getOrganizationRating)
                .sum();
        float averageRating = validRatingsCount > 0 ? (float) sum / validRatingsCount : 0;
        organization.setRating(averageRating);
        organization.setNumberOfRates(validRatingsCount);
        organizationRepository.save(organization);
    }


}
