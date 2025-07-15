package br.com.mubook.mubook.jparepository.specifications;

import org.springframework.data.jpa.domain.Specification;
import br.com.mubook.mubook.entity.UsuarioEntity;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSpecifications {

    public static Specification<UsuarioEntity> comFiltros(String nome, String cpf) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null && !nome.isEmpty()) {
                predicates.add(
                        cb.like(cb.lower(root.get("pessoa").get("nome")), "%" + nome.toLowerCase() + "%")
                );
            }

            if (cpf != null && !cpf.isEmpty()) {
                predicates.add(
                        cb.like(cb.lower(root.get("pessoa").get("cpf")), "%" + cpf + "%")
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
