package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.ConvidadoEntity;
import br.com.mubook.mubook.jparepository.ConvidadoJpaRepository;
import br.com.mubook.mubook.mapper.ConvidadoEntityMapper;
import br.com.mubook.mubook.model.Convidado;
import br.com.mubook.mubook.service.ConvidadoService;
import org.springframework.stereotype.Service;

/**
 * Implementação da camada de serviço para Convidado.
 * A anotação @Service a registra como um componente de serviço do Spring.
 */
@Service
public class ConvidadoServiceImpl extends GenericServiceImpl<Convidado, Long, ConvidadoEntity>
        implements ConvidadoService {

    private final ConvidadoJpaRepository repository;
    private final ConvidadoEntityMapper mapper;

    public ConvidadoServiceImpl(ConvidadoJpaRepository repository, ConvidadoEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}