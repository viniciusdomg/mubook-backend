package br.com.mubook.mubook.dto;

import java.util.List;

public record PageResponse<T>(List<T> content, Long totalElements, int totalPages, int size, int number,
                              Boolean first, Boolean last, Boolean empty) {
}
