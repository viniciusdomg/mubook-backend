package br.com.mubook.mubook.jparepository.specifications;

import br.com.mubook.mubook.entity.ReservasDisponiveisEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservasDisponveisSpecifications {

    public static Specification<ReservasDisponiveisEntity> comFiltros(Long idTipoQuadra, LocalDate data, LocalTime hora) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (idTipoQuadra != null && idTipoQuadra > 0) {
                predicates.add(cb.equal(root.get("quadra").get("tipoQuadra").get("id"), idTipoQuadra));
            }

            LocalDate filtroData = (data != null) ? data : LocalDate.now();

            predicates.add(cb.between(
                    root.get("dataHora"),
                    LocalDateTime.of(filtroData, LocalTime.MIN),
                    LocalDateTime.of(filtroData, LocalTime.MAX)
            ));

            if (hora != null) {
                LocalDateTime inicio = LocalDateTime.of(filtroData, hora);
                LocalDateTime fim = inicio.plusMinutes(59);
                predicates.add(cb.between(root.get("dataHora"), inicio, fim));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
