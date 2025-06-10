package br.com.mubook.mubook.mapper;

import java.util.List;

public interface GenericEntityMapper<M, E> {

    M toModel(E entity);

    List<M> toModel(List<E> entity);

    E fromModel(M model);

    List<E> fromModel(List<M> model);
}
