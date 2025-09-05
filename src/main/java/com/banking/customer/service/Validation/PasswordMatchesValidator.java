package com.banking.customer.service.Validation;

import com.banking.customer.service.DTO.CustomerRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, CustomerRequestDTO> {

    @Override
    public boolean isValid(CustomerRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getPassword() == null || dto.getConfirmPassword() == null) {
            return false;
        }
        return dto.getPassword().equals(dto.getConfirmPassword());
    }

}
