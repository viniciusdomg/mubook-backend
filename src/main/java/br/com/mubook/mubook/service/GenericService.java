package br.com.mubook.mubook.service;

import org.hibernate.annotations.SoftDelete;

import java.util.List;

public interface GenericService<M, K> {

    M save(M model);

//    Iterable<M> saveAll(Iterable<M> model);

//    void saveAllDiscart(Iterable<M> model);

    List<M> findAll();

    M findById(K id);

    void hardDeleteById(K id);

    void hardDeleteAll(Iterable<K> id);

    void softDeleteById(K id);

    void softDeleteAll(Iterable<K> id);
}
