# 004 - Manejo de Errores Centralizado

## Estado: PENDIENTE

## Objetivo
Transformar excepciones en respuestas HTTP consistentes usando @RestControllerAdvice.

## Tareas
- [ ] Crear excepciones custom: `UserAlreadyExistsException`, `UserNotFoundException`, `MeetingNotFoundException`, `InvalidCredentialsException`
- [ ] Crear `GlobalExceptionHandler` con @RestControllerAdvice
- [ ] Crear DTO `ErrorResponse` (status, message, timestamp)
- [ ] Mapear cada excepción a un código HTTP (400, 401, 404, 409, 500)
- [ ] Manejar `MethodArgumentNotValidException` (errores de @Valid)

## Dependencias
- 003 - Controllers

## Resultado esperado
Todas las respuestas de error tienen el mismo formato JSON y códigos HTTP apropiados.
