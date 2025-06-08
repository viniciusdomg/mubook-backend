package br.com.mubook.mubook.repository;

import java.util.List;

public interface GenericRepository<M, K> {

    M save(M model);

//    Iterable<M> saveAll(Iterable<M> model);

//    void saveAllDiscart(Iterable<M> model);

    List<M> findAll();

    M findById(K id);

    void hardDeleteById(K id);

    void hardDeleteAll(Iterable<K> id);

    void softDeleteById(K id);
}
