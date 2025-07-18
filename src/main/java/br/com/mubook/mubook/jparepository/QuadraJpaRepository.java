package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.QuadraEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadraJpaRepository extends GenericRepository<QuadraEntity, Integer>, JpaSpecificationExecutor<QuadraEntity> {
    @Query("SELECT COUNT(q) FROM QuadraEntity q")
    long countTotalQuadras();
}
