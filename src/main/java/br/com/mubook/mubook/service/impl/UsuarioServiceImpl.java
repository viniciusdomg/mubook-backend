package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.exception.AuthenticationInvalidException;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.repository.UsuarioRepository;
import br.com.mubook.mubook.service.UsuarioService;

import java.util.Optional;

public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long, UsuarioRepository> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Usuario> findByEmailOrCpf(String search) {
        if (!search.matches("\\d{11}") && !search.contains("@")) {
            throw new AuthenticationInvalidException("Verifique o email ou o CPF informado");
        }
        return repository.findByEmailOrCpf(search);
    }
}
