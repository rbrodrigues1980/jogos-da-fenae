package br.org.fenae.jogosdafenae.model;

import br.org.fenae.jogosdafenae.model.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "apcef")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apcef extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(name = "presidents_count", nullable = false)
    private Integer presidentsCount;

    @Column(name = "sport_directors_count", nullable = false)
    private Integer sportDirectorsCount;

    @Column(name = "athletes_count", nullable = false)
    private Integer athletesCount;

    @Column(name = "parathletes_count", nullable = false)
    private Integer parathletesCount;

    @Column(name = "technicians_count", nullable = false)
    private Integer techniciansCount;

    @Column(name = "beach_tennis_athletes_count", nullable = false)
    private Integer beachTennisAthletesCount;
}
