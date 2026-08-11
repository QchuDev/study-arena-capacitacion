# Training - Capacitación Lab IV

Aplicación web de reuniones con videollamadas (Jitsi Meet) para la capacitación de Laboratorio IV.

## Estructura

```
├── backend/          → API REST con Java + Spring Boot
├── frontend/         → SPA con React + TypeScript + Vite
├── docs/             → Consigna, specs y planes de desarrollo
└── docker-compose.yml
```

## Stack

| Componente | Tecnología |
|---|---|
| Backend | Java 21, Spring Boot 4, Spring Security, JPA, PostgreSQL |
| Frontend | React, TypeScript, Vite, Axios, shadcn/ui, Tailwind CSS |
| Videollamadas | Jitsi as a Service (JaaS) |
| Infraestructura | Docker, Docker Compose |

## Ejecución local

```bash
docker compose up --build
```

## Equipo

Study Arena - Laboratorio IV
