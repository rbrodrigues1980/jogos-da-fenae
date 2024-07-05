package br.org.fenae.jogosdafenae.api.v1.dto;

import br.org.fenae.jogosdafenae.interfaces.ValidStartDateEndDate;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidStartDateEndDate
public class EditionDTO {
    private String id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate bornFrom;
    private LocalDate bornTo;
    private LocalDate associatedUntil;
    private LocalDate linkExpiryDate;
    private String link;
    private String terms;
    private boolean active;
}
