# Design Document

## Overview

This design implements the automation of 7 core business rules from the "Segunda Entrega" project directly in PostgreSQL, with proper integration into the existing Spring Boot backend and Angular frontend. The architecture leverages PostgreSQL triggers, stored procedures, and PL/pgSQL functions to enforce business logic at the database level, ensuring data integrity, transactional consistency, and security.

## Architecture

### System Architecture

#### Database Layer (PostgreSQL)
- **Triggers**: 14 new triggers for real-time business rule enforcement
- **Functions**: 12 PL/pgSQL functions for complex calculations and validations
- **Stored Procedures**: 8 procedures for batch operations and integration
- **Roles**: 3 database roles with granular privilege separation
- **Views**: 5 optimized views for frontend data access

#### Application Layer (Spring Boot)
- **Repository Extensions**: Custom repository methods calling PostgreSQL functions
- **Entity Updates**: JPA entity mappings for new database functions
- **Service Layer**: Business service integration with database triggers
- **Exception Handling**: Custom exceptions for PostgreSQL constraint violations
- **Scheduled Tasks**: Quartz/Cron jobs for monthly batch operations

#### Frontend Layer (Angular)
- **Service Integration**: Angular services calling Spring Boot endpoints
- **Real-time Updates**: WebSocket connections for database change notifications
- **Validation Feedback**: User-friendly error messages from PostgreSQL constraints
- **Dashboard Views**: Monitoring interfaces for business rule compliance

## Components and Interfaces

### 1. Session Management Module

#### Database Components
```sql
-- Trigger: trg_set_session_end_time
-- Fires: AFTER UPDATE ON Historial_Sesiones
-- Purpose: Sets Hora_Fin when session closes, logs security alerts
-- Invariant: Closed sessions cannot have null Hora_Fin

-- Function: fn_calculate_session_duration
-- Returns: INTERVAL
-- Purpose: Calculates duration between Hora_Inicio and Hora_Fin
-- Business Rule: Excludes sessions closed by timeout vs manual closure

-- View: vw_active_sessions
-- Columns: Cedula_Miembro, Hora_Inicio, Duration, Status
-- Purpose: Real-time monitoring of active sessions
```

#### Spring Boot Integration
- **SessionService**: Manages session lifecycle, calls `fn_calculate_session_duration`
- **SecurityAuditService**: Logs security alerts from timeout closures
- **SessionRepository**: Custom queries using `vw_active_sessions`

### 2. Reservation Conflict Prevention Module

#### Database Components
```sql
-- Trigger: trg_validate_reservation_conflicts  
-- Fires: BEFORE INSERT OR UPDATE ON Solicitud_Servicio
-- Purpose: Validates no time overlaps for same Espacio_Físico
-- Invariant: ∀r1,r2 ∈ Reservations: r1.space = r2.space ∧ r1.day = r2.day ⇒ ¬overlap(r1.interval, r2.interval)

-- Function: fn_find_alternative_slots
-- Parameters: desired_date DATE, space_id INT, duration INTERVAL
-- Returns: TABLE of alternative time slots
-- Purpose: Suggests available slots when conflict detected

-- Constraint: chk_no_same_day_overlap
-- CHECK constraint on Solicitud_Servicio
-- Purpose: Ensures basic same-day validation
```

#### Spring Boot Integration
- **ReservationService**: Business logic for reservation creation
- **ConflictDetectionService**: Calls `fn_find_alternative_slots`
- **RealTimeAvailabilityService**: Polls `vw_space_availability` view

### 3. Service Resolution Time Calculator

#### Database Components
```sql
-- Function: fn_calculate_business_days
-- Parameters: start_date DATE, end_date DATE
-- Returns: INTEGER (business days count)
-- Business Rule: Excludes weekends (Saturday, Sunday)
-- Extension: Excludes defined holidays from feriados table

-- Trigger: trg_calculate_resolution_time
-- Fires: BEFORE UPDATE ON Solicitud_Servicio WHEN Estado_actual changes to 'Completada' or 'Cancelada'
-- Purpose: Calculates and stores Tiempo_resolucion
-- Invariant: Resolution time ≥ 0 business days

-- Table: feriados
-- Columns: fecha DATE, descripcion VARCHAR, recurrente BOOLEAN
-- Purpose: Holiday calendar for accurate business day calculation
```

#### Spring Boot Integration
- **ResolutionTimeService**: Orchestrates resolution time calculation
- **HolidayService**: Manages feriados table for institutional holidays
- **PerformanceMetricsService**: Aggregates resolution time statistics

### 4. Monthly Billing Automation Module

#### Database Components
```sql
-- Procedure: sp_monthly_billing_closure
-- Parameters: cutoff_date DATE DEFAULT LAST_DAY(CURRENT_DATE)
-- Purpose: Identifies open Folio_Consumo, creates Factura, updates status
-- Transaction: All-or-nothing batch operation

-- Function: fn_calculate_folio_total
-- Parameters: folio_id VARCHAR, fecha_apertura DATE
-- Returns: NUMERIC(18,4)
-- Business Rule: SUM(Precio_unitario × Cantidad + Impuesto)
-- Validation: Returns NULL if no items or all zero consumption

-- Trigger: trg_validate_folio_items
-- Fires: BEFORE INSERT ON Folio_Consumo
-- Purpose: Ensures Folio has at least one non-zero Item_consumo
-- Invariant: Folio_Consumo must have ∃item ∈ Item_consumo: cantidad > 0 ∧ precio_unitario > 0
```

#### Spring Boot Integration
- **BillingScheduler**: @Scheduled cron job calling `sp_monthly_billing_closure`
- **InvoiceService**: Manages Factura creation and validation
- **FinancialLimitService**: Checks pre-defined limits for manual review flags

### 5. Dynamic Pricing Engine

#### Database Components
```sql
-- Function: fn_get_historical_price
-- Parameters: servicio_id VARCHAR, perfil VARCHAR, fecha_cargo DATE
-- Returns: NUMERIC(18,4)
-- Algorithm: 
--   1. Find exact date match in Tarifa_Servicio
--   2. If none, find most recent applicable tarifa before fecha_cargo
--   3. Apply sede adjustment from Aplica_En
--   4. Apply Categoria_Fidelidad discount

-- View: vw_current_prices
-- Columns: Codigo_Servicio, Perfil_solicitante, Precio_base, Sede, Categoria, Precio_Final
-- Purpose: Real-time price reference for all services

-- Constraint: chk_price_within_limits
-- CHECK on Item_consumo
-- Purpose: Ensures Precio_unitario ≤ Tarifa_Servicio.limite_costo
```

#### Spring Boot Integration
- **PricingService**: Main service for price determination
- **TariffHistoryService**: Manages Tarifa_Servicio historical data
- **DiscountApplicationService**: Applies Categoria_Fidelidad discounts

### 6. ERE Family Validation Module

#### Database Components
```sql
-- Trigger: trg_validate_beneficiary_age
-- Fires: BEFORE INSERT OR UPDATE ON Beneficiario
-- Purpose: Enforces age-based relationship constraints
-- Business Rules:
--   Cargo_Menor: age < 18 years
--   Cargo_Mayor: age ≥ 18 years
--   Validation: EXTRACT(YEAR FROM AGE(fecha_nacimiento_beneficiario))

-- Function: fn_check_member_eligibility
-- Parameters: cedula_miembro VARCHAR
-- Returns: BOOLEAN
-- Purpose: Validates Miembro is Docente or Personal_Administrativo
-- Query: EXISTS in subtipo tables

-- Trigger: trg_upgrade_beneficiary_on_birthday
-- Fires: Scheduled daily via pg_cron
-- Purpose: Automatically updates Beneficiario status when age 18 reached
```

#### Spring Boot Integration
- **BeneficiaryService**: Manages ERE family registrations
- **AgeValidationService**: Calculates and validates ages
- **NotificationService**: Sends notifications for status changes

### 7. ERE Date Consistency Module

#### Database Components
```sql
-- Constraint: chk_affiliation_date_chain
-- CHECK on Miembro_Vinculacion
-- Purpose: Ensures Fecha_Fin = next Fecha_Inicio (no gaps)
-- Implementation: Uses LEAD() window function in constraint

-- Trigger: trg_prevent_overlapping_affiliations
-- Fires: BEFORE INSERT OR UPDATE ON Miembro_Vinculacion  
-- Purpose: Prevents overlapping date ranges for same Miembro
-- Invariant: ∀a1,a2 ∈ Affiliations: a1.miembro = a2.miembro ⇒ ¬overlap(a1.interval, a2.interval)

-- Function: fn_check_active_affiliations
-- Parameters: cedula_miembro VARCHAR
-- Returns: INTEGER (count of active affiliations)
-- Purpose: Determines if account should be suspended
```

#### Spring Boot Integration
- **AffiliationService**: Manages Miembro_Vinculacion lifecycle
- **AccountSuspensionService**: Automatically suspends accounts without active affiliations
- **DateConsistencyService**: Validates affiliation date chains

### 8. Security and Roles Module

#### Database Components
```sql
-- Roles Creation:
--   miembro_role: SELECT on member views, EXECUTE on member functions
--   admin_role: ALL privileges on operational tables, EXECUTE on admin functions  
--   auditor_role: SELECT on audit tables, EXECUTE on reporting functions

-- Trigger: trg_block_after_failed_attempts
-- Fires: BEFORE UPDATE ON Miembro WHEN Intentos_fallidos changes
-- Business Rule: IF Intentos_fallidos >= 5 THEN Estado_cuenta = 'Bloqueada'

-- Function: fn_verify_mfa_code  
-- Existing function enhanced with rate limiting and audit logging

-- Table: seguridad_auditoria
-- Columns: timestamp, usuario, operacion, tabla, datos_anteriores, datos_nuevos
-- Purpose: Comprehensive audit trail for sensitive operations
```

#### Spring Boot Integration
- **RoleBasedSecurityService**: Manages database role assignments
- **AuthenticationService**: Integrates with `fn_verificar_clave` and MFA
- **AuditService**: Logs to seguridad_auditoria table

### 9. Integration Layer

#### Database Components
```sql
-- Function: fn_get_error_message
-- Parameters: error_code VARCHAR, language VARCHAR DEFAULT 'es'
-- Returns: TABLE(error_message TEXT, user_message TEXT, technical_details TEXT)
-- Purpose: Provides clear error messages for Spring Boot exception handling

-- Procedure: sp_notify_frontend_changes
-- Parameters: table_name VARCHAR, operation VARCHAR, record_id VARCHAR
-- Purpose: Sends change notifications via LISTEN/NOTIFY for real-time updates

-- View: vw_api_optimized_data
-- Materialized view refreshed on schedule
-- Purpose: Pre-joined, filtered data for Angular frontend performance
```

#### Spring Boot Integration
- **PostgreSQLFunctionTemplate**: Generic template for calling PostgreSQL functions
- **DatabaseChangeListener**: Listens to PostgreSQL NOTIFY events
- **OptimizedQueryService**: Uses materialized views for performance

### 10. Testing Infrastructure

#### Database Components
```sql
-- Function: fn_generate_test_data
-- Parameters: scenario VARCHAR, count INTEGER DEFAULT 100
-- Returns: VOID (populates test tables)
-- Purpose: Generates property-based test data for all business rules

-- Table: pbt_test_cases
-- Columns: test_id UUID, scenario VARCHAR, input_data JSONB, expected_output JSONB
-- Purpose: Stores property-based test cases

-- Function: fn_run_property_tests
-- Returns: TABLE(test_name VARCHAR, passed BOOLEAN, counterexample JSONB)
-- Purpose: Executes property tests and returns results
```

#### Spring Boot Integration
- **PropertyBasedTestService**: Orchestrates database-level property testing
- **TestDataGeneratorService**: Calls `fn_generate_test_data`
- **TestResultRepository**: Stores and analyzes test results

## Data Models

### Enhanced Entity Relationships
```
Miembro (1) -- (0..*) Historial_Sesiones
Miembro (1) -- (0..*) Solicitud_Servicio
Solicitud_Servicio (1) -- (1) Folio_Consumo
Folio_Consumo (1) -- (1..*) Item_consumo
Item_consumo (1) -- (1) Tarifa_Servicio (historical)
Miembro (1) -- (0..*) Beneficiario (ERE)
Miembro (1) -- (0..*) Miembro_Vinculacion
```

### New Audit Tables
```
seguridad_auditoria
  - id_auditoria SERIAL PK
  - timestamp TIMESTAMP
  - usuario VARCHAR
  - operacion VARCHAR
  - tabla VARCHAR
  - datos_anteriores JSONB
  - datos_nuevos JSONB
  - ip_origen INET
  - user_agent TEXT

pbt_test_results
  - test_id UUID PK
  - execution_time TIMESTAMP
  - scenario VARCHAR
  - passed BOOLEAN
  - counterexample JSONB
  - duration INTERVAL
```

## Transaction Design

### ACID Compliance Patterns
1. **Session Closure**: Single-statement UPDATE with RETURNING clause
2. **Reservation Creation**: BEGIN → Validation → Insert → COMMIT (or ROLLBACK)
3. **Monthly Billing**: Stored procedure with SAVEPOINT for partial recovery
4. **Price Determination**: READ COMMITTED isolation for historical price lookup
5. **ERE Validation**: SERIALIZABLE isolation for family relationship integrity

### Concurrency Control
- **Optimistic Locking**: Version columns on frequently updated tables
- **Pessimistic Locking**: SELECT FOR UPDATE on financial transactions
- **Row-Level Security**: Policies based on database roles
- **Application-Level**: Spring @Transactional with propagation settings

## Performance Considerations

### Indexing Strategy
```sql
-- Composite indexes for common query patterns
CREATE INDEX idx_reservation_space_date ON Solicitud_Servicio(Espacio_Fisico_Id, Fecha_Inicio, Fecha_Fin);
CREATE INDEX idx_tarifa_service_date ON Tarifa_Servicio(Codigo_Servicio, Fecha_Inicio, Fecha_Fin);
CREATE INDEX idx_beneficiary_age ON Beneficiario(fecha_nacimiento_beneficiario, Parentesco);

-- Partial indexes for active records
CREATE INDEX idx_active_sessions ON Historial_Sesiones(Hora_Fin) WHERE Hora_Fin IS NULL;
CREATE INDEX idx_open_folios ON Folio_Consumo(Estado_cierre) WHERE Estado_cierre = 'Abierto';
```

### Query Optimization
- Materialized views for dashboard data
- Function volatility markers (IMMUTABLE, STABLE, VOLATILE)
- Prepared statement usage from Spring Boot
- Connection pooling configuration

## Deployment Strategy

### Database Migration
```sql
-- Versioned migration scripts
-- 001_create_business_rule_triggers.sql
-- 002_add_audit_tables.sql  
-- 003_create_optimized_views.sql
-- 004_setup_database_roles.sql
-- 005_populate_test_data.sql
```

### Spring Boot Integration
- **Flyway/Liquibase**: Database migration management
- **Spring Profiles**: dev/test/prod configuration
- **Health Checks**: Database connection and function validation
- **Metrics**: Prometheus metrics for trigger execution counts

### Angular Integration
- **WebSocket Service**: Real-time database change notifications
- **Error Interceptor**: User-friendly error messages from PostgreSQL
- **Loading States**: Optimistic UI updates with rollback on failure
- **Dashboard Components**: Business rule compliance monitoring

## Correctness Properties

*A property is a characteristic or behavior that should hold true across all valid executions of a system—essentially, a formal statement about what the system should do. Properties serve as the bridge between human-readable specifications and machine-verifiable correctness guarantees.*

### Property 1: Session Time Invariants

*For any* session in Historial_Sesiones where Hora_Fin is not null, the session end time must be greater than or equal to the start time, the duration must equal the difference between end and start times, and the duration must not exceed 24 hours.

**Validates:** Requirements 1.1, 1.2

### Property 2: Reservation Non-Overlap

*For any* two reservations r1 and r2 in Solicitud_Servicio for the same physical space on the same date, their time intervals must not overlap.

**Validates:** Requirements 2.1, 2.2

### Property 3: Business Day Calculation

*For any* start and end dates, the business days calculation must exclude weekends, return a non-negative value, and be less than or equal to the total calendar days between dates.

**Validates:** Requirements 3.1, 3.2

### Property 4: Folio Total Consistency

*For any* folio in Folio_Consumo, the calculated total using fn_calculate_folio_total must equal the stored Monto_total, or both must be zero/null when appropriate.

**Validates:** Requirements 4.1, 4.2

### Property 5: Price History Lookup

*For any* item in Item_consumo, the historical price lookup must return a non-negative value that does not exceed the service cost limit and properly considers sede adjustments.

**Validates:** Requirements 5.1, 5.2

### Property 6: Age Relationship Validation

*For any* beneficiary in Beneficiario, if the relationship is 'Cargo_Menor' the age must be under 18, and if 'Cargo_Mayor' the age must be 18 or older.

**Validates:** Requirements 6.1, 6.2

### Property 7: Affiliation Date Chain

*For any* member, their sorted affiliation records must form a continuous chain with no gaps or overlaps between successive records.

**Validates:** Requirements 7.1, 7.2

### Property 8: Security Invariants

*For any* member, if failed login attempts reach 5 or more, the account must be blocked, and successful login must reset the failed attempt counter to zero.

**Validates:** Requirements 8.1, 8.2

## Error Handling Design

### PostgreSQL Error Codes Mapping
```
23514 - Check constraint violation → BusinessRuleViolationException
23505 - Unique constraint violation → DuplicateResourceException
23503 - Foreign key violation → ReferentialIntegrityException
P0001 - Custom RAISE EXCEPTION → BusinessLogicException
```

### Spring Boot Exception Hierarchy
```
DatabaseBusinessRuleException
├── SessionValidationException
├── ReservationConflictException  
├── BillingAutomationException
├── PricingCalculationException
├── EREValidationException
├── DateConsistencyException
└── SecurityViolationException
```

### Angular Error Display
- **User-friendly messages**: Translated from PostgreSQL error details
- **Technical details toggle**: For support troubleshooting
- **Recovery suggestions**: Alternative actions when available
- **Audit trail reference**: Link to security audit entry

## Monitoring and Observability

### Database Metrics
- Trigger execution counts and durations
- Function performance statistics
- Lock contention monitoring
- Query plan analysis for slow operations

### Application Metrics
- Spring Boot actuator endpoints for database health
- Custom metrics for business rule compliance rates
- Alert thresholds for abnormal patterns
- Dashboard for real-time system status

### Audit Trail
- Comprehensive security event logging
- Business rule violation tracking
- Change history for critical data
- Compliance reporting exports

## Success Criteria

### Functional Requirements Met
1. All 7 business rules implemented in PostgreSQL with triggers/functions
2. Proper integration with existing Spring Boot entities and services
3. Angular frontend receives real-time updates and validation feedback
4. Comprehensive property-based testing coverage
5. Role-based security with audit trails

### Non-Functional Requirements
1. **Performance**: < 100ms for 95% of business rule validations
2. **Reliability**: 99.9% uptime for critical billing operations
3. **Security**: Zero SQL injection vulnerabilities, encrypted sensitive data
4. **Maintainability**: Documented migration path for future changes
5. **Testability**: 90%+ code coverage for database functions

This design provides a complete blueprint for implementing the "Segunda Entrega" business rules automation while maintaining the existing UCAB-Services architecture and ensuring seamless integration across all layers.