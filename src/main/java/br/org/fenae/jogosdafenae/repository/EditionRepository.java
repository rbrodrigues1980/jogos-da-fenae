package br.org.fenae.jogosdafenae.repository;

import br.org.fenae.jogosdafenae.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepository extends JpaRepository<Edition, String> {
}
