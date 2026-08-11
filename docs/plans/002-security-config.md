# 002 - Configuración de Spring Security + Filtro JWT

## Estado: PENDIENTE

## Objetivo
Configurar Spring Security para que los endpoints de auth sean públicos y
el resto requiera un JWT válido en el header Authorization.

## Tareas
- [ ] Crear `SecurityConfig` (SecurityFilterChain, PasswordEncoder bean)
- [ ] Crear `JwtAuthenticationFilter` (extrae token del header, valida, setea SecurityContext)
- [ ] Crear `UserDetailsServiceImpl` (carga usuario desde DB para Spring Security)
- [ ] Configurar endpoints públicos: POST /api/auth/**
- [ ] Configurar endpoints protegidos: /api/meetings/**
- [ ] Deshabilitar CSRF (es una API stateless)
- [ ] Configurar CORS para el frontend

## Dependencias
- 001 - DTOs y Auth Service

## Resultado esperado
Requests sin token a /api/meetings → 401.
Requests con token válido → pasan al controller.
