# Implementation Plan: PostgreSQL Business Rules Automation for UCAB-Services Segunda Entrega

## Overview

This implementation plan automates 7 key business rules, integrity constraints, and transaction logic for the UCAB-Services platform directly in PostgreSQL using triggers, stored procedures, and PL/pgSQL functions. The plan covers database implementation, Spring Boot integration, Angular frontend integration, testing, and deployment across 5 phases.

## Tasks

- [ ] 1. Database Implementation (PostgreSQL)
  - [ ] 1.1 Create Session Management Triggers and Functions
    - Create `trg_set_session_end_time` trigger for Historial_Sesiones
    - Create `fn_calculate_session_duration` function with security alert logic
    - Create `vw_active_sessions` view for real-time monitoring
    - Implement 24-hour timeout closure logic
    - Write property test: Session time invariants (PBT-1)
    - _Requirements: 1.1, 1.2, 1.3, 1.4_

  - [ ] 1.2 Implement Reservation Conflict Prevention
    - Create `trg_validate_reservation_conflicts` trigger
    - Develop `fn_find_alternative_slots` function with conflict detection
    - Add `chk_no_same_day_overlap` CHECK constraint
    - Create `vw_space_availability` materialized view
    - Write property test: Reservation non-overlap (PBT-2)
    - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5_

  - [ ] 1.3 Build Service Resolution Time Calculator
    - Create `fn_calculate_business_days` function excluding weekends
    - Implement `trg_calculate_resolution_time` trigger
    - Create `feriados` table for holiday management
    - Add holiday exclusion logic to business day calculation
    - Write property test: Business day calculation (PBT-3)
    - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5_

  - [ ] 1.4 Develop Monthly Billing Automation
    - Create `sp_monthly_billing_closure` stored procedure
    - Implement `fn_calculate_folio_total` function with validation
    - Add `trg_validate_folio_items` trigger for non-zero consumption
    - Create financial limit checking logic
    - Write property test: Folio total consistency (PBT-4)
    - _Requirements: 4.1, 4.2, 4.3, 4.4, 4.5, 4.6_

  - [ ] 1.5 Implement Dynamic Pricing Engine
    - Create `fn_get_historical_price` function with date lookup algorithm
    - Develop `vw_current_prices` view with sede and category adjustments
    - Add `chk_price_within_limits` CHECK constraint
    - Implement Categoria_Fidelidad discount application
    - Write property test: Price history lookup (PBT-5)
    - _Requirements: 5.1, 5.2, 5.3, 5.4, 5.5, 5.6_

  - [ ] 1.6 Build ERE Family Validation Module
    - Create `trg_validate_beneficiary_age` trigger with age constraints
    - Implement `fn_check_member_eligibility` function for Docente/Admin validation
    - Add `trg_upgrade_beneficiary_on_birthday` scheduled trigger
    - Create age calculation and notification logic
    - Write property test: Age relationship validation (PBT-6)
    - _Requirements: 6.1, 6.2, 6.3, 6.4, 6.5, 6.6_

  - [ ] 1.7 Implement ERE Date Consistency Module
    - Create `chk_affiliation_date_chain` CHECK constraint using window functions
    - Implement `trg_prevent_overlapping_affiliations` trigger
    - Develop `fn_check_active_affiliations` function for account suspension
    - Add automatic account suspension logic
    - Write property test: Affiliation date chain (PBT-7)
    - _Requirements: 7.1, 7.2, 7.3, 7.4, 7.5, 7.6_

  - [ ] 1.8 Create Security and Roles Infrastructure
    - Create database roles: `miembro_role`, `admin_role`, `auditor_role`
    - Implement `trg_block_after_failed_attempts` trigger (5 attempts limit)
    - Enhance `fn_verify_mfa_code` with rate limiting
    - Create `seguridad_auditoria` table and audit triggers
    - Write property test: Security invariants (PBT-8)
    - _Requirements: 8.1, 8.2, 8.3, 8.4, 8.5, 8.6_

  - [ ] 1.9 Build Integration Layer Components
    - Create `fn_get_error_message` function for Spring Boot exceptions
    - Implement `sp_notify_frontend_changes` procedure for LISTEN/NOTIFY
    - Develop `vw_api_optimized_data` materialized view
    - Create error code mapping table
    - Implement transaction consistency guarantees
    - _Requirements: 9.1, 9.2, 9.3, 9.4, 9.5, 9.6_

  - [ ] 1.10 Create Testing Infrastructure
    - Create `fn_generate_test_data` function for property-based testing
    - Implement `pbt_test_cases` table and `fn_run_property_tests` function
    - Create test data for all 7 business rule scenarios
    - Develop migration script management system
    - Create performance benchmarking queries
    - _Requirements: 10.1, 10.2, 10.3, 10.4, 10.5, 10.6_

- [ ] 2. Spring Boot Integration
  - [ ] 2.1 Update Entity Mappings
    - Update Miembro entity to support new database functions
    - Create HistorialSesiones entity with timestamp validations
    - Update SolicitudServicio entity for resolution time calculation
    - Create FolioConsumo and ItemConsumo entities with pricing logic
    - Update Beneficiario entity with age validation constraints
    - _Requirements: 9.2, 9.3, 9.4_

  - [ ] 2.2 Create Repository Extensions
    - Create SessionRepository with custom `calculateSessionDuration` method
    - Implement ReservationRepository with `findAlternativeSlots` method
    - Create BillingRepository with `executeMonthlyClosure` method
    - Implement PricingRepository with `getHistoricalPrice` method
    - Create BeneficiaryRepository with `validateAgeRelationship` method
    - _Requirements: 9.2, 9.3, 9.4_

  - [ ] 2.3 Develop Service Layer
    - Create SessionService integrating with session triggers
    - Implement ReservationService with conflict detection
    - Create ResolutionTimeService for business day calculations
    - Implement BillingService for monthly automation
    - Create PricingService for dynamic price determination
    - Implement EREValidationService for family relationships
    - Create SecurityService integrating with database roles
    - _Requirements: 9.1, 9.2, 9.3, 9.4, 9.5, 9.6_

  - [ ] 2.4 Implement Exception Handling
    - Create DatabaseBusinessRuleException hierarchy
    - Implement exception translators for PostgreSQL error codes
    - Create global exception handler with user-friendly messages
    - Implement audit logging for all business rule violations
    - Create recovery mechanisms for failed transactions
    - _Requirements: 9.1, 9.5, 9.6_

  - [ ] 2.5 Add Scheduled Tasks
    - Implement @Scheduled monthly billing job
    - Create daily beneficiary age check job
    - Implement session timeout monitoring job
    - Add materialized view refresh schedules
    - Create performance metric collection jobs
    - _Requirements: 4.1, 6.6, 8.2_

  - [ ] 2.6 Create Integration Tests
    - Write integration tests for all PostgreSQL function calls
    - Create end-to-end tests for business rule scenarios
    - Implement transaction rollback tests
    - Create concurrent access tests for reservation system
    - Write performance tests for high-volume operations
    - _Requirements: 10.1, 10.2, 10.3, 10.4, 10.5, 10.6_

- [ ] 3. Angular Frontend Integration
  - [ ] 3.1 Create WebSocket Service
    - Implement WebSocket connection to PostgreSQL LISTEN/NOTIFY
    - Create real-time session status updates
    - Implement reservation availability notifications
    - Create billing status change notifications
    - Add error notification system
    - _Requirements: 9.5, 9.6_

  - [ ] 3.2 Develop Service Integration
    - Create AuthService with enhanced error handling
    - Implement ReservationService with conflict feedback
    - Create BillingService with real-time invoice updates
    - Implement PricingService with dynamic price display
    - Create BeneficiaryService with age validation feedback
    - _Requirements: 9.5, 9.6_

  - [ ] 3.3 Build User Interface Components
    - Create session monitoring dashboard
    - Implement reservation calendar with conflict highlighting
    - Create billing status dashboard with real-time updates
    - Implement pricing calculator with historical data
    - Create beneficiary registration form with age validation
    - Build affiliation timeline visualization
    - _Requirements: 9.5_

  - [ ] 3.4 Implement Error Handling UI
    - Create user-friendly error message display
    - Implement technical details toggle for support
    - Create recovery suggestion components
    - Add audit trail reference links
    - Implement loading states with rollback capability
    - _Requirements: 9.1, 9.5_

  - [ ] 3.5 Create Monitoring Dashboards
    - Build business rule compliance dashboard
    - Create session analytics visualization
    - Implement reservation conflict heatmap
    - Create billing automation status monitor
    - Build security event dashboard
    - _Requirements: 9.5_

  - [ ] 3.6 Write Frontend Tests
    - Create unit tests for all Angular services
    - Implement component tests with mock PostgreSQL responses
    - Create integration tests with Spring Boot backend
    - Write E2E tests for critical user journeys
    - Implement performance tests for real-time updates
    - _Requirements: 10.1, 10.3, 10.4, 10.5, 10.6_

- [ ] 4. Testing and Validation
  - [ ] 4.1 Property-Based Testing Implementation
    - Write PBT-1: Session time invariants tests
    - Implement PBT-2: Reservation non-overlap tests
    - Create PBT-3: Business day calculation tests
    - Write PBT-4: Folio total consistency tests
    - Implement PBT-5: Price history lookup tests
    - Create PBT-6: Age relationship validation tests
    - Write PBT-7: Affiliation date chain tests
    - Implement PBT-8: Security invariants tests
    - _Requirements: 10.1, 10.2, 10.3_

  - [ ] 4.2 Generate Comprehensive Test Data
    - Create test data for session management scenarios
    - Generate reservation conflict test cases
    - Create billing automation test datasets
    - Generate pricing calculation edge cases
    - Create ERE family relationship test scenarios
    - Generate security violation test cases
    - _Requirements: 10.4, 10.5_

  - [ ] 4.3 Performance Testing
    - Create load tests for reservation system
    - Implement stress tests for billing automation
    - Create concurrency tests for session management
    - Implement scalability tests for pricing engine
    - Create endurance tests for audit logging
    - _Requirements: 10.5_

  - [ ] 4.4 Security Testing
    - Implement SQL injection prevention tests
    - Create privilege escalation tests
    - Implement audit trail integrity tests
    - Create data encryption validation tests
    - Implement role-based access control tests
    - _Requirements: 8.5, 10.5_

  - [ ] 4.5 Integration Testing
    - Create end-to-end tests for all business rules
    - Implement database-application consistency tests
    - Create frontend-backend synchronization tests
    - Implement error propagation tests
    - Create rollback and recovery tests
    - _Requirements: 10.5, 10.6_

- [ ] 5. Deployment and Documentation
  - [ ] 5.1 Create Migration Scripts
    - Write 001_create_business_rule_triggers.sql
    - Create 002_add_audit_tables.sql
    - Write 003_create_optimized_views.sql
    - Create 004_setup_database_roles.sql
    - Write 005_populate_test_data.sql
    - Create rollback scripts for all migrations
    - _Requirements: 9.3, 10.5_

  - [ ] 5.2 Implement Deployment Configuration
    - Create Spring Boot profiles for dev/test/prod
    - Implement database connection pooling configuration
    - Create Flyway/Liquibase migration configuration
    - Implement health check endpoints
    - Create Prometheus metrics configuration
    - _Requirements: 9.3, 9.5_

  - [ ] 5.3 Write Documentation
    - Create database function API documentation
    - Write Spring Boot service documentation
    - Create Angular component documentation
    - Implement API endpoint documentation
    - Create deployment and operations guide
    - _Requirements: 10.6_

  - [ ] 5.4 Create Monitoring Setup
    - Implement database performance monitoring
    - Create business rule compliance alerts
    - Set up audit trail reporting
    - Implement error rate monitoring
    - Create performance baseline measurements
    - _Requirements: 8.4, 9.5_

  - [ ] 5.5 Final Validation
    - Execute all property-based tests
    - Run comprehensive integration test suite
    - Perform security penetration testing
    - Execute performance benchmark tests
    - Validate all acceptance criteria
    - Create final deployment package
    - _Requirements: 10.1, 10.2, 10.3, 10.4, 10.5, 10.6_

- [ ] 6. Checkpoint - Ensure all tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [ ] 7. Checkpoint - Final validation
  - Ensure all tests pass, ask the user if questions arise.

## Notes

- This implementation plan follows a phased approach across 5 major areas: Database, Spring Boot, Angular, Testing, and Deployment
- Each task references specific requirements from the requirements document for traceability
- The plan uses PostgreSQL triggers, stored procedures, and functions to enforce business rules at the database level
- Spring Boot provides the application layer integration with PostgreSQL functions
- Angular provides the user interface with real-time updates through WebSocket connections
- Property-based tests (PBT-1 through PBT-8) validate core invariants for each business rule
- All database operations maintain transaction consistency and data integrity
- The implementation supports existing UCAB-Services database schema with minimal disruption

## Task Dependency Graph

```json
{
  "waves": [
    { "id": 0, "tasks": ["1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "1.10"] },
    { "id": 1, "tasks": ["2.1", "2.2", "2.3", "2.4", "2.5", "2.6"] },
    { "id": 2, "tasks": ["3.1", "3.2", "3.3", "3.4", "3.5", "3.6"] },
    { "id": 3, "tasks": ["4.1", "4.2", "4.3", "4.4", "4.5"] },
    { "id": 4, "tasks": ["5.1", "5.2", "5.3", "5.4", "5.5"] },
    { "id": 5, "tasks": ["6"] },
    { "id": 6, "tasks": ["7"] }
  ]
}
```