# Requirements Document

## Introduction

This feature automates all business rules, integrity constraints, and transaction logic for the UCAB-Services platform directly in PostgreSQL using triggers, stored procedures, and PL/pgSQL functions. The "Segunda Entrega" focuses on implementing 7 key business rules from the project requirements at the database level, ensuring data integrity, transactional consistency, and security while providing proper integration with Spring Boot backend and Angular frontend.

## Glossary

- **System**: UCAB-Services Platform
- **Miembro**: University community member with credentials to access services
- **Sesión**: User login session tracking
- **Reservación**: Service reservation request
- **Espacio_Físico**: Physical space available for reservations
- **Solicitud_Servicio**: Service request instance
- **Factura**: Formal invoice for services consumed
- **Folio_Consumo**: Temporary consumption record before billing
- **Tarifa**: Service pricing based on member profile and time
- **ERE (Esquema de Relaciones Familiares)**: Family benefits scheme for administrative and teaching staff
- **Beneficiario**: Family member registered under ERE
- **TAI**: Tú-Ahorro-Infinito digital wallet system

## Requirements

### Requirement 1: Session Time Management

**User Story:** As a system administrator, I want accurate session tracking, so that I can monitor user activity and ensure security compliance.

#### Acceptance Criteria

1. WHEN a Miembro closes their session, THE Sistema SHALL set Hora_Fin in Historial_Sesiones to the current timestamp and log security alert
2. THE Sistema SHALL prevent Hora_Fin from being null for closed sessions
3. WHERE a session is active, THE Sistema SHALL automatically calculate session duration until one closure method completes, treating manual and automatic closures as sequential events
4. IF a session remains open beyond 24 hours, THEN THE Sistema SHALL force closure and log security alert

### Requirement 2: Reservation Collision Prevention

**User Story:** As a facilities manager, I want to prevent double-booking of spaces, so that resource allocation is efficient and conflict-free.

#### Acceptance Criteria

1. WHEN a new reservation is created, THE Sistema SHALL verify no overlapping time intervals exist for the same Espacio_Físico
2. WHILE validating reservations, THE Sistema SHALL consider same-day, same-space conflicts as invalid
3. IF a conflict is detected, THEN THE Sistema SHALL reject the reservation with descriptive error and keep the reservation pending until the error message can be delivered
4. WHERE multiple spaces are available, THE Sistema SHALL suggest alternative time slots
5. THE Sistema SHALL maintain real-time availability status for all Espacio_Físico entities

### Requirement 3: Service Resolution Time Calculation

**User Story:** As a service coordinator, I want accurate resolution time tracking, so that I can measure service efficiency and identify bottlenecks.

#### Acceptance Criteria

1. WHEN a Solicitud_Servicio is closed (completed or canceled), THE Sistema SHALL calculate resolution time as Fecha_Fin minus Fecha_Inicio and prevent service closure until resolution time is successfully calculated
2. WHILE calculating resolution time, THE Sistema SHALL exclude weekends (Saturday and Sunday)
3. WHERE public holidays are defined, THE Sistema SHALL exclude them from business day calculations
4. THE Sistema SHALL store calculated Tiempo_resolucion in the Solicitud_Servicio record only when calculation actually succeeds
5. IF Fecha_Inicio or Fecha_Fin are null, THEN THE Sistema SHALL not calculate resolution time

### Requirement 4: Monthly Billing Automation

**User Story:** As a billing administrator, I want automated monthly invoice generation, so that financial operations are timely and accurate.

#### Acceptance Criteria

1. WHEN the last day of the month is reached, THE Sistema SHALL identify all Folio_Consumo records with Estado_cierre = 'Abierto'
2. THE Sistema SHALL create Factura records for each identified Folio_Consumo that contains Item_consumo records
3. WHILE creating Factura, THE Sistema SHALL calculate Monto_total as sum of (Precio_unitario × Cantidad + Impuesto) for all Item_consumo in the Folio, and prevent invoices when all items have zero consumption
4. WHERE Folio_Consumo has no Item_consumo or all items have zero consumption, THE Sistema SHALL not create Factura
5. IF a Folio_Consumo total exceeds pre-defined limits, THEN THE Sistema SHALL flag for manual review
6. THE Sistema SHALL update Folio_Consumo.Estado_cierre to 'Cerrado' after successful Factura creation

### Requirement 5: Dynamic Pricing Application

**User Story:** As a financial manager, I want accurate service pricing based on time and user profile, so that billing reflects current rates and policies.

#### Acceptance Criteria

1. WHEN an Item_consumo is created, THE Sistema SHALL always determine Precio_unitario from Tarifa_Servicio history valid for the Fecha_cargo
2. WHILE determining price, THE Sistema SHALL consider the Miembro's current profile (Estudiante, Docente, etc.)
3. WHERE sede-specific pricing exists, THE Sistema SHALL apply the correct price for the service location
4. IF no valid Tarifa exists for the date, THEN THE Sistema SHALL use the most recent applicable Tarifa
5. THE Sistema SHALL validate that institutional service pricing considers entity, sede, and requester profile
6. WHERE discounts apply from Categoria_Fidelidad, THE Sistema SHALL apply them to the final price

### Requirement 6: ERE Family Relationship Validation

**User Story:** As a benefits administrator, I want automated validation of family relationships, so that ERE benefits are correctly assigned according to age rules.

#### Acceptance Criteria

1. WHEN a Beneficiario is registered or updated, THE Sistema SHALL validate age-based relationship constraints
2. WHILE a Beneficiario has Parentesco = 'Cargo_Menor', THE Sistema SHALL require age < 18 years
3. WHILE a Beneficiario has Parentesco = 'Cargo_Mayor', THE Sistema SHALL require age ≥ 18 years
4. WHERE age validation fails, THEN THE Sistema SHALL reject the registration with specific error message
5. THE Sistema SHALL only allow Beneficiario registration for Miembros with roles Docente or Personal_Administrativo
6. IF a Beneficiario reaches age 18 while registered as Cargo_Menor, THEN THE Sistema SHALL automatically update requirements and notify

### Requirement 7: ERE Date Consistency Enforcement

**User Story:** As a human resources manager, I want consistent date tracking for member affiliations, so that employment history is accurately maintained.

#### Acceptance Criteria

1. WHEN Miembro_Vinculacion.Fecha_Fin is not null, THE Sistema SHALL require it to equal Fecha_Inicio of the subsequent affiliation change
2. WHILE validating date consistency, THE Sistema SHALL ensure no gaps exist in affiliation timeline
3. WHERE Fecha_Fin is null, THE Sistema SHALL treat the affiliation as currently active
4. IF Fecha_Fin is earlier than Fecha_Inicio, THEN THE Sistema SHALL reject the update
5. THE Sistema SHALL always prevent overlapping affiliation periods for the same Miembro (continuous constraint)
6. WHEN a Miembro loses all active affiliations, THE Sistema SHALL automatically suspend their account access

### Requirement 8: Security and Role Implementation

**User Story:** As a security officer, I want role-based access control and security logic, so that data protection complies with institutional policies.

#### Acceptance Criteria

1. THE Sistema SHALL implement database roles (miembro_role, admin_role, auditor_role) with appropriate privileges
2. WHEN authentication attempts exceed 5 failures, THE Sistema SHALL automatically block the account
3. WHILE MFA is enabled for a Miembro, THE Sistema SHALL require second-factor verification
4. WHERE sensitive operations occur, THE Sistema SHALL log detailed audit trails
5. IF unauthorized access is attempted, THEN THE Sistema SHALL trigger security alerts
6. THE Sistema SHALL enforce password policies via PostgreSQL functions

### Requirement 9: Integration Requirements

**User Story:** As a full-stack developer, I want proper integration between database rules and application layers, so that the system functions cohesively.

#### Acceptance Criteria

1. WHEN database triggers modify data, THE Sistema SHALL provide clear error messages for Spring Boot exception handling
2. THE Sistema SHALL expose PL/pgSQL functions as callable procedures from Spring Data JPA
3. WHERE business logic executes in PostgreSQL, THE Sistema SHALL maintain transaction consistency across application layers and prevent business logic execution in PostgreSQL when consistency cannot be guaranteed
4. WHEN Angular frontend requests data, THE Sistema SHALL provide optimized views and functions for performance
5. THE Sistema SHALL implement database change notifications for real-time frontend updates
6. IF integration tests fail, THEN THE Sistema SHALL provide detailed diagnostic information

### Requirement 10: Testing and Validation

**User Story:** As a quality assurance engineer, I want comprehensive testing of business rules, so that system reliability meets production standards.

#### Acceptance Criteria

1. THE Sistema SHALL include property-based tests for all PostgreSQL functions
2. WHEN triggers execute, THE Sistema SHALL maintain data integrity invariants
3. WHILE testing, THE Sistema SHALL verify round-trip consistency for serialization operations
4. WHERE edge cases exist, THE Sistema SHALL include specific test scenarios
5. THE Sistema SHALL provide test data generation functions for all business rule scenarios
6. IF tests reveal bugs, THEN THE Sistema SHALL document corrective actions and re-test