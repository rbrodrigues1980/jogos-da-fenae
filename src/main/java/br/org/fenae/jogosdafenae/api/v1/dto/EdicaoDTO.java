package br.org.fenae.jogosdafenae.api.v1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EdicaoDTO {
    private String id;
    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativo;
}
