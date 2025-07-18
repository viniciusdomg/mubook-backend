package br.com.mubook.mubook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para requisição de troca de senha.
 * Contém o token de reset e a nova senha.
 */
public record ChangePasswordRequest(@NotBlank String token, @NotBlank @Size(min = 8) String novaSenha) {}