package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.dto.FiltrosUsuarioRequest;
import br.com.mubook.mubook.entity.PasswordResetTokenEntity;
import br.com.mubook.mubook.entity.UsuarioEntity;
import br.com.mubook.mubook.jparepository.PasswordResetTokenRepository;
import br.com.mubook.mubook.jparepository.UsuarioJpaRepository;
import br.com.mubook.mubook.jparepository.specifications.UsuarioSpecifications;
import br.com.mubook.mubook.mapper.UsuarioEntityMapper;
import br.com.mubook.mubook.model.Usuario;
import br.com.mubook.mubook.service.EmailService;
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
import java.util.UUID;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long, UsuarioEntity> implements UsuarioService {

    private final UsuarioJpaRepository repository;
    private final PasswordResetTokenRepository tokenRepository; // 1. Injetar novo repo
    private final UsuarioEntityMapper mapper;
    private final PessoaService pessoaService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UsuarioServiceImpl(UsuarioJpaRepository repository, PasswordResetTokenRepository tokenRepository, UsuarioEntityMapper mapper, PessoaService pessoaService, PasswordEncoder passwordEncoder, EmailService emailService) {
        super(repository, mapper);
        this.repository = repository;
        this.tokenRepository = tokenRepository;
        this.mapper = mapper;
        this.pessoaService = pessoaService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
    @Transactional
    public void gerarTokenDeResetDeSenha(String cpf) {
        UsuarioEntity usuarioEntity = repository.findByEmailOrCpf(cpf)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado com o CPF informado."));

        String tokenString = UUID.randomUUID().toString();
        PasswordResetTokenEntity tokenEntity = new PasswordResetTokenEntity(tokenString, usuarioEntity);

        tokenRepository.save(tokenEntity);

        emailService.enviarEmailRecuperacaoSenha(usuarioEntity.getPessoa().getEmail(), tokenString);
    }

    @Override
    @Transactional
    public void trocarSenha(String token, String novaSenha) {
        PasswordResetTokenEntity tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Token inválido ou não encontrado."));

        if (tokenEntity.isExpired()) {
            tokenRepository.delete(tokenEntity); // Remove o token expirado
            throw new BadCredentialsException("Token expirado.");
        }

        UsuarioEntity usuario = tokenEntity.getUsuario();
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        repository.save(usuario);

        // Importante: Invalida o token após o uso bem-sucedido
        tokenRepository.delete(tokenEntity);
    }

    public Long contarAdministradores() {
        return repository.countByRoleAdministrador();
    }

    public Long contarSocios() {
        return repository.countByRoleSocio();
    }

    @Override
    public Usuario update(Usuario model) {
        return mapper.toModel(repository.save(mapper.fromModel(model)));
    }

    private Boolean existsByEmailOrCpf(String email, String cpf) {
        return repository.existsByEmailOrCpf(email, cpf);
    }
}
