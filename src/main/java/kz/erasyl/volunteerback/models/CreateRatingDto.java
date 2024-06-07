package kz.erasyl.volunteerback.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateRatingDto {
    private Long organizationId;
    private Long volunteerId;
    private Integer volunteerRating;
    private Integer organizationRating;
    private String volunteerFeedback;
    private String organizationFeedback;

}
