package br.com.mubook.mubook.mapper;

public interface GenericEntityMapper<M, E> {

    M toModel(E entity);

    Iterable<M> toModel(Iterable<E> entity);

    E fromModel(M model);

    Iterable<E> fromModel(Iterable<M> model);
}
