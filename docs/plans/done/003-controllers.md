# 003 - Controllers REST

## Estado: PENDIENTE

## Objetivo
Exponer los endpoints HTTP de la aplicación.

## Endpoints
- `POST /api/auth/register` → registrar usuario
- `POST /api/auth/login` → autenticar y devolver JWT
- `GET /api/meetings` → listar reuniones (protegido)
- `GET /api/meetings/{id}` → detalle de reunión (protegido)
- `POST /api/meetings` → crear reunión (protegido)

## Tareas
- [x] Crear `AuthController` (register, login)
- [x] Crear `MeetingService` (crear, listar, buscar por id)
- [x] Crear DTOs de Meeting (CreateMeetingRequest, MeetingResponse)
- [x] Crear `MeetingController` (CRUD endpoints)
- [x] Generar jitsiRoomId (UUID URL-safe) al crear reunión
- [x] Validar DTOs con `@Valid` + annotations de Bean Validation

## Dependencias
- 001 - Auth Service
- 002 - Security Config

## Resultado esperado
API funcional que se puede testear con Postman/Insomnia.

## Aprendido
- @PostMapping y @GetMapping del Java Spring Boot, son los tags para aquellos metodos que 
responden a las requests http.
