package br.org.fenae.jogosdafenae.api.v1.controller;

import br.org.fenae.jogosdafenae.api.v1.dto.EditionDTO;
import br.org.fenae.jogosdafenae.model.Edition;
import br.org.fenae.jogosdafenae.service.EditionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/editions")
@CrossOrigin(origins = "*")
@Validated
public class EditionController {

    private final EditionService editionService;

    @Autowired
    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    @GetMapping
    public List<EditionDTO> getAllEditions() {
        return editionService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditionDTO> getEditionById(@PathVariable String id) {
        return editionService.findById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EditionDTO createEdition(@Valid @RequestBody EditionDTO editionDto) {
        Edition edition = convertToEntity(editionDto);
        return convertToDto(editionService.save(edition));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditionDTO> updateEdition(@PathVariable String id, @RequestBody EditionDTO editionDto) {
        return editionService.findById(id)
                .map(edition -> {
                    updateEditionFromDto(edition, editionDto);
                    return ResponseEntity.ok(convertToDto(editionService.save(edition)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdition(@PathVariable String id) {
        return editionService.findById(id)
                .map(edition -> {
                    editionService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private EditionDTO convertToDto(Edition edition) {
        EditionDTO dto = new EditionDTO();
        dto.setId(edition.getId());
        dto.setTitle(edition.getTitle());
        dto.setStartDate(edition.getStartDate());
        dto.setEndDate(edition.getEndDate());
        dto.setBornFrom(edition.getBornFrom());
        dto.setBornTo(edition.getBornTo());
        dto.setAssociatedUntil(edition.getAssociatedUntil());
        dto.setLinkExpiryDate(edition.getLinkExpiryDate());
        dto.setLink(edition.getLink());
        dto.setTerms(edition.getTerms());
        dto.setActive(edition.isActive());
        return dto;
    }

    private Edition convertToEntity(EditionDTO dto) {
        Edition edition = new Edition();
        updateEditionFromDto(edition, dto);
        return edition;
    }

    private void updateEditionFromDto(Edition edition, EditionDTO dto) {
        edition.setTitle(dto.getTitle());
        edition.setStartDate(dto.getStartDate());
        edition.setEndDate(dto.getEndDate());
        edition.setBornFrom(dto.getBornFrom());
        edition.setBornTo(dto.getBornTo());
        edition.setAssociatedUntil(dto.getAssociatedUntil());
        edition.setLinkExpiryDate(dto.getLinkExpiryDate());
        edition.setLink(dto.getLink());
        edition.setTerms(dto.getTerms());
        edition.setActive(dto.isActive());
    }
}