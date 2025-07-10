package br.com.mubook.mubook.dto;

public record QuadraCreateDTO( String nome,
                               Integer tipoQuadraId,
                               Integer quantidadeMaxima,
                               String foto_url) {}