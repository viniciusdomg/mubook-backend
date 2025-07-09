package br.com.mubook.mubook.dto;

import lombok.Data;

@Data
public class QuadraCreateDTO {
    private String nome;
    private Integer tipoQuadraId;
    private Integer quantidadeMaxima;
    private String foto_url;
}