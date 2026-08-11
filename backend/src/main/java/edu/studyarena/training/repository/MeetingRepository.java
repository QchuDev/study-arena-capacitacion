package edu.studyarena.training.repository;

import edu.studyarena.training.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de reuniones.
 *
 * Spring Data JPA genera la implementación completa.
 * Métodos heredados de JpaRepository que ya tenés gratis:
 * - save(Meeting) → INSERT o UPDATE
 * - findById(String) → SELECT por ID
 * - findAll() → SELECT * (listar todas)
 * - deleteById(String) → DELETE por ID
 * - count() → COUNT(*)
 */
public interface MeetingRepository extends JpaRepository<Meeting, String> {

    /**
     * SELECT * FROM meetings WHERE owner_id = ? ORDER BY created_at DESC
     */
    List<Meeting> findByOwnerIdOrderByCreatedAtDesc(String ownerId);

    /**
     * SELECT * FROM meetings ORDER BY date_time ASC
     */
    List<Meeting> findAllByOrderByDateTimeAsc();
}
