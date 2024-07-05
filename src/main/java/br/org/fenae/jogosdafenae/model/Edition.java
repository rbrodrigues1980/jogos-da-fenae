package br.org.fenae.jogosdafenae.model;

import br.org.fenae.jogosdafenae.model.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "edition")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edition extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "born_from")
    private LocalDate bornFrom;

    @Column(name = "born_to")
    private LocalDate bornTo;

    @Column(name = "associated_until", nullable = false)
    private LocalDate associatedUntil;

    @Column(name = "link_expiry_date")
    private LocalDate linkExpiryDate;

    @Column(name = "link")
    private String link;

    @Column(name = "terms", columnDefinition = "TEXT")
    private String terms;

    @Column(nullable = false)
    private boolean active;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Edition edition = (Edition) o;
        return getId() != null && Objects.equals(getId(), edition.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
