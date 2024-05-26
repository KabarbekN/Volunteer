package kz.erasyl.volunteerback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VolunteerBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolunteerBackApplication.class, args);
    }

}
