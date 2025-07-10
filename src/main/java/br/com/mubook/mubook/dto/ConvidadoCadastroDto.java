package br.com.mubook.mubook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para receber os dados de um novo convidado ao criar uma reserva.
 * O uso de 'record' garante que o objeto seja imutável e conciso.
 *
 * @param nome  O nome completo do convidado. Não pode ser nulo ou vazio.
 * @param email O email do convidado. Deve ser um formato de email válido.
 */
public record ConvidadoCadastroDto(
        @NotBlank(message = "O nome do convidado não pode ser vazio.")
        String nome,

        @Email(message = "O email do convidado deve ser válido.")
        @NotBlank(message = "O email do convidado não pode ser vazio.")
        String email
) {
}