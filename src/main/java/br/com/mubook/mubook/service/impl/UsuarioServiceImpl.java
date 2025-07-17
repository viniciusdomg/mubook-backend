package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.dto.FiltrosUsuarioRequest;
import br.com.mubook.mubook.entity.UsuarioEntity;
import br.com.mubook.mubook.jparepository.UsuarioJpaRepository;
import br.com.mubook.mubook.jparepository.specifications.UsuarioSpecifications;
import br.com.mubook.mubook.mapper.UsuarioEntityMapper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.service.PessoaService;
import br.com.mubook.mubook.service.UsuarioService;
import br.com.mubook.mubook.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long, UsuarioEntity> implements UsuarioService {

    private final UsuarioJpaRepository repository;

    private final UsuarioEntityMapper mapper;

    private final PessoaService pessoaService;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioJpaRepository repository, UsuarioEntityMapper mapper, PessoaService pessoaService, PasswordEncoder passwordEncoder) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.pessoaService = pessoaService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findByEmailOrCpf(String search) {
        if (!search.matches("\\d{11}") && !search.contains("@")) {
            throw new BadCredentialsException("Verifique o email ou o CPF informado");
        }
        return repository.findByEmailOrCpf(search).map(mapper::toModel);
    }

    @Override
    public Page<Usuario> findAllWithFilters(FiltrosUsuarioRequest filtros, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<UsuarioEntity> entities = repository.findAll(
                UsuarioSpecifications.comFiltros(filtros.nome(), filtros.cpf()),
                pageable);

        List<Usuario> usuarios = mapper.toModel(entities.getContent());
        return PageUtils.mapPage(entities, usuarios);
    }

    @Override
    public Usuario findByPessoaId(Long id) {
        return mapper.toModel(repository.findByPessoa(id));
    }

    @Override
    public Usuario save(Usuario model) {
        if(existsByEmailOrCpf(model.getPessoa().getEmail(), model.getPessoa().getCpf())){
            throw new BadCredentialsException("Email ou CPF já cadastrado");
        }

        model.setAtivo(true);
        model.getPessoa().setAtivo(true);
        var pessoa = pessoaService.save(model.getPessoa());

        model.setPessoa(pessoa);
        model.setSenha(passwordEncoder.encode(model.getSenha()));
        UsuarioEntity entity = mapper.fromModel(model);
        UsuarioEntity savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity);
    }

    @Override
    public Usuario update(Usuario model) {
        return mapper.toModel(repository.save(mapper.fromModel(model)));
    }

    private Boolean existsByEmailOrCpf(String email, String cpf) {
        return repository.existsByEmailOrCpf(email, cpf);
    }
}
