package br.org.fenae.jogosdafenae.api.v1.controller;

import br.org.fenae.jogosdafenae.api.v1.dto.ApcefDTO;
import br.org.fenae.jogosdafenae.model.Apcef;
import br.org.fenae.jogosdafenae.service.ApcefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/apcef")
@CrossOrigin(origins = "*")
@Validated
public class ApcefController {

    private final ApcefService apcefService;

    @Autowired
    public ApcefController(ApcefService apcefService) {
        this.apcefService = apcefService;
    }

    @GetMapping
    public List<ApcefDTO> getAllApcefs() {
        return apcefService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApcefDTO> getApcefById(@PathVariable String id) {
        return apcefService.findById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ApcefDTO createApcef(@RequestBody ApcefDTO apcefDto) {
        Apcef apcef = convertToEntity(apcefDto);
        return convertToDto(apcefService.save(apcef));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApcefDTO> updateApcef(@PathVariable String id, @RequestBody ApcefDTO apcefDto) {
        return apcefService.findById(id)
                .map(apcef -> {
                    updateApcefFromDto(apcef, apcefDto);
                    return ResponseEntity.ok(convertToDto(apcefService.save(apcef)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApcef(@PathVariable String id) {
        return apcefService.findById(id)
                .map(apcef -> {
                    apcefService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private ApcefDTO convertToDto(Apcef apcef) {
        ApcefDTO dto = new ApcefDTO();
        dto.setId(apcef.getId());
        dto.setTitle(apcef.getTitle());
        dto.setPresidentsCount(apcef.getPresidentsCount());
        dto.setSportDirectorsCount(apcef.getSportDirectorsCount());
        dto.setAthletesCount(apcef.getAthletesCount());
        dto.setParathletesCount(apcef.getParathletesCount());
        dto.setTechniciansCount(apcef.getTechniciansCount());
        dto.setBeachTennisAthletesCount(apcef.getBeachTennisAthletesCount());
        return dto;
    }

    private Apcef convertToEntity(ApcefDTO dto) {
        Apcef apcef = new Apcef();
        updateApcefFromDto(apcef, dto);
        return apcef;
    }

    private void updateApcefFromDto(Apcef apcef, ApcefDTO dto) {
        apcef.setTitle(dto.getTitle());
        apcef.setPresidentsCount(dto.getPresidentsCount());
        apcef.setSportDirectorsCount(dto.getSportDirectorsCount());
        apcef.setAthletesCount(dto.getAthletesCount());
        apcef.setParathletesCount(dto.getParathletesCount());
        apcef.setTechniciansCount(dto.getTechniciansCount());
        apcef.setBeachTennisAthletesCount(dto.getBeachTennisAthletesCount());
    }
}
