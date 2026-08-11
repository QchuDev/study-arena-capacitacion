# 007 - Dockerfile + Docker Compose

## Estado: PENDIENTE

## Objetivo
Contenerizar el backend y orquestar el stack completo (backend + PostgreSQL)
con Docker Compose para desarrollo local.

## Tareas
- [ ] Crear `Dockerfile` multi-stage (build con Gradle + run con JRE)
- [ ] Crear `docker-compose.yml` (services: database + backend)
- [ ] Configurar variables de entorno en compose (desde .env)
- [ ] Configurar `depends_on` y healthcheck de PostgreSQL
- [ ] Probar `docker compose up --build` y verificar que la app arranca

## Dependencias
- 006 - Configuración (para que la app lea env vars correctamente)
- Backend funcional (al menos 001-003 completados)

## Resultado esperado
`docker compose up` levanta PostgreSQL + backend conectados y funcionando.
