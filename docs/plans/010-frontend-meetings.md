# 010 - Frontend: Reuniones (CRUD + Listado)

## Estado: PENDIENTE

## Objetivo
Implementar las pantallas de listado, creación y detalle de reuniones.

## Tareas
- [ ] Crear tipos TypeScript (Meeting, CreateMeetingRequest)
- [ ] Crear servicio `meetingService.ts` (getMeetings, getMeeting, createMeeting)
- [ ] Crear `MeetingsPage` (listado con nombre, descripción, fecha, creador)
- [ ] Crear `CreateMeetingPage` (formulario + validación + redirect al detalle)
- [ ] Crear `MeetingDetailPage` (info de la reunión + botón "Unirse")
- [ ] Agregar navegación entre pantallas

## Dependencias
- 009 - Frontend auth (para tener sesión activa)
- 003 - Backend meetings endpoints

## Resultado esperado
Usuario logueado puede ver, crear y acceder al detalle de reuniones.
