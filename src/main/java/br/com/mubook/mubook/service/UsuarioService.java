package br.com.mubook.mubook.service;

import br.com.mubook.mubook.dto.FiltrosUsuarioRequest;
import br.com.mubook.mubook.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UsuarioService extends GenericService<Usuario, Long> {

    Usuario save(Usuario model);

    Usuario update (Usuario model);

    Optional<Usuario> findByEmailOrCpf(String search);

    Page<Usuario> findAllWithFilters(FiltrosUsuarioRequest filtros, int offset, int limit);

    Usuario findByPessoaId(Long id);
    void gerarTokenDeResetDeSenha(String cpf);
    void trocarSenha(String token, String novaSenha);
    public Long contarSocios();
    public Long contarAdministradores();
}
