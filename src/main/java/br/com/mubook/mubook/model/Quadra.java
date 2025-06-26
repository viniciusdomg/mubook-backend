package br.com.mubook.mubook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quadra {
    private Integer id;
    private String nome;
    private TipoQuadra tipoQuadra;
    private Integer quantidadeMaxima;
    private String foto_url;
}
