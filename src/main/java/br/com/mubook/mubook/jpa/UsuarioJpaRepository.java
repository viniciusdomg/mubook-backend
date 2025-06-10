package br.com.mubook.mubook.jpa;

import br.com.mubook.mubook.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("from UsuarioEntity u where u.pessoa.email = :search OR u.pessoa.cpf = :search")
    UsuarioEntity findByEmailOrCpf(String search);

}
