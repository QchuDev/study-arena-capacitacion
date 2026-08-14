# 002 - Configuración de Spring Security + Filtro JWT

## Estado: PENDIENTE

## Objetivo
Configurar Spring Security para que los endpoints de auth sean públicos y
el resto requiera un JWT válido en el header Authorization.

## Tareas
- [x] Crear `SecurityConfig` (SecurityFilterChain, PasswordEncoder bean)
- [x] Crear `JwtAuthenticationFilter` (extrae token del header, valida, setea SecurityContext)
- [x] Crear `UserDetailsServiceImpl` (carga usuario desde DB para Spring Security)
- [x] Configurar endpoints públicos: POST /api/auth/**
- [x] Configurar endpoints protegidos: /api/meetings/**
- [x] Deshabilitar CSRF (es una API stateless)
- [x] Configurar CORS para el frontend

## Dependencias
- 001 - DTOs y Auth Service

## Resultado esperado
Requests sin token a /api/meetings → 401.
Requests con token válido → pasan al controller.

## Aprendido
- Dentro del SecurityConfig.java tenemos las configuraciones de los accesos de nuestros http y comm con front
