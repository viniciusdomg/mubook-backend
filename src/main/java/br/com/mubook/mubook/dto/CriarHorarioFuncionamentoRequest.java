package br.com.mubook.mubook.dto;

import java.util.List;

public record CriarHorarioFuncionamentoRequest(List<String> diasDaSemana, Integer tipoQuadraId,
                                               String abertura, String fechamento) {
}
