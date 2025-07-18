package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.HistoricoReservasEntity;
import br.com.mubook.mubook.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoReservasJpaRepository extends GenericRepository<HistoricoReservasEntity, Long>, JpaSpecificationExecutor<HistoricoReservasEntity> {
    boolean existsByUsuarioIdAndStatus(Long idUsuario, StatusReserva status);
}