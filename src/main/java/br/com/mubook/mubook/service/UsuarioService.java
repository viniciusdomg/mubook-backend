package br.com.mubook.mubook.service;

import br.com.mubook.mubook.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioService extends GenericService<Usuario, Long> {

    Optional<Usuario> findByEmailOrCpf(String search);
}
