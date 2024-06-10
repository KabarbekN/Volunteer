package kz.erasyl.volunteerback.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactUsRequest {
    private String name;
    private String email;
    private String message;
}
