package kz.erasyl.volunteerback.auth;
import jakarta.validation.constraints.*;
import kz.erasyl.volunteerback.models.enums.Role;
import kz.erasyl.volunteerback.validations.ValidRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Username is mandatory")
    @NotNull(message = "Username is mandatory")
    @Size(min = 6, message = "Username should be 6 characters long minimum")
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;

    @ValidRole(message = "zzzz")
    private Role role;

    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;
}
