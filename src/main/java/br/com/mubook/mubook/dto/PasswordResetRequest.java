package br.com.mubook.mubook.dto;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO para requisição de reset de senha.
 * Contém apenas o CPF do usuário que deseja resetar a senha.
 */
public record PasswordResetRequest(@NotBlank String cpf) {}