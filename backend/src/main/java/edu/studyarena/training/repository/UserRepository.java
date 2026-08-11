package edu.studyarena.training.repository;

import edu.studyarena.training.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de usuarios.
 *
 * Spring Data JPA se encarga de:
 * - Implementar TODA esta interfaz automáticamente (no escribís una clase).
 * - Generar las queries SQL a partir del nombre del método.
 * - Proveer save(), findById(), findAll(), delete(), etc. gratis.
 * - Manejar transacciones automáticamente.
 *
 * Vos solo declarás la firma del método y Spring genera la query.
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * SELECT * FROM users WHERE email = ?
     * Spring lo genera automáticamente por el nombre "findByEmail".
     */
    Optional<User> findByEmail(String email);

    /**
     * SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
     * FROM users u WHERE u.email = ?
     */
    boolean existsByEmail(String email);
}
