-- ================================================================
-- UCAB-SERVICES — Consultas de Verificación de Datos
-- Úsalas después de cargar los INSERT para comprobar que todo
-- quedó registrado correctamente. Organizado por entidad.
-- ================================================================


-- ════════════════════════════════════════════════════════════════
-- 1. MIEMBRO Y SUBTIPOS
-- ════════════════════════════════════════════════════════════════

-- Ver todos los miembros (tabla base, sin distinguir subtipo)
SELECT Cedula_Miembro, Nombres_Completos, Apellidos_Completos,
       Estado_cuenta, Tipo_Categoria, Intentos_fallidos, MFA_habilitado
FROM Miembro
ORDER BY Cedula_Miembro;

-- Buscar UN miembro específico por cédula (consulta más común)
SELECT * FROM Miembro WHERE Cedula_Miembro = 'V-12345678';

-- Saber a qué subtipo(s) pertenece un miembro (sin usar Periodo_Vinculacion)
SELECT 'Estudiante' AS subtipo FROM Estudiante WHERE Cedula_Miembro = 'V-12345678'
UNION ALL
SELECT 'Becario'    FROM Becario              WHERE Cedula_Miembro = 'V-12345678'
UNION ALL
SELECT 'Preparador' FROM Preparador           WHERE Cedula_Miembro = 'V-12345678'
UNION ALL
SELECT 'Egresado'   FROM Egresado             WHERE Cedula_Miembro = 'V-12345678'
UNION ALL
SELECT 'Docente'    FROM Docente              WHERE Cedula_Miembro = 'V-12345678'
UNION ALL
SELECT 'Personal_Administrativo' FROM Personal_Administrativo WHERE Cedula_Miembro = 'V-12345678';

-- Ver el detalle completo de un Estudiante específico
SELECT * FROM Estudiante WHERE Cedula_Miembro = 'V-12345678';

-- Ver todos los Becarios
SELECT Cedula_Miembro, Nombres_Completos, Tipo_Beca, Estatus_Beneficio, Indice_Mantenimiento
FROM Becario;

-- Ver todos los Preparadores
SELECT Cedula_Miembro, Nombres_Completos, Asignatura_Asignada, Horas_Ayudantia
FROM Preparador;

-- Ver todos los Egresados
SELECT Cedula_Miembro, Nombres_Completos, Titulo, Ano_graduacion, Indice_academico
FROM Egresado;

-- Ver todos los Docentes
SELECT Cedula_Miembro, Nombres_Completos, escalafon_docente, Codigo_investigador, Carga_semanal
FROM Docente;

-- Ver todo el Personal Administrativo
SELECT Cedula_Miembro, Nombres_Completos, Unidad_Adscripcion, Cargo_Administrativo
FROM Personal_Administrativo;

-- Contar cuántos miembros hay por estado de cuenta
SELECT Estado_cuenta, COUNT(*) AS total
FROM Miembro
GROUP BY Estado_cuenta;

-- Buscar miembros por nombre o apellido (LIKE / ILIKE para insensible a mayúsculas)
SELECT Cedula_Miembro, Nombres_Completos, Apellidos_Completos
FROM Miembro
WHERE Nombres_Completos ILIKE '%ana%' OR Apellidos_Completos ILIKE '%ana%';

-- Buscar por correo institucional
SELECT * FROM Miembro WHERE Correo_Institucional = 'ana.lopez@ucab.edu.ve';


-- ════════════════════════════════════════════════════════════════
-- 2. PERIODO_VINCULACION (entidad débil de Miembro)
-- ════════════════════════════════════════════════════════════════

-- Ver toda la trayectoria institucional de un miembro
SELECT Cedula_Miembro, Rol, fecha_inicio, fecha_fin
FROM Periodo_Vinculacion
WHERE Cedula_Miembro = 'V-23456789'
ORDER BY fecha_inicio;

-- Ver solo las vinculaciones activas (fecha_fin IS NULL) de todos los miembros
SELECT pv.Cedula_Miembro, m.Nombres_Completos, pv.Rol, pv.fecha_inicio
FROM Periodo_Vinculacion pv
JOIN Miembro m ON m.Cedula_Miembro = pv.Cedula_Miembro
WHERE pv.fecha_fin IS NULL;

-- Ver miembros SIN ninguna vinculación activa (candidatos a estar Suspendidos)
SELECT m.Cedula_Miembro, m.Nombres_Completos, m.Estado_cuenta
FROM Miembro m
WHERE NOT EXISTS (
    SELECT 1 FROM Periodo_Vinculacion pv
    WHERE pv.Cedula_Miembro = m.Cedula_Miembro AND pv.fecha_fin IS NULL
);


-- ════════════════════════════════════════════════════════════════
-- 3. AUDITORIA_SESION (entidad débil de Miembro)
-- ════════════════════════════════════════════════════════════════

-- Historial de accesos de un miembro
SELECT * FROM Auditoria_Sesion
WHERE Cedula_Miembro = 'V-12345678'
ORDER BY Fecha_Hora_Acceso DESC;

-- Última conexión registrada por cada miembro
SELECT Cedula_Miembro, MAX(Fecha_Hora_Acceso) AS ultima_sesion
FROM Auditoria_Sesion
GROUP BY Cedula_Miembro;


-- ════════════════════════════════════════════════════════════════
-- 4. MFA_CODIGO (entidad débil de Miembro)
-- ════════════════════════════════════════════════════════════════

-- Ver todos los códigos generados para un miembro
SELECT * FROM MFA_Codigo
WHERE Cedula_Miembro = 'V-12345678'
ORDER BY Fecha_generado DESC;

-- Ver solo el código vigente (no usado, no expirado) de un miembro
SELECT * FROM MFA_Codigo
WHERE Cedula_Miembro = 'V-12345678'
  AND Usado = FALSE
  AND Fecha_expira > now()
ORDER BY Fecha_generado DESC
LIMIT 1;


-- ════════════════════════════════════════════════════════════════
-- 5. BENEFICIARIO
-- ════════════════════════════════════════════════════════════════

-- Ver todos los beneficiarios de un miembro
SELECT * FROM Beneficiario WHERE Cedula_Miembro = 'V-45678901';

-- Buscar un beneficiario por su documento de identidad (PK)
SELECT * FROM Beneficiario WHERE Documento_identidad = 'V-87654321';

-- Ver beneficiarios activos
SELECT Documento_identidad, Nombre, Parentesco, Estatus_beneficios
FROM Beneficiario
WHERE Estatus_beneficios = 'Activo';

-- Ver beneficiarios menores de edad (con datos de carga menor)
SELECT Documento_identidad, Nombre, fecha_nacimiento_beneficiario
FROM Beneficiario
WHERE Esquema_vacunacion IS NOT NULL;


-- ════════════════════════════════════════════════════════════════
-- 6. BILLETERA_TAI
-- ════════════════════════════════════════════════════════════════

-- Ver la billetera de un miembro específico
SELECT * FROM Billetera_TAI WHERE Cedula_Miembro = 'V-12345678';

-- Ver todas las billeteras ordenadas por saldo
SELECT b.UID, b.Cedula_Miembro, m.Nombres_Completos, b.Saldo_Virtual, b.Saldo_Restante
FROM Billetera_TAI b
JOIN Miembro m ON m.Cedula_Miembro = b.Cedula_Miembro
ORDER BY b.Saldo_Restante DESC;


-- ════════════════════════════════════════════════════════════════
-- 7. SEDE / EDIFICACION / ESPACIO_FISICO
-- ════════════════════════════════════════════════════════════════

-- Ver todas las sedes
SELECT * FROM Sede;

-- Ver edificaciones de una sede
SELECT * FROM Edificacion WHERE nombre_sede = 'Montalban';

-- Buscar un espacio físico por edificio + número identificador (PK compuesta)
SELECT * FROM Espacio_Fisico
WHERE Nombre_Edif = 'Edificio Cincuentenario'
  AND Direccion_Interna = 'Av. Teheran, Urb. Montalbán'
  AND Num_identificador = 1;

-- Ver disponibilidad de espacios por sede
SELECT s.nombre_sede, e.Nombre_Edif, ef.Num_identificador,
       ef.Tipo_inmobiliario, ef.Capacidad_aforo, ef.Estatus
FROM Espacio_Fisico ef
JOIN Edificacion e ON e.Nombre_Edif = ef.Nombre_Edif AND e.Direccion_Interna = ef.Direccion_Interna
JOIN Sede s ON s.nombre_sede = e.nombre_sede
ORDER BY s.nombre_sede, e.Nombre_Edif;

-- Contar espacios por estatus
SELECT Estatus, COUNT(*) FROM Espacio_Fisico GROUP BY Estatus;


-- ════════════════════════════════════════════════════════════════
-- 8. ENTIDAD_PRESTADORA / INTERNA / EXTERNA
-- ════════════════════════════════════════════════════════════════

-- Ver todas las entidades con su tipo
SELECT * FROM Entidad_Prestadora;

-- Ver detalle de una entidad interna por id
SELECT * FROM Interna WHERE id_entidad = 1;

-- Ver detalle de una entidad externa por RIF
SELECT * FROM Externa WHERE rif = 'J-12345678-9';

-- Ver todas las entidades externas con contrato próximo a vencer
SELECT id_entidad, razon_social, fecha_vencimiento_contrato
FROM Externa
WHERE fecha_vencimiento_contrato < CURRENT_DATE + INTERVAL '1 year';


-- ════════════════════════════════════════════════════════════════
-- 9. CATEGORIA_FIDELIDAD
-- ════════════════════════════════════════════════════════════════

SELECT * FROM Categoria_Fidelidad;

-- Miembros agrupados por categoría de fidelidad
SELECT Tipo_Categoria, COUNT(*) AS total_miembros
FROM Miembro
GROUP BY Tipo_Categoria;


-- ════════════════════════════════════════════════════════════════
-- 10. SERVICIO / TARIFA_SERVICIO / APLICA_EN / CLASIFICADO_EN / ASIGNADO_EN
-- ════════════════════════════════════════════════════════════════

-- Buscar un servicio por código (PK)
SELECT * FROM Servicio WHERE Codigo_Servicio = 'SRV-001';

-- Ver servicios activos
SELECT Codigo_Servicio, Descripcion_detallada, Estado_Servicio
FROM Servicio
WHERE Estado_Servicio = 'Activo';

-- Ver tarifas de un servicio específico
SELECT * FROM Tarifa_Servicio WHERE Codigo_Servicio = 'SRV-001';

-- Ver precios de un servicio por sede
SELECT * FROM Aplica_En WHERE codigo_servicio = 'SRV-001';

-- Ver categorías de fidelidad que aplican a una tarifa
SELECT * FROM Clasificado_En WHERE Codigo_Servicio = 'SRV-001';

-- Ver en qué espacios físicos está asignado un servicio
SELECT * FROM Asignado_En WHERE Codigo_Servicio = 'SRV-001';

-- Ver servicios junto con la entidad que los presta
SELECT s.Codigo_Servicio, s.Descripcion_detallada, ep.id_entidad, ep.tipo_entidad
FROM Servicio s
JOIN Entidad_Prestadora ep ON ep.id_entidad = s.id_entidad;


-- ════════════════════════════════════════════════════════════════
-- 11. OFERTA_LABORAL / SUGERIDA_A
-- ════════════════════════════════════════════════════════════════

-- Buscar ofertas por entidad externa + cargo (PK compuesta)
SELECT * FROM Oferta_Laboral
WHERE id_entidad_externa = 3 AND cargo_laboral = 'Desarrollador Backend';

-- Ver ofertas disponibles
SELECT * FROM Oferta_Laboral WHERE estatus_vacante = 'Disponible';

-- Ver a qué egresados se les sugirió una oferta específica
SELECT sa.Cedula_Miembro, m.Nombres_Completos
FROM Sugerida_A sa
JOIN Miembro m ON m.Cedula_Miembro = sa.Cedula_Miembro
WHERE sa.id_entidad_externa = 3 AND sa.cargo_laboral = 'Desarrollador Backend';

-- Ver todas las ofertas sugeridas a un egresado en particular
SELECT ol.cargo_laboral, ol.estatus_vacante
FROM Sugerida_A sa
JOIN Oferta_Laboral ol ON ol.id_entidad_externa = sa.id_entidad_externa
                       AND ol.cargo_laboral = sa.cargo_laboral
WHERE sa.Cedula_Miembro = 'V-23456789';


-- ════════════════════════════════════════════════════════════════
-- 12. SOLICITUD_SERVICIO / PASO_ACTIVIDAD / ACOMPANANTE_TEMPORAL
-- ════════════════════════════════════════════════════════════════

-- Buscar una solicitud por identificador (PK)
SELECT * FROM Solicitud_Servicio WHERE Identificador = 'SOL-2025-001';

-- Ver todas las solicitudes de un miembro
SELECT * FROM Solicitud_Servicio WHERE Cedula_Miembro = 'V-12345678';

-- Ver solicitudes por estado
SELECT Identificador, Cedula_Miembro, Codigo_Servicio, Estado_actual
FROM Solicitud_Servicio
WHERE Estado_actual = 'En_Proceso';

-- Ver los pasos de actividad de una solicitud, en orden
SELECT * FROM Paso_Actividad
WHERE Identificador = 'SOL-2025-001'
ORDER BY Orden_paso;

-- Ver acompañantes de una solicitud
SELECT * FROM Acompanante_Temporal WHERE Identificador = 'SOL-2025-002';

-- Ver acompañantes activos (solicitudes aún no cerradas)
SELECT * FROM Acompanante_Temporal WHERE Estado_activo = TRUE;


-- ════════════════════════════════════════════════════════════════
-- 13. FOLIO_CONSUMO / ITEM_CONSUMO / FACTURA
-- ════════════════════════════════════════════════════════════════

-- Buscar un folio por solicitud + fecha de apertura (PK compuesta)
SELECT * FROM Folio_Consumo
WHERE Identificador = 'SOL-2025-001' AND Fecha_apertura = '2025-05-01';

-- Ver folios abiertos (pendientes de facturar)
SELECT * FROM Folio_Consumo WHERE Estado_cierre = 'Abierto';

-- Ver todos los ítems de consumo de un folio
SELECT * FROM Item_consumo
WHERE Identificador = 'SOL-2025-001' AND Fecha_apertura = '2025-05-01';

-- Buscar una factura por número de control (PK)
SELECT * FROM Factura WHERE Numero_control = 'FCT-2025-001';

-- Ver todas las facturas de un miembro
SELECT * FROM Factura WHERE Cedula_Miembro = 'V-12345678';

-- Ver facturas por estatus
SELECT Numero_control, Cedula_Miembro, Monto_total, Saldo_restante_pagar
FROM Factura
WHERE Estatus_factura = 'Pendiente';

-- Ver el total facturado vs pagado por miembro
SELECT Cedula_Miembro,
       SUM(Monto_total) AS total_facturado,
       SUM(Monto_total - Saldo_restante_pagar) AS total_pagado,
       SUM(Saldo_restante_pagar) AS total_pendiente
FROM Factura
GROUP BY Cedula_Miembro;


-- ════════════════════════════════════════════════════════════════
-- 14. PAGO Y SUBTIPOS (Pago_Movil, Criptomonedas, Zelle, Efectivo, Tarjeta, Pago_TAI)
-- ════════════════════════════════════════════════════════════════

-- Ver todos los pagos de una factura
SELECT * FROM Pago WHERE Numero_control_Factura = 'FCT-2025-001';

-- Ver pagos por tipo
SELECT * FROM Pago WHERE tipo_pago = 'Zelle';

-- Ver el detalle completo de un pago en Zelle (join con el subtipo)
SELECT p.*, z.nombre_titular, z.correo_origen, z.codigo_confirmacion
FROM Pago p
JOIN Zelle z ON z.Numero_control_Factura = p.Numero_control_Factura
            AND z.fecha_operacion = p.fecha_operacion
WHERE p.Numero_control_Factura = 'FCT-2025-002';

-- Ver el detalle completo de un pago en Efectivo
SELECT p.*, e.divisa, e.tasa_bcv, e.desglose_denominacion
FROM Pago p
JOIN Efectivo e ON e.Numero_control_Factura = p.Numero_control_Factura
                AND e.fecha_operacion = p.fecha_operacion;

-- Ver el detalle completo de un pago con Tarjeta
SELECT p.*, t.tipo_red, t.compania, t.num_tarjeta
FROM Pago p
JOIN Tarjeta t ON t.Numero_control_Factura = p.Numero_control_Factura
              AND t.fecha_operacion = p.fecha_operacion;

-- Ver el detalle completo de un pago Móvil
SELECT p.*, pm.banco, pm.referencia, pm.telefono_emisor
FROM Pago p
JOIN Pago_Movil pm ON pm.Numero_control_Factura = p.Numero_control_Factura
                   AND pm.fecha_operacion = p.fecha_operacion;

-- Ver el detalle completo de un pago con Criptomonedas
SELECT p.*, c.dxid, c.red_blockchain, c.billetera, c.tasa_conversion
FROM Pago p
JOIN Criptomonedas c ON c.Numero_control_Factura = p.Numero_control_Factura
                     AND c.fecha_operacion = p.fecha_operacion;

-- Ver el detalle completo de un pago con TAI (incluye la billetera)
SELECT p.*, pt.pos_terminal, pt.recibo_digital, bt.Cedula_Miembro AS titular_billetera
FROM Pago p
JOIN Pago_TAI pt ON pt.Numero_control_Factura = p.Numero_control_Factura
                 AND pt.fecha_operacion = p.fecha_operacion
JOIN Billetera_TAI bt ON bt.UID = pt.uid_billetera;

-- Total pagado por método, en todo el sistema
SELECT tipo_pago, COUNT(*) AS cantidad, SUM(monto_liquidacion) AS total
FROM Pago
GROUP BY tipo_pago
ORDER BY total DESC;


-- ════════════════════════════════════════════════════════════════
-- 15. FUNCIONES DE SEGURIDAD (clave y MFA)
-- ════════════════════════════════════════════════════════════════

-- Verificar login con contraseña (debe devolver TRUE con la clave de prueba)
SELECT fn_verificar_clave('V-12345678', 'Ucab2026*');

-- Generar un código MFA de prueba para un miembro
SELECT fn_generar_codigo_mfa('V-12345678');

-- Verificar un código MFA (reemplaza '000000' por el código real generado arriba)
SELECT fn_verificar_codigo_mfa('V-12345678', '000000');


-- ════════════════════════════════════════════════════════════════
-- 16. FUNCIONES DE NEGOCIO (recurrencia, tiempo de resolución, costo final)
-- ════════════════════════════════════════════════════════════════

-- Calcular y actualizar el índice de recurrencia de un miembro
SELECT fn_calcular_indice_recurrencia('V-12345678');

-- Calcular el tiempo de resolución de una solicitud ya cerrada
SELECT fn_tiempo_resolucion_solicitud('SOL-2025-002');

-- Calcular el costo final de un servicio para un miembro (con su descuento de fidelidad)
SELECT fn_costo_final_servicio('SRV-001', 'V-23456789');