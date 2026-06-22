-- ================================================================
-- UCAB-SERVICES — Script de limpieza (DROP)
-- Ejecutar ANTES del script principal para resetear el esquema.
-- ================================================================

DROP TABLE IF EXISTS Pago_TAI               CASCADE;
DROP TABLE IF EXISTS Tarjeta                CASCADE;
DROP TABLE IF EXISTS Efectivo               CASCADE;
DROP TABLE IF EXISTS Zelle                  CASCADE;
DROP TABLE IF EXISTS Criptomonedas          CASCADE;
DROP TABLE IF EXISTS Pago_Movil             CASCADE;
DROP TABLE IF EXISTS Pago                   CASCADE;
DROP TABLE IF EXISTS Item_consumo           CASCADE;
DROP TABLE IF EXISTS Folio_Consumo          CASCADE;
DROP TABLE IF EXISTS Factura                CASCADE;
DROP TABLE IF EXISTS Paso_Actividad         CASCADE;
DROP TABLE IF EXISTS Acompanante_Temporal   CASCADE;
DROP TABLE IF EXISTS Solicitud_Servicio     CASCADE;
DROP TABLE IF EXISTS Sugerida_A             CASCADE;
DROP TABLE IF EXISTS Oferta_Laboral         CASCADE;
DROP TABLE IF EXISTS Aplica_En              CASCADE;
DROP TABLE IF EXISTS Clasificado_En         CASCADE;
DROP TABLE IF EXISTS Asignado_En            CASCADE;
DROP TABLE IF EXISTS Tarifa_Servicio        CASCADE;
DROP TABLE IF EXISTS Billetera_TAI          CASCADE;
DROP TABLE IF EXISTS Beneficiario           CASCADE;
DROP TABLE IF EXISTS MFA_Codigo              CASCADE;
DROP TABLE IF EXISTS Auditoria_Sesion       CASCADE;
DROP TABLE IF EXISTS Periodo_Vinculacion    CASCADE;
DROP TABLE IF EXISTS Becario                CASCADE;
DROP TABLE IF EXISTS Preparador             CASCADE;
DROP TABLE IF EXISTS Estudiante             CASCADE;
DROP TABLE IF EXISTS Docente                CASCADE;
DROP TABLE IF EXISTS Personal_Administrativo CASCADE;
DROP TABLE IF EXISTS Egresado               CASCADE;
DROP TABLE IF EXISTS Miembro                CASCADE;
DROP TABLE IF EXISTS Categoria_Fidelidad    CASCADE;
DROP TABLE IF EXISTS Servicio               CASCADE;
DROP TABLE IF EXISTS Interna                CASCADE;
DROP TABLE IF EXISTS Externa                CASCADE;
DROP TABLE IF EXISTS Entidad_Prestadora     CASCADE;
DROP TABLE IF EXISTS Espacio_Fisico         CASCADE;
DROP TABLE IF EXISTS Edificacion            CASCADE;
DROP TABLE IF EXISTS Sede                   CASCADE;

DROP FUNCTION IF EXISTS fn_actualizar_saldo_factura()           CASCADE;
DROP FUNCTION IF EXISTS fn_suspender_cuenta_sin_vinculacion()   CASCADE;
DROP FUNCTION IF EXISTS fn_cerrar_paso_actividad()              CASCADE;
DROP FUNCTION IF EXISTS fn_validar_precio_tarifa()              CASCADE;
DROP FUNCTION IF EXISTS fn_archivar_acompanante()               CASCADE;
DROP FUNCTION IF EXISTS fn_upgradebeneficiario_mayoria_edad()   CASCADE;
DROP FUNCTION IF EXISTS fn_marcar_espacio_ocupado()             CASCADE;
DROP FUNCTION IF EXISTS fn_liberar_espacio()                    CASCADE;
DROP FUNCTION IF EXISTS fn_validar_solapamiento_espacio()       CASCADE;
DROP FUNCTION IF EXISTS fn_bloquear_reapertura_paso()           CASCADE;
DROP FUNCTION IF EXISTS fn_reclasificar_categoria_fidelidad()   CASCADE;
DROP FUNCTION IF EXISTS fn_bloquear_por_intentos_fallidos()     CASCADE;
DROP FUNCTION IF EXISTS fn_validar_rol_beneficiario()           CASCADE;
DROP FUNCTION IF EXISTS fn_establecer_clave(VARCHAR, TEXT)      CASCADE;
DROP FUNCTION IF EXISTS fn_verificar_clave(VARCHAR, TEXT)       CASCADE;
DROP FUNCTION IF EXISTS fn_generar_codigo_mfa(VARCHAR)          CASCADE;
DROP FUNCTION IF EXISTS fn_verificar_codigo_mfa(VARCHAR, VARCHAR) CASCADE;
DROP FUNCTION IF EXISTS fn_calcular_indice_recurrencia(VARCHAR) CASCADE;
DROP FUNCTION IF EXISTS fn_costo_final_servicio(VARCHAR,VARCHAR) CASCADE;
DROP FUNCTION IF EXISTS fn_tiempo_resolucion_solicitud(VARCHAR)  CASCADE;
DROP PROCEDURE IF EXISTS sp_cierre_masivo_folios()              CASCADE;
DROP PROCEDURE IF EXISTS sp_actualizar_tasas_conversion(NUMERIC) CASCADE;
DROP PROCEDURE IF EXISTS sp_archivar_vinculos_expirados()       CASCADE;