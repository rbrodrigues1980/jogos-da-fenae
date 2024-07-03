package br.org.fenae.jogosdafenae.api.v1.controller;

import br.org.fenae.jogosdafenae.api.v1.dto.EdicaoDTO;
import br.org.fenae.jogosdafenae.model.Edicao;
import br.org.fenae.jogosdafenae.service.EdicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/edicoes")
@CrossOrigin(origins = "*")
public class EdicaoController {

    private final EdicaoService edicaoService;

    @Autowired
    public EdicaoController(EdicaoService edicaoService) {
        this.edicaoService = edicaoService;
    }

    @GetMapping
    public List<EdicaoDTO> getAllEdicoes() {
        return edicaoService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EdicaoDTO> getEdicaoById(@PathVariable String id) {
        return edicaoService.findById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EdicaoDTO createEdicao(@RequestBody EdicaoDTO edicaoDto) {
        Edicao edicao = convertToEntity(edicaoDto);
        return convertToDto(edicaoService.save(edicao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EdicaoDTO> updateEdicao(@PathVariable String id, @RequestBody EdicaoDTO edicaoDto) {
        return edicaoService.findById(id)
                .map(edicao -> {
                    updateEdicaoFromDto(edicao, edicaoDto);
                    return ResponseEntity.ok(convertToDto(edicaoService.save(edicao)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdicao(@PathVariable String id) {
        return edicaoService.findById(id)
                .map(edicao -> {
                    edicaoService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private EdicaoDTO convertToDto(Edicao edicao) {
        EdicaoDTO dto = new EdicaoDTO();
        dto.setId(edicao.getId());
        dto.setTitulo(edicao.getTitulo());
        dto.setDataInicio(edicao.getDataInicio());
        dto.setDataFim(edicao.getDataFim());
        dto.setAtivo(edicao.isAtivo());
        return dto;
    }

    private Edicao convertToEntity(EdicaoDTO dto) {
        Edicao edicao = new Edicao();
        updateEdicaoFromDto(edicao, dto);
        return edicao;
    }

    private void updateEdicaoFromDto(Edicao edicao, EdicaoDTO dto) {
        edicao.setTitulo(dto.getTitulo());
        edicao.setDataInicio(dto.getDataInicio());
        edicao.setDataFim(dto.getDataFim());
        edicao.setAtivo(dto.isAtivo());
    }
}