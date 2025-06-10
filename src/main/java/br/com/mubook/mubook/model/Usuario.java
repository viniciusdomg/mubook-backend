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

    private boolean ativo;

    private String senha;

    private TipoUsuario tipoUsuario;

    private Pessoa pessoa;

    public String getUsername() {
        return this.pessoa != null ? this.pessoa.getEmail() : null;
        // Ou você pode implementar lógica para retornar email ou CPF
    }
}
