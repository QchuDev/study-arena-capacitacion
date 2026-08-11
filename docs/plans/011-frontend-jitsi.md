# 011 - Frontend: Integración Jitsi (Videollamada)

## Estado: PENDIENTE

## Objetivo
Implementar la vista de videollamada usando @jitsi/react-sdk,
solicitando el token justo antes de montar el componente.

## Tareas
- [ ] Crear tipo `JitsiAccess` (domain, roomName, token, expiresAt)
- [ ] Crear servicio `jitsiService.ts` (getJitsiAccess via POST /meetings/{id}/access)
- [ ] Crear componente `JitsiRoom` (encapsula <JitsiMeeting />, recibe props)
- [ ] Crear `VideoCallPage` (solicita access → monta JitsiRoom)
- [ ] Manejar estados de carga y error al pedir el token
- [ ] Configurar iframe style (100% width/height)
- [ ] Probar flujo completo: login → reunión → unirse → videollamada

## Flujo
1. Usuario en MeetingDetailPage presiona "Unirse"
2. Frontend hace POST /api/meetings/{id}/access (con Bearer token)
3. Backend responde con {domain, roomName, token, expiresAt}
4. Frontend monta <JitsiMeeting domain={} roomName={} jwt={} />
5. JaaS valida el token y permite el ingreso

## Dependencias
- 010 - Frontend meetings
- 005 - Backend Jitsi integration

## Resultado esperado
Dos usuarios en la misma reunión entran a la misma sala de videollamada.
