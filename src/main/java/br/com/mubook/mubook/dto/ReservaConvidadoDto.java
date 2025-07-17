package br.com.mubook.mubook.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class ReservaConvidadoDto {

    @NotEmpty(message = "A lista de IDs de convidados não pode ser vazia.")
    private List<Long> convidadosIds;
}