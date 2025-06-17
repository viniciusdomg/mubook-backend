package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.jparepository.GenericRepository;
import br.com.mubook.mubook.mapper.GenericEntityMapper;
import br.com.mubook.mubook.service.GenericService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GenericServiceImpl<M, K, E> implements GenericService<M, K> {

    protected final GenericRepository<E, K> repository;

    protected final GenericEntityMapper<M, E> mapper;

    @Override
    public M save(M model) {
        E entity = mapper.fromModel(model);
        E savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity);
    }

    @Override
    public List<M> findAll() {
        List<E> entities = repository.findAll();
        return mapper.toModel(entities);
    }

    @Override
    public M findById(K id) {
        E entity = repository.findById(id).orElse(null);
        return mapper.toModel(entity);
    }

    @Override
    public void hardDeleteById(K id) {

    }

    @Override
    public void hardDeleteAll(Iterable<K> id) {

    }

    @Override
    public void softDeleteById(K id) {

    }
}
