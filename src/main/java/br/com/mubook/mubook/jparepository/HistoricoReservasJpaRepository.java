package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.HistoricoReservasEntity;
import br.com.mubook.mubook.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricoReservasJpaRepository extends GenericRepository<HistoricoReservasEntity, Long>, JpaSpecificationExecutor<HistoricoReservasEntity> {
    boolean existsByUsuarioIdAndStatus(Long idUsuario, StatusReserva status);

    @Query("SELECT COUNT(h) FROM HistoricoReservasEntity h WHERE h.dataHora >= :dataInicio")
    long countReservasUltimos30Dias(@Param("dataInicio") LocalDateTime dataInicio);
}