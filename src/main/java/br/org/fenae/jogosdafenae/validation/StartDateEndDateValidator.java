package br.org.fenae.jogosdafenae.validation;

import br.org.fenae.jogosdafenae.api.v1.dto.EditionDTO;
import br.org.fenae.jogosdafenae.interfaces.ValidStartDateEndDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartDateEndDateValidator implements ConstraintValidator<ValidStartDateEndDate, EditionDTO> {

    @Override
    public void initialize(ValidStartDateEndDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(EditionDTO editionDTO, ConstraintValidatorContext context) {
        if (editionDTO.getStartDate() != null && editionDTO.getEndDate() != null) {
            return !editionDTO.getEndDate().isBefore(editionDTO.getStartDate());
        }
        return true;
    }
}
