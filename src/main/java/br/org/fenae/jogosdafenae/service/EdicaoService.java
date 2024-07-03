package br.org.fenae.jogosdafenae.service;

import br.org.fenae.jogosdafenae.model.Edicao;
import br.org.fenae.jogosdafenae.repository.EdicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdicaoService {

    private final EdicaoRepository edicaoRepository;

    @Autowired
    public EdicaoService(EdicaoRepository edicaoRepository) {
        this.edicaoRepository = edicaoRepository;
    }

    public List<Edicao> findAll() {
        return edicaoRepository.findAll();
    }

    public Optional<Edicao> findById(String id) {
        return edicaoRepository.findById(id);
    }

    public Edicao save(Edicao edicao) {
        return edicaoRepository.save(edicao);
    }

    public void deleteById(String id) {
        edicaoRepository.deleteById(id);
    }
}