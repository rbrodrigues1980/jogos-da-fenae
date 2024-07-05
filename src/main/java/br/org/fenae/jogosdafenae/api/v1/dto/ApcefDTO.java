package br.org.fenae.jogosdafenae.api.v1.dto;

import lombok.Data;

@Data
public class ApcefDTO {
    private String id;
    private String title;
    private Integer presidentsCount;
    private Integer sportDirectorsCount;
    private Integer athletesCount;
    private Integer parathletesCount;
    private Integer techniciansCount;
    private Integer beachTennisAthletesCount;
}
