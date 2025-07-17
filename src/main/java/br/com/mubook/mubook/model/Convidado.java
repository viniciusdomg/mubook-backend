package br.com.mubook.mubook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Convidado {
    private Long id;
    private Pessoa pessoa;
}
