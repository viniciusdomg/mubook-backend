package br.com.mubook.mubook.model;

import br.com.mubook.mubook.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;

    private String senha;

    private TipoUsuario tipoUsuario;

    private Pessoa pessoa;
}
