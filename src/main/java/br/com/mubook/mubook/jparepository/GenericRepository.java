package br.com.mubook.mubook.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<E, K> extends JpaRepository<E, K>{

}
