package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.entity.TipoQuadraEntity;
import br.com.mubook.mubook.exception.BussinesException;
import br.com.mubook.mubook.exception.ObjetoNullException;
import br.com.mubook.mubook.jparepository.TipoQuadraJpaRepository;
import br.com.mubook.mubook.mapper.TipoQuadraEntityMapper;
import br.com.mubook.mubook.model.TipoQuadra;
import br.com.mubook.mubook.service.TipoQuadraService;
import org.springframework.stereotype.Service;


@Service
public class TipoQuadraServiceImpl extends GenericServiceImpl<TipoQuadra, Integer, TipoQuadraEntity> implements TipoQuadraService {

    private final TipoQuadraJpaRepository repository;

    private final TipoQuadraEntityMapper mapper;

    public TipoQuadraServiceImpl(TipoQuadraJpaRepository repository, TipoQuadraEntityMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoQuadra save(TipoQuadra tipoQuadra){
        validate(tipoQuadra);
        tipoQuadra.setNome(capitalize(tipoQuadra.getNome()));
        return mapper.toModel(repository.save(mapper.fromModel(tipoQuadra)));
    }

//    @Override
//    public Page<TipoQuadra> findAllPageable(int offset, int limit) {
//        Pageable pageable = PageRequest.of(offset, limit);
//        Page<TipoQuadraEntity> entities = repository.findAll(pageable);
//        List<TipoQuadra> tipos = mapper.toModel(entities.getContent());
//        return PageUtils.mapPage(entities, tipos);
//    }

    private void validate(TipoQuadra tipo) {
        if (tipo == null) {
            throw new ObjetoNullException();
        }
        if (tipo.getNome().isEmpty()) {
            throw new BussinesException("O tipo da quadra não pode ser vazio");
        }

        String input = tipo.getNome().trim();
        if(repository.existsByNome(input)){
            throw new BussinesException("Não é possivel salvar um tipo de quadra com o nome " + input + " pois já existe!");
        }
    }

    private String capitalize(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        String trimmed = input.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }

}
