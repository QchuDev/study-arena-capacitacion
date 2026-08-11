# Plan de Desarrollo - Capacitación Lab IV

## Progreso

| # | Plan | Estado | Bloque |
|---|---|---|---|
| ✅ | Entidades JPA + Repositorios | COMPLETADO | Backend |
| 001 | [DTOs y Servicios de Auth](./001-dtos-auth-service.md) | PENDIENTE | Backend |
| 002 | [Security Config + Filtro JWT](./002-security-config.md) | PENDIENTE | Backend |
| 003 | [Controllers REST](./003-controllers.md) | PENDIENTE | Backend |
| 004 | [Manejo de Errores](./004-error-handling.md) | PENDIENTE | Backend |
| 005 | [Integración Jitsi Backend](./005-jitsi-backend.md) | PENDIENTE | Backend |
| 006 | [Configuración (yml + env)](./006-configuration.md) | PENDIENTE | Infra |
| 007 | [Dockerfile + Docker Compose](./007-docker.md) | PENDIENTE | Infra |
| 008 | [Setup Frontend](./008-frontend-setup.md) | PENDIENTE | Frontend |
| 009 | [Frontend Auth](./009-frontend-auth.md) | PENDIENTE | Frontend |
| 010 | [Frontend Meetings](./010-frontend-meetings.md) | PENDIENTE | Frontend |
| 011 | [Frontend Jitsi](./011-frontend-jitsi.md) | PENDIENTE | Frontend |

## Orden recomendado

```
006 (config) ──┐
               ├──→ 001 → 002 → 003 → 004 → 005  (Backend completo)
               │                                          │
008 (frontend setup) → 009 → 010 → 011                   │
                                                          │
007 (docker) ←────────────────────────────────────────────┘
```

## Notas
- 006 y 008 se pueden hacer en paralelo (son independientes).
- 007 (Docker) conviene dejarlo para cuando el backend ya funciona.
- El frontend necesita el backend corriendo para testear integración.
