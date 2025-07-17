package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.QuadraEntity;
import br.com.mubook.mubook.entity.ReservasDisponiveisEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservasDisponiveisJpaRepository extends GenericRepository<ReservasDisponiveisEntity, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ReservasDisponiveisEntity r WHERE r.dataHora < :data")
    void deleteByDataHoraBefore( LocalDateTime data);

    @Query("SELECT COUNT(r) > 0 FROM ReservasDisponiveisEntity r WHERE r.quadra = :quadra AND r.dataHora = :dataHora")
    boolean existsByQuadraAndDataHora(QuadraEntity quadra, LocalDateTime dataHora);

    List<ReservasDisponiveisEntity> quadra(QuadraEntity quadra);
}
