# 009 - Frontend: Autenticación (Registro + Login)

## Estado: PENDIENTE

## Objetivo
Implementar las pantallas de registro y login, el contexto de autenticación
y la persistencia del token JWT.

## Tareas
- [ ] Crear tipos TypeScript (User, AuthResponse, RegisterRequest, LoginRequest)
- [ ] Crear `AuthContext` + `AuthProvider` (estado de sesión, token en localStorage)
- [ ] Crear servicio `authService.ts` (register, login via axios)
- [ ] Crear `RegisterPage` (formulario con validación + confirmación de password)
- [ ] Crear `LoginPage` (formulario + guardar token + redirect)
- [ ] Configurar rutas públicas vs protegidas (ProtectedRoute component)
- [ ] Agregar interceptor axios para incluir Bearer token automáticamente

## Dependencias
- 008 - Frontend setup
- 001/002/003 - Backend con auth funcional

## Resultado esperado
Usuario puede registrarse, loguearse, y acceder a rutas protegidas.
