package br.org.fenae.jogosdafenae.service;

import br.org.fenae.jogosdafenae.model.Apcef;
import br.org.fenae.jogosdafenae.repository.ApcefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApcefService {

    private final ApcefRepository apcefRepository;

    @Autowired
    public ApcefService(ApcefRepository apcefRepository) {
        this.apcefRepository = apcefRepository;
    }

    public List<Apcef> findAll() {
        return apcefRepository.findAll();
    }

    public Optional<Apcef> findById(String id) {
        return apcefRepository.findById(id);
    }

    public Apcef save(Apcef apcef) {
        return apcefRepository.save(apcef);
    }

    public void deleteById(String id) {
        apcefRepository.deleteById(id);
    }
}
