package kz.erasyl.volunteerback.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kz.erasyl.volunteerback.models.enums.Role;

import java.util.Arrays;
import java.util.List;

public class ValidRoleValidator implements ConstraintValidator<ValidRole, Role> {
    private static final List<Role> VALID_ROLES = Arrays.asList(Role.ORGANIZATION, Role.VOLUNTEER);

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        return role != null && VALID_ROLES.contains(role);
    }
}
