package br.com.mubook.mubook.jparepository.specifications;

import br.com.mubook.mubook.entity.QuadraEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class QuadraSpecifications {

    public static Specification<QuadraEntity> comFiltros(String tipoQuadra) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tipoQuadra != null && !tipoQuadra.isEmpty()) {
                predicates.add(
                        cb.like(cb.lower(root.get("tipoQuadra").get("nome")), "%" + tipoQuadra.toLowerCase() + "%")
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
