# 007 - Dockerfile + Docker Compose

## Estado: PENDIENTE

## Objetivo
Contenerizar el backend y orquestar el stack completo (backend + PostgreSQL)
con Docker Compose para desarrollo local.

## Tareas
- [x] Crear `Dockerfile` multi-stage (build con Gradle + run con JRE)
- [x] Crear `docker-compose.yml` (services: database + backend)
- [x] Configurar variables de entorno en compose (desde .env)
- [x] Configurar `depends_on` y healthcheck de PostgreSQL
- [x] Probar `docker compose up --build` y verificar que la app arranca

## Dependencias
- 006 - Configuración (para que la app lea env vars correctamente)
- Backend funcional (al menos 001-003 completados)

## Resultado esperado
`docker compose up` levanta PostgreSQL + backend conectados y funcionando.

## Aprendido
- JRE: Java Runtime Environment
- Dockerfile: explicamos como van a ser la imagen que vamos a usar 
para los contenedores.
    - Imagen: template con las dependencias y especificaciones a usar.
    - Contenedor: instancia encapsulada y especifica corriendo la aplicacion.
    - Multi-stage: para que el contenedor resultado pese menos, primero compilamos en una etapa y luego solamente usamos el JRE con el jar para que ocupe menos espacio.
