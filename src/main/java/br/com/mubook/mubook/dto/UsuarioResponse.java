package br.com.mubook.mubook.dto;

public record UsuarioResponse(Long id,
                              String nome,
                              String cpf,
                              String email,
                              String tipo) {
}
