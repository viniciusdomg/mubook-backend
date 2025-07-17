package br.com.mubook.mubook.dto;

import br.com.mubook.mubook.enums.RoleUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarAtualizarUsuarioRequest(Long id,
                                           @NotBlank String nome,
                                           @NotBlank @Email String email,
                                           @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
                                           @NotBlank @Size(min = 8) String senha,
                                           RoleUser tipo) {
}
