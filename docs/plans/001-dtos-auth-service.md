# 001 - DTOs y Servicios de Autenticación

## Estado: PENDIENTE

## Objetivo
Implementar los DTOs (contratos de entrada/salida) y el servicio de autenticación
(registro + login) con hashing de contraseña.

## Tareas
- [x] Crear `RegisterRequest` DTO (name, email, password)
- [x] Crear `LoginRequest` DTO (email, password)
- [x] Crear `AuthResponse` DTO (token, nombre, email)
- [x] Implementar `AuthService` (registro con BCrypt, login con validación)
- [x] Agregar dependencia JJWT al `build.gradle`
- [x] Implementar `JwtService` (generar token, validar token, extraer claims)

## Dependencias
- Entidades User y Meeting (✅ completado)
- Repositorios (✅ completado)

## Resultado esperado
Poder registrar un usuario (contraseña hasheada en DB) y obtener un JWT al hacer login.

## Aprendido
- BCrypt es un algoritmo para encryptar una contraseña. Es robusto pero lento, util contra ataques de fuerza bruta. Dos usuarios distintos con la misma contraseña producen un hash distinto por un salto random que sucede dentro, el valor del salto esta en el mismo hash.
- Sprinf tiene distintos tags para reconocer algunas clases que puede usar, como: @Entity, @Service, ...
- @Bean: notacion de metodos, le dice a Spring que el objeto que devuelve tiene que ser administrado por el. Ademas de darle disponibilidad.
- @Configuration: 
