package br.org.fenae.jogosdafenae.service;

import br.org.fenae.jogosdafenae.interfaces.EditionValidator;
import br.org.fenae.jogosdafenae.model.Edition;
import br.org.fenae.jogosdafenae.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditionService {

    private final EditionRepository editionRepository;
    private final List<EditionValidator> validators;

    @Autowired
    public EditionService(EditionRepository editionRepository, List<EditionValidator> validators) {
        this.editionRepository = editionRepository;
        this.validators = validators;
    }

    public List<Edition> findAll() {
        return editionRepository.findAll();
    }

    public Optional<Edition> findById(String id) {
        return editionRepository.findById(id);
    }

    public Edition save(Edition edition) {
        validate(edition);
        return editionRepository.save(edition);
    }

    public void deleteById(String id) {
        editionRepository.deleteById(id);
    }

    private void validate(Edition edition) {
        for (EditionValidator validator : validators) {
            validator.validate(edition);
        }
    }
}