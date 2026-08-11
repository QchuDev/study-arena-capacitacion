# Kit de Herramientas - Capacitación Lab IV

## DevTools (Herramientas de desarrollo que usamos nosotros)

| Herramienta | Propósito | Notas |
|---|---|---|
| **IntelliJ IDEA / VS Code** | IDE principal | Para Java (IntelliJ recomendado) y React (VS Code) |
| **Git** | Control de versiones | Ramas, commits, PRs, merge |
| **GitHub** | Repositorio remoto | PRs, code reviews, hosting del código |
| **Spring Initializr** | Scaffolding del backend | https://start.spring.io/ |
| **npm / Node.js** | Runtime + package manager frontend | `npm create vite` para scaffolding |
| **Gradle (Groovy)** | Build tool del backend | Ya configurado en el proyecto |
| **Docker Desktop** | Contenedores locales | Para correr el stack completo |
| **Postman / Insomnia** | Testing de API | Probar endpoints manualmente |

---

## Sistemas / Servicios externos (ya resueltos, solo configurar)

| Sistema | Propósito | Qué tenemos que hacer |
|---|---|---|
| **PostgreSQL** | Base de datos relacional | Solo levantar con Docker, Spring Data hace el resto |
| **Jitsi as a Service (JaaS)** | Videollamadas | Crear app en consola JaaS, obtener AppID + kid + clave RSA |
| **Docker Compose** | Orquestación local | Definir `docker-compose.yml` con backend + DB |
| **8x8.vc** | Dominio de Jitsi | Infraestructura de video manejada por Jitsi, no por nosotros |

---

## Dependencias (Librerías y Frameworks)

### Backend (Java / Spring Boot)

| Dependencia | Propósito | ¿Implementar o ya resuelto? |
|---|---|---|
| `spring-boot-starter-webmvc` | API REST (controllers, endpoints) | **Ya incluida** - solo crear controllers |
| `spring-boot-starter-data-jpa` | ORM + repositorios | **Ya incluida** - crear entities e interfaces Repository |
| `spring-boot-starter-security` | Autenticación, filtros, seguridad | **Ya incluida** - configurar SecurityFilterChain + filtro JWT |
| `spring-boot-starter-validation` | Validación de DTOs (`@Valid`, `@NotBlank`) | **Ya incluida** - anotar DTOs |
| `postgresql` (runtime) | Driver de conexión a PostgreSQL | **Ya incluida** - solo configurar `application.yml` |
| **`io.jsonwebtoken:jjwt-api`** | Generación/validación de JWT (app auth) | ⚠️ **AGREGAR** - para tokens de sesión |
| **`io.jsonwebtoken:jjwt-impl`** | Implementación de JJWT | ⚠️ **AGREGAR** (runtime) |
| **`io.jsonwebtoken:jjwt-jackson`** | Serialización JSON para JJWT | ⚠️ **AGREGAR** (runtime) |
| **`com.nimbusds:nimbus-jose-jwt`** (alternativa) | Firma RSA para tokens Jitsi | ⚠️ **AGREGAR** - para firmar JWT de JaaS con RSA |

### Frontend (React / TypeScript / Vite)

| Dependencia | Propósito | ¿Implementar o ya resuelto? |
|---|---|---|
| **React + ReactDOM** | UI library | Viene con Vite template |
| **TypeScript** | Tipado estático | Viene con Vite template |
| **Vite** | Bundler + dev server | Scaffolding con `npm create vite` |
| **react-router-dom** | Routing / navegación SPA | ⚠️ **INSTALAR** |
| **axios** | Cliente HTTP para consumir la API | ⚠️ **INSTALAR** |
| **@jitsi/react-sdk** | Componente de videollamada Jitsi | ⚠️ **INSTALAR** |
| **shadcn/ui** | Componentes UI (buttons, forms, cards) | ⚠️ **INSTALAR** (requiere Tailwind CSS) |
| **Tailwind CSS** | Utilidades CSS | ⚠️ **INSTALAR** (requisito de shadcn) |

---

## Paso a paso detallado

### Fase 1: Setup del Backend

1. **Proyecto ya creado** con Spring Initializr (✅ ya está en el repo).
2. **Agregar dependencias JWT** al `build.gradle`:
   ```groovy
   implementation 'io.jsonwebtoken:jjwt-api:0.12.6'
   runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
   runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'
   // Para firmar tokens Jitsi con RSA:
   implementation 'com.nimbusds:nimbus-jose-jwt:9.40'
   ```
3. **Crear estructura de paquetes**:
   ```
   src/main/java/edu/studyarena/training/
   ├── configuration/    → SecurityConfig, CorsConfig
   ├── controller/       → AuthController, MeetingController
   ├── dto/              → RegisterRequest, LoginRequest, MeetingDTO, etc.
   ├── entity/           → User, Meeting
   ├── exception/        → GlobalExceptionHandler, custom exceptions
   ├── repository/       → UserRepository, MeetingRepository
   ├── security/         → JwtService, JwtAuthFilter, UserDetailsImpl
   └── service/          → AuthService, MeetingService, JitsiAccessService
   ```
4. **Implementar entidades** (`@Entity`): `User` y `Meeting` con JPA annotations.
5. **Implementar repositorios** (`extends JpaRepository`): queries derivadas.
6. **Implementar servicios**: lógica de negocio (registro, login, CRUD meetings).
7. **Implementar seguridad**:
   - `SecurityFilterChain` → endpoints públicos vs protegidos.
   - `JwtService` → generar/validar tokens HMAC (auth de app).
   - `JwtAuthFilter` → interceptar requests, extraer usuario del token.
   - `PasswordEncoder` → BCrypt para hashear contraseñas.
8. **Implementar controllers**: endpoints REST con `@RestController`.
9. **Implementar integración Jitsi**:
   - `VideoConferenceAccessService` → firma JWT RSA con clave privada de JaaS.
   - Endpoint `POST /api/meetings/{id}/access` → genera token temporal.
10. **Manejo de errores**: `@RestControllerAdvice` + excepciones custom.
11. **Configurar `application.yml`**: datasource, JWT secret, JaaS credentials.
12. **Crear Dockerfile** (multi-stage build):
    ```dockerfile
    FROM gradle:8-jdk21 AS build
    COPY . /app
    WORKDIR /app
    RUN gradle bootJar --no-daemon

    FROM eclipse-temurin:21-jre
    COPY --from=build /app/build/libs/*.jar app.jar
    ENTRYPOINT ["java", "-jar", "app.jar"]
    ```

### Fase 2: Setup del Frontend

1. **Crear proyecto**: `npm create vite@latest frontend -- --template react-ts`
2. **Instalar dependencias**:
   ```bash
   npm install axios react-router-dom @jitsi/react-sdk
   npm install -D tailwindcss @tailwindcss/vite
   npx shadcn@latest init
   ```
3. **Crear estructura de carpetas** (`src/api`, `src/pages`, `src/components`, etc.)
4. **Configurar variables de entorno**: `.env` con `VITE_API_URL=http://localhost:8080`
5. **Configurar Axios**: instancia base con interceptor para agregar `Authorization: Bearer <token>`.
6. **Implementar AuthContext**: estado global de sesión (token en localStorage/memory).
7. **Implementar páginas**:
   - `RegisterPage` → formulario + validación + `POST /api/auth/register`
   - `LoginPage` → formulario + `POST /api/auth/login` → guardar token
   - `MeetingsPage` → `GET /api/meetings` → listar reuniones
   - `CreateMeetingPage` → formulario + `POST /api/meetings`
   - `MeetingDetailPage` → `GET /api/meetings/{id}` → info + botón "Unirse"
   - `VideoCallPage` → `POST /api/meetings/{id}/access` → montar `<JitsiMeeting />`
8. **Configurar rutas** con `react-router-dom` (rutas protegidas + públicas).
9. **Componente Jitsi encapsulado**: recibe `domain`, `roomName`, `jwt` como props.

### Fase 3: Docker Compose

1. **Crear `docker-compose.yml`** en la raíz:
   ```yaml
   services:
     database:
       image: postgres:16
       environment:
         POSTGRES_DB: training
         POSTGRES_USER: ${DB_USER}
         POSTGRES_PASSWORD: ${DB_PASSWORD}
       ports:
         - "5432:5432"

     backend:
       build:
         context: .
         dockerfile: Dockerfile
       ports:
         - "${EXTERNAL_APP_PORT:-8080}:8080"
       depends_on:
         - database
       environment:
         SPRING_DATASOURCE_URL: jdbc:postgresql://database:5432/training
         SPRING_DATASOURCE_USERNAME: ${DB_USER}
         SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
   ```
2. **Crear `.env`** (no versionado) con variables sensibles.
3. **Probar**: `docker compose up --build`

---

## Resumen: ¿Qué implemento yo vs qué ya está resuelto?

| Aspecto | Estado |
|---|---|
| Infraestructura de video (Jitsi) | ✅ Resuelto por JaaS (8x8.vc) |
| Base de datos | ✅ PostgreSQL levantado con Docker |
| ORM / queries | ✅ Spring Data JPA genera las queries |
| Validaciones de input | ✅ Bean Validation (`@Valid`) |
| Hash de contraseñas | ✅ BCryptPasswordEncoder de Spring |
| Componente de video en frontend | ✅ `@jitsi/react-sdk` lo renderiza |
| UI components | ✅ shadcn/ui provee los building blocks |
| **Entidades y modelo de datos** | 🔨 **Implementar** |
| **Lógica de negocio (services)** | 🔨 **Implementar** |
| **Endpoints REST** | 🔨 **Implementar** |
| **Configuración de seguridad (JWT)** | 🔨 **Implementar** |
| **Firma de tokens Jitsi (RSA)** | 🔨 **Implementar** |
| **Páginas y formularios del frontend** | 🔨 **Implementar** |
| **Routing y auth context** | 🔨 **Implementar** |
| **Dockerfiles y compose** | 🔨 **Implementar** |
| **Manejo de errores centralizado** | 🔨 **Implementar** |
