# 005 - Integración Jitsi (Backend)

## Estado: PENDIENTE

## Objetivo
Implementar el servicio que genera tokens JWT firmados con RSA para acceder
a salas de Jitsi as a Service (JaaS).

## Tareas
- [ ] Agregar dependencia `nimbus-jose-jwt` al build.gradle
- [ ] Crear `VideoConferenceAccessService` (interfaz + implementación)
- [ ] Crear DTO `VideoConferenceAccess` (domain, roomName, token, expiresAt)
- [ ] Implementar firma JWT con RSA (clave privada de JaaS)
- [ ] Configurar propiedades JaaS en application.yml (app-id, kid, ruta clave privada)
- [ ] Crear endpoint `POST /api/meetings/{meetingId}/access`
- [ ] Validar que el usuario autenticado puede acceder a la reunión

## Claims del JWT Jitsi
```json
{
  "aud": "jitsi",
  "iss": "chat",
  "sub": "<app-id>",
  "room": "<sala>",
  "exp": <timestamp>,
  "context": { "user": {...}, "features": {...}, "room": {...} }
}
```

## Dependencias
- 003 - Controllers (endpoint de meetings funcional)
- Cuenta JaaS creada con AppID, kid y clave privada RSA

## Resultado esperado
`POST /api/meetings/{id}/access` devuelve domain, roomName, token JWT y expiresAt.
