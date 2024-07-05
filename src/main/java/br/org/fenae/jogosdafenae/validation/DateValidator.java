package br.org.fenae.jogosdafenae.validation;

import br.org.fenae.jogosdafenae.constants.ValidationMessages;
import br.org.fenae.jogosdafenae.interfaces.EditionValidator;
import br.org.fenae.jogosdafenae.model.Edition;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidator implements EditionValidator {

    @Override
    public void validate(Edition edition) {
        validateDates(edition.getStartDate(), edition.getEndDate(), ValidationMessages.END_DATE_BEFORE_START_DATE);
        validateDates(edition.getBornFrom(), edition.getBornTo(), ValidationMessages.BORN_TO_DATE_BEFORE_BORN_FROM_DATE);
    }

    private void validateDates(LocalDate startDate, LocalDate endDate, String errorMessage) {
        if (startDate == null || endDate == null) {
            return;
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
