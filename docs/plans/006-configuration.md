# 006 - Configuración (application.yml + .env)

## Estado: PENDIENTE

## Objetivo
Centralizar toda la configuración de la app (DB, JWT, JaaS) en application.yml
con soporte de variables de entorno para no hardcodear secretos.

## Tareas
- [ ] Convertir `application.properties` → `application.yml`
- [ ] Configurar datasource PostgreSQL (url, user, password desde env vars)
- [ ] Configurar JPA (ddl-auto, show-sql, dialect)
- [ ] Configurar propiedades JWT app (secret, expiration)
- [ ] Configurar propiedades JaaS (app-id, kid, private-key-path)
- [ ] Crear `.env.example` con las variables requeridas
- [ ] Agregar `.env` al .gitignore

## Dependencias
- Ninguna (se puede hacer en paralelo con 001)

## Resultado esperado
La app arranca conectándose a PostgreSQL y leyendo config de variables de entorno.
