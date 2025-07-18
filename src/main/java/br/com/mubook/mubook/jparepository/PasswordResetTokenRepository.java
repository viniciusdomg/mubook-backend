package br.com.mubook.mubook.jparepository;

import br.com.mubook.mubook.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

    // Método para buscar um token pela string do token
    Optional<PasswordResetTokenEntity> findByToken(String token);
}