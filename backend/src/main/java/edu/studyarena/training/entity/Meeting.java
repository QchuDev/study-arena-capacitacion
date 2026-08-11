package edu.studyarena.training.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Representa una reunión creada por un usuario.
 *
 * JPA se encarga de:
 * - Mapear esta clase a la tabla "meetings" en PostgreSQL.
 * - Generar el ID (UUID) automáticamente.
 * - Gestionar la relación con User (@ManyToOne) creando la FK en la tabla.
 * - Persistir/recuperar los datos sin escribir SQL manual.
 */
@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    /**
     * Identificador único de la sala Jitsi.
     * Se genera una sola vez al crear la reunión.
     * Debe ser URL-safe y difícil de adivinar.
     */
    @Column(nullable = false, unique = true)
    private String jitsiRoomId;

    /**
     * Relación Many-to-One: muchas reuniones pueden pertenecer a un usuario.
     * JPA crea automáticamente la columna "owner_id" como FK en la tabla meetings.
     */
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private Instant createdAt;

    // Constructor vacío requerido por JPA
    protected Meeting() {}

    public Meeting(String name, String description, LocalDateTime dateTime,
                   String jitsiRoomId, User owner) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.jitsiRoomId = jitsiRoomId;
        this.owner = owner;
        this.createdAt = Instant.now();
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getJitsiRoomId() {
        return jitsiRoomId;
    }

    public User getOwner() {
        return owner;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
