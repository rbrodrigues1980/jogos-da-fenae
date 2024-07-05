package br.org.fenae.jogosdafenae.interfaces;

import br.org.fenae.jogosdafenae.validation.StartDateEndDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StartDateEndDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStartDateEndDate {
    String message() default "End date must be equal or after the start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
