package br.com.mubook.mubook.dto;


import java.time.LocalDateTime;
import java.util.List;

public record ReservaCreateDto (LocalDateTime dataHora, Integer quadraId, List<ConvidadoRequest> convidados) {}