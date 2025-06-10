package br.com.mubook.mubook.service.impl;

import br.com.mubook.mubook.repository.GenericRepository;
import br.com.mubook.mubook.service.GenericService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GenericServiceImpl<M, K, R extends GenericRepository<M, K>> implements GenericService<M, K> {

    protected final R repository;

    @Override
    public M save(M model) {
        return repository.save(model);
    }

    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @Override
    public M findById(K id) {
        return repository.findById(id);
    }

    @Override
    public void hardDeleteById(K id) {
        repository.hardDeleteById(id);
    }

    @Override
    public void hardDeleteAll(Iterable<K> id) {
        repository.hardDeleteAll(id);
    }

    @Override
    public void softDeleteById(K id) {

    }
}
