package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.UsuarioEntity;
import br.com.mubook.mubook.jparepository.UsuarioJpaRepository;
import br.com.mubook.mubook.mapper.UsuarioEntityMapper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.service.UsuarioService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long, UsuarioEntity> implements UsuarioService {

    private final UsuarioJpaRepository repository;

    private final UsuarioEntityMapper mapper;

    public UsuarioServiceImpl(UsuarioJpaRepository repository, UsuarioEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Usuario> findByEmailOrCpf(String search) {
        if (!search.matches("\\d{11}") && !search.contains("@")) {
            throw new BadCredentialsException("Verifique o email ou o CPF informado");
        }
        return repository.findByEmailOrCpf(search).map(mapper::toModel);
    }
}
