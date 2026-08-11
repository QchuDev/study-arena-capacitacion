# 001 - DTOs y Servicios de Autenticación

## Estado: PENDIENTE

## Objetivo
Implementar los DTOs (contratos de entrada/salida) y el servicio de autenticación
(registro + login) con hashing de contraseña.

## Tareas
- [ ] Crear `RegisterRequest` DTO (name, email, password)
- [ ] Crear `LoginRequest` DTO (email, password)
- [ ] Crear `AuthResponse` DTO (token, nombre, email)
- [ ] Implementar `AuthService` (registro con BCrypt, login con validación)
- [ ] Agregar dependencia JJWT al `build.gradle`
- [ ] Implementar `JwtService` (generar token, validar token, extraer claims)

## Dependencias
- Entidades User y Meeting (✅ completado)
- Repositorios (✅ completado)

## Resultado esperado
Poder registrar un usuario (contraseña hasheada en DB) y obtener un JWT al hacer login.
