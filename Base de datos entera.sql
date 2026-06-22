-- ================================================================
-- UCAB-SERVICES — Script Completo de Base de Datos
-- PostgreSQL | Segunda Entrega
-- Incluye: DROP, CREATE, ALTER, CONSTRAINTS, TRIGGERS, FUNCIONES,
--          PROCEDIMIENTOS, DATOS DE PRUEBA (INSERT)
-- ================================================================

-- ================================================================
-- SECCIÓN 1: EXTENSIONES
-- ================================================================
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ================================================================
-- SECCIÓN 2: CREATE TABLES
-- (mismo orden que Tablas_completas.sql)
-- ================================================================

-- ── Sede ─────────────────────────────────────────────────────────
CREATE TABLE Sede (
    nombre_sede VARCHAR(100) PRIMARY KEY
);

-- ── Edificacion ──────────────────────────────────────────────────
CREATE TABLE Edificacion (
    Nombre_Edif       VARCHAR(100) NOT NULL,
    Direccion_Interna TEXT         NOT NULL,
    nombre_sede       VARCHAR(100) NOT NULL REFERENCES Sede(nombre_sede),
    PRIMARY KEY (Nombre_Edif, Direccion_Interna)
);

-- ── Espacio_Fisico ───────────────────────────────────────────────
CREATE TABLE Espacio_Fisico (
    Num_identificador SERIAL       NOT NULL,
    Nombre_Edif       VARCHAR(100) NOT NULL,
    Direccion_Interna TEXT         NOT NULL,
    Capacidad_aforo   INTEGER      NOT NULL CHECK (Capacidad_aforo > 0),
    Tipo_inmobiliario VARCHAR(50)  NOT NULL,
    Estatus           VARCHAR(30)  NOT NULL
        CHECK (Estatus IN ('Disponible','Ocupado','Mantenimiento')),
    PRIMARY KEY (Nombre_Edif, Direccion_Interna, Num_identificador),
    FOREIGN KEY (Nombre_Edif, Direccion_Interna)
        REFERENCES Edificacion(Nombre_Edif, Direccion_Interna)
);

-- ── Entidad_Prestadora + subtipos ────────────────────────────────
CREATE TABLE Entidad_Prestadora (
    id_entidad   INT         PRIMARY KEY,
    tipo_entidad VARCHAR(10) NOT NULL CHECK (tipo_entidad IN ('Interna','Externa')),
    CONSTRAINT uq_entidad_y_tipo UNIQUE (id_entidad, tipo_entidad)
);

CREATE TABLE Interna (
    id_entidad       INT         PRIMARY KEY,
    tipo_entidad     VARCHAR(10) NOT NULL CHECK (tipo_entidad = 'Interna'),
    codigo_presu     VARCHAR(50) UNIQUE NOT NULL,
    director_oficina VARCHAR(100),
    CONSTRAINT fk_interna_padre FOREIGN KEY (id_entidad, tipo_entidad)
        REFERENCES Entidad_Prestadora(id_entidad, tipo_entidad)
);

CREATE TABLE Externa (
    id_entidad   INT         PRIMARY KEY,
    tipo_entidad VARCHAR(10) NOT NULL CHECK (tipo_entidad = 'Externa'),
    rif          VARCHAR(50) UNIQUE NOT NULL,
    razon_social VARCHAR(150),
    contactos_legales            TEXT,
    fecha_vencimiento_contrato   DATE,
    CONSTRAINT fk_externa_padre FOREIGN KEY (id_entidad, tipo_entidad)
        REFERENCES Entidad_Prestadora(id_entidad, tipo_entidad)
);

-- ── Categoria_Fidelidad ──────────────────────────────────────────
CREATE TABLE Categoria_Fidelidad (
    Tipo_Categoria        VARCHAR(50)  PRIMARY KEY,
    Rango_indice_min      NUMERIC(4,2) NOT NULL,
    Rango_indice_max      NUMERIC(4,2) NOT NULL,
    Descuento_global      NUMERIC(5,2) DEFAULT 0,
    Prioridad_reservacion INTEGER      DEFAULT 0,
    CONSTRAINT chk_rango_cat CHECK (Rango_indice_min <= Rango_indice_max)
);

-- ── Miembro (tabla base de la jerarquía de subtipos) ─────────────
CREATE TABLE Miembro (
    Cedula_Miembro       VARCHAR(20)  PRIMARY KEY,
    Nombres_Completos    VARCHAR(200) NOT NULL,
    Apellidos_Completos  VARCHAR(200) NOT NULL,
    Sexo                 VARCHAR(1)   NOT NULL CHECK (Sexo IN ('M','F','O')),
    fecha_nacimiento     DATE         NOT NULL,
    Estado_cuenta        VARCHAR(30)  NOT NULL
        CHECK (Estado_cuenta IN ('Activa','Suspendida','Bloqueada')),
    -- Hash de la contraseña (bcrypt vía pgcrypto). NUNCA se guarda en
    -- texto plano ni se "desencripta": solo se compara con fn_verificar_clave.
    Clave_Hash           TEXT,
    Fecha_Cambio_Clave   TIMESTAMP,
    Direccion_habitacion TEXT         NOT NULL,
    Correo_Institucional VARCHAR(150) NOT NULL UNIQUE,
    Telefono_Personal    VARCHAR(20)  NOT NULL,
    Ultima_Conexion      TIMESTAMP,
    Indice_Recurrencia   NUMERIC(5,2) DEFAULT 0,
    Fecha_apertura       DATE         NOT NULL DEFAULT CURRENT_DATE,
    Tipo_Categoria       VARCHAR(50)  REFERENCES Categoria_Fidelidad(Tipo_Categoria),
    -- Campos de seguridad del enunciado (MFA, intentos fallidos)
    Intentos_fallidos    SMALLINT     DEFAULT 0,
    MFA_habilitado       BOOLEAN      DEFAULT FALSE
);

CREATE TABLE Auditoria_Sesion (
    Cedula_Miembro    VARCHAR(20) NOT NULL REFERENCES Miembro(Cedula_Miembro),
    Fecha_Hora_Acceso TIMESTAMP   NOT NULL,
    Geolocalizacion   TEXT,
    IP_Origen         INET,
    UUID_Dispositivo  UUID,
    PRIMARY KEY (Cedula_Miembro, Fecha_Hora_Acceso)
);

-- MFA_Codigo: entidad débil de Miembro. Códigos temporales de
-- segundo factor (enviados por correo institucional). Cada intento
-- de login con MFA genera una fila nueva; el código expira a los
-- pocos minutos y se marca como usado tras la verificación, para
-- que no pueda reutilizarse.
-- PK compuesta: (Cedula_Miembro, Fecha_generado) — clave parcial:
-- Fecha_generado (el instante de generación distingue cada código
-- de un mismo miembro).
CREATE TABLE MFA_Codigo (
    Cedula_Miembro  VARCHAR(20) NOT NULL REFERENCES Miembro(Cedula_Miembro)
                    ON DELETE CASCADE,
    Fecha_generado  TIMESTAMP   NOT NULL DEFAULT now(),
    Codigo          VARCHAR(6)  NOT NULL,           -- código de 6 dígitos
    Fecha_expira    TIMESTAMP   NOT NULL,            -- normalmente +5 minutos
    Usado           BOOLEAN     NOT NULL DEFAULT FALSE,
    PRIMARY KEY (Cedula_Miembro, Fecha_generado)
);

CREATE INDEX idx_mfa_cedula_vigente ON MFA_Codigo(Cedula_Miembro, Usado, Fecha_expira);

-- Periodo_Vinculacion: entidad débil de Miembro (relación Posee)
-- PK compuesta: (Cedula_Miembro, fecha_inicio) — clave parcial: fecha_inicio
-- Un mismo miembro no puede tener dos periodos que comiencen el mismo día.
CREATE TABLE Periodo_Vinculacion (
    Cedula_Miembro VARCHAR(20) NOT NULL REFERENCES Miembro(Cedula_Miembro)
                   ON DELETE CASCADE,
    fecha_inicio   DATE        NOT NULL,
    Rol            VARCHAR(30) NOT NULL
        CHECK (Rol IN ('Estudiante','Becario','Preparador',
                       'Docente','Personal_Administrativo','Egresado')),
    fecha_fin      DATE,
    PRIMARY KEY (Cedula_Miembro, fecha_inicio),
    CONSTRAINT chk_fechas_vinc CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio)
);

-- ── Subtipos de Miembro (especialización vía FK, NO INHERITS) ───
-- Cada subtipo es una tabla independiente con su propia PK
-- (Cedula_Miembro) que referencia a Miembro. Solo contiene los
-- atributos PROPIOS del subtipo — los atributos compartidos
-- (nombre, correo, etc.) viven una sola vez en Miembro.
-- Esto evita la duplicación de filas que ocurría con INHERITS
-- (donde una fila en un subtipo también aparecía al consultar
-- la tabla padre).

CREATE TABLE Estudiante (
    Cedula_Miembro VARCHAR(20)  PRIMARY KEY
                   REFERENCES Miembro(Cedula_Miembro) ON DELETE CASCADE,
    Promedio       NUMERIC(4,2),
    Escuela        VARCHAR(150),
    Estatus_beca   VARCHAR(50),
    Semestre       SMALLINT CHECK (Semestre IS NULL OR Semestre > 0),
    facultad       VARCHAR(150)
);

-- Preparador: especialización de Estudiante (PK también es FK a Estudiante)
CREATE TABLE Preparador (
    Cedula_Miembro      VARCHAR(20) PRIMARY KEY
                        REFERENCES Estudiante(Cedula_Miembro) ON DELETE CASCADE,
    Asignatura_Asignada VARCHAR(150),
    Horas_Ayudantia     NUMERIC(5,2)
        CHECK (Horas_Ayudantia IS NULL OR Horas_Ayudantia >= 0)
);

-- Becario: especialización de Estudiante (PK también es FK a Estudiante)
CREATE TABLE Becario (
    Cedula_Miembro       VARCHAR(20) PRIMARY KEY
                         REFERENCES Estudiante(Cedula_Miembro) ON DELETE CASCADE,
    Tipo_Beca            VARCHAR(50)
        CHECK (Tipo_Beca IN ('Ayuda_Economica','Excelencia','Comedor')),
    Estatus_Beneficio    VARCHAR(50),
    Indice_Mantenimiento NUMERIC(4,2)
);

CREATE TABLE Egresado (
    Cedula_Miembro   VARCHAR(20) PRIMARY KEY
                     REFERENCES Miembro(Cedula_Miembro) ON DELETE CASCADE,
    Titulo           VARCHAR(200),
    Ano_graduacion   SMALLINT,
    Indice_academico NUMERIC(4,2)
);

CREATE TABLE Personal_Administrativo (
    Cedula_Miembro       VARCHAR(20) PRIMARY KEY
                         REFERENCES Miembro(Cedula_Miembro) ON DELETE CASCADE,
    Unidad_Adscripcion   VARCHAR(150),
    Cargo_Administrativo VARCHAR(150),
    Carga_semanal        NUMERIC(4,2)
        CHECK (Carga_semanal IS NULL OR Carga_semanal >= 0)
);

CREATE TABLE Docente (
    Cedula_Miembro      VARCHAR(20) PRIMARY KEY
                        REFERENCES Miembro(Cedula_Miembro) ON DELETE CASCADE,
    Codigo_investigador VARCHAR(50),
    escalafon_docente   VARCHAR(100),
    Carga_semanal       NUMERIC(4,2)
        CHECK (Carga_semanal IS NULL OR Carga_semanal >= 0)
);

-- ── Beneficiario ─────────────────────────────────────────────────
CREATE TABLE Beneficiario (
    Documento_identidad           VARCHAR(30)  NOT NULL PRIMARY KEY,
    Cedula_Miembro                VARCHAR(20)  NOT NULL
        REFERENCES Miembro(Cedula_Miembro),
    Nombre                        VARCHAR(200) NOT NULL,
    fecha_nacimiento_beneficiario DATE         NOT NULL,
    Parentesco                    VARCHAR(50)  NOT NULL,
    -- Atributos de carga menor
    Esquema_vacunacion            TEXT,
    Centro_edu_inicial            VARCHAR(200),
    -- Atributos de carga mayor estudiando
    Constancia_est_Universitarios TEXT,
    Certificado_solteria          TEXT,        -- ruta/URL del documento
    -- Control de vigencia
    Estatus_beneficios            VARCHAR(30)
        CHECK (Estatus_beneficios IN ('Activo','Inactivo','Suspendido')),
    fecha_inicio                  DATE,
    fecha_fin                     DATE,
    CONSTRAINT chk_fechas_benef CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio)
);

-- ── Billetera_TAI ────────────────────────────────────────────────
CREATE TABLE Billetera_TAI (
    UID            UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
    Cedula_Miembro VARCHAR(20)   NOT NULL UNIQUE
        REFERENCES Miembro(Cedula_Miembro),
    Saldo_Virtual  NUMERIC(18,4) DEFAULT 0 CHECK (Saldo_Virtual >= 0),
    Saldo_Restante NUMERIC(18,4) DEFAULT 0 CHECK (Saldo_Restante >= 0)
);

-- ── Servicio ─────────────────────────────────────────────────────
CREATE TABLE Servicio (
    Codigo_Servicio       VARCHAR(50) PRIMARY KEY,
    Descripcion_detallada TEXT,
    Requisitos            TEXT,
    Estado_Servicio       VARCHAR(50)
        CHECK (Estado_Servicio IN ('Activo','Inactivo','Suspendido')),
    Perfil_solicitante    TEXT,
    id_entidad            INT NOT NULL
        REFERENCES Entidad_Prestadora(id_entidad) ON DELETE CASCADE
);

-- ── Tarifa_Servicio ──────────────────────────────────────────────
CREATE TABLE Tarifa_Servicio (
    Perfil_solicitante VARCHAR(30)   NOT NULL,
    Codigo_Servicio    VARCHAR(30)   NOT NULL
        REFERENCES Servicio(Codigo_Servicio),
    Precio_base        NUMERIC(18,4) NOT NULL CHECK (Precio_base >= 0),
    Limite_costo       NUMERIC(18,4)          CHECK (Limite_costo IS NULL OR Limite_costo >= Precio_base),
    Fecha_Inicio       DATE,
    Fecha_Fin          DATE,
    PRIMARY KEY (Perfil_solicitante, Codigo_Servicio),
    CONSTRAINT chk_fechas_tarifa CHECK (Fecha_Fin IS NULL OR Fecha_Fin >= Fecha_Inicio)
);

-- ── Aplica_En: Tarifa_Servicio ↔ Sede ───────────────────────────
-- Permite ajuste por sede (precios distintos Montalbán vs Guayana)
CREATE TABLE Aplica_En (
    nombre_sede        VARCHAR(100)  NOT NULL
        REFERENCES Sede(nombre_sede) ON DELETE CASCADE,
    codigo_servicio    VARCHAR(50)   NOT NULL,
    perfil_solicitante VARCHAR(100)  NOT NULL,
    precio_base        DECIMAL(10,2) NOT NULL CHECK (precio_base >= 0),
    limite_costo       DECIMAL(10,2)          CHECK (limite_costo IS NULL OR limite_costo >= precio_base),
    PRIMARY KEY (nombre_sede, codigo_servicio, perfil_solicitante),
    CONSTRAINT fk_aplica_tarifa FOREIGN KEY (codigo_servicio, perfil_solicitante)
        REFERENCES Tarifa_Servicio(Codigo_Servicio, Perfil_solicitante) ON DELETE CASCADE
);

-- ── Clasificado_En: Tarifa_Servicio ↔ Categoria_Fidelidad ───────
CREATE TABLE Clasificado_En (
    Perfil_solicitante VARCHAR(30)  NOT NULL,
    Codigo_Servicio    VARCHAR(30)  NOT NULL,
    Tipo_Categoria     VARCHAR(50)  NOT NULL
        REFERENCES Categoria_Fidelidad(Tipo_Categoria),
    PRIMARY KEY (Perfil_solicitante, Codigo_Servicio, Tipo_Categoria),
    FOREIGN KEY (Perfil_solicitante, Codigo_Servicio)
        REFERENCES Tarifa_Servicio(Perfil_solicitante, Codigo_Servicio)
);

-- ── Asignado_En: Servicio ↔ Espacio_Fisico ───────────────────────
CREATE TABLE Asignado_En (
    Codigo_Servicio   VARCHAR(50)  NOT NULL
        REFERENCES Servicio(Codigo_Servicio),
    Nombre_Edif       VARCHAR(100) NOT NULL,
    Direccion_Interna TEXT         NOT NULL,
    Num_identificador INTEGER      NOT NULL,
    PRIMARY KEY (Codigo_Servicio, Nombre_Edif, Direccion_Interna, Num_identificador),
    FOREIGN KEY (Nombre_Edif, Direccion_Interna, Num_identificador)
        REFERENCES Espacio_Fisico(Nombre_Edif, Direccion_Interna, Num_identificador)
);

-- ── Oferta_Laboral ───────────────────────────────────────────────
CREATE TABLE Oferta_Laboral (
    id_entidad_externa   INT          NOT NULL,
    cargo_laboral        VARCHAR(100) NOT NULL,
    perfil_buscado       TEXT,
    beneficios           TEXT,
    rango_fecha_graduacion VARCHAR(100),
    fecha_oferta         DATE,
    responsabilidades    TEXT,
    estatus_vacante      VARCHAR(50)
        CHECK (estatus_vacante IN ('Disponible','Finalizada')),
    indice_academico_min DECIMAL(4,2),
    PRIMARY KEY (id_entidad_externa, cargo_laboral),
    CONSTRAINT fk_oferta_externa FOREIGN KEY (id_entidad_externa)
        REFERENCES Externa(id_entidad) ON DELETE CASCADE
);

-- Sugerida_A: Oferta_Laboral ↔ Egresado
CREATE TABLE Sugerida_A (
    id_entidad_externa INT         NOT NULL,
    cargo_laboral      VARCHAR(100) NOT NULL,
    Cedula_Miembro     VARCHAR(20)  NOT NULL
        REFERENCES Egresado(Cedula_Miembro),
    PRIMARY KEY (id_entidad_externa, cargo_laboral, Cedula_Miembro),
    FOREIGN KEY (id_entidad_externa, cargo_laboral)
        REFERENCES Oferta_Laboral(id_entidad_externa, cargo_laboral) ON DELETE CASCADE
);

-- ── Solicitud_Servicio ───────────────────────────────────────────
CREATE TABLE Solicitud_Servicio (
    Identificador     VARCHAR(30)  PRIMARY KEY,
    Cedula_Miembro    VARCHAR(20)  NOT NULL REFERENCES Miembro(Cedula_Miembro),
    Codigo_Servicio   VARCHAR(30)  NOT NULL REFERENCES Servicio(Codigo_Servicio),
    Estado_actual     VARCHAR(50)  NOT NULL
        CHECK (Estado_actual IN ('Pendiente','En_Proceso','Completada','Cancelada')),
    Fecha_Inicio      DATE,
    Fecha_Fin         DATE,
    Tiempo_resolucion INTERVAL,
    Fecha_creacion    TIMESTAMP    DEFAULT now(),
    CONSTRAINT chk_fechas_sol CHECK (Fecha_Fin IS NULL OR Fecha_Fin >= Fecha_Inicio)
);

-- ── Acompanante_Temporal ─────────────────────────────────────────
CREATE TABLE Acompanante_Temporal (
    Documento_identidad_Acom VARCHAR(30)  NOT NULL PRIMARY KEY,
    Identificador            VARCHAR(20)  NOT NULL
        REFERENCES Solicitud_Servicio(Identificador),
    Nombre_acompanante       VARCHAR(200) NOT NULL,
    Estado_activo            BOOLEAN      DEFAULT TRUE
);

-- ── Paso_Actividad ───────────────────────────────────────────────
CREATE TABLE Paso_Actividad (
    Identificador  VARCHAR(30) NOT NULL
        REFERENCES Solicitud_Servicio(Identificador),
    Orden_paso     INTEGER     NOT NULL CHECK (Orden_paso > 0),
    Estado_paso    VARCHAR(50) NOT NULL
        CHECK (Estado_paso IN ('Pendiente','En_Proceso','Completado','Rechazado')),
    Fecha_evento   DATE,
    Responsable    VARCHAR(200),
    Observaciones  TEXT,
    PRIMARY KEY (Identificador, Orden_paso)
);

-- ── Factura ──────────────────────────────────────────────────────
CREATE TABLE Factura (
    Numero_control       VARCHAR(50)   PRIMARY KEY,
    Identificador        VARCHAR(30)   NOT NULL
        REFERENCES Solicitud_Servicio(Identificador),
    Cedula_Miembro       VARCHAR(20)   NOT NULL
        REFERENCES Miembro(Cedula_Miembro),
    Estatus_factura      VARCHAR(30)   NOT NULL DEFAULT 'Pendiente'
        CHECK (Estatus_factura IN ('Pendiente','Parcial','Pagada','Anulada')),
    Fecha_emision        TIMESTAMP     DEFAULT now(),
    Monto_total          NUMERIC(18,4) NOT NULL DEFAULT 0 CHECK (Monto_total >= 0),
    Saldo_restante_pagar NUMERIC(18,4) NOT NULL DEFAULT 0 CHECK (Saldo_restante_pagar >= 0)
);

-- ── Folio_Consumo ────────────────────────────────────────────────
CREATE TABLE Folio_Consumo (
    Identificador  VARCHAR(50) NOT NULL,
    Fecha_apertura DATE        NOT NULL DEFAULT CURRENT_DATE,
    Estado_cierre  VARCHAR(50) DEFAULT 'Abierto'
        CHECK (Estado_cierre IN ('Abierto','Cerrado')),
    PRIMARY KEY (Identificador, Fecha_apertura),
    CONSTRAINT fk_folio_solicitud FOREIGN KEY (Identificador)
        REFERENCES Solicitud_Servicio(Identificador) ON DELETE CASCADE
);

-- ── Item_consumo ─────────────────────────────────────────────────
CREATE TABLE Item_consumo (
    Identificador   VARCHAR(50)   NOT NULL,
    Fecha_apertura  DATE          NOT NULL,
    Concepto        VARCHAR(300)  NOT NULL,
    Precio_unitario NUMERIC(18,4) NOT NULL CHECK (Precio_unitario >= 0),
    Cantidad        NUMERIC(10,3) NOT NULL CHECK (Cantidad > 0),
    Impuesto        NUMERIC(5,2)  DEFAULT 0 CHECK (Impuesto >= 0),
    Fecha_cargo     DATE          NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (Identificador, Fecha_apertura, Concepto),
    CONSTRAINT fk_item_folio FOREIGN KEY (Identificador, Fecha_apertura)
        REFERENCES Folio_Consumo(Identificador, Fecha_apertura) ON DELETE CASCADE
);

-- ── Pago y subtipos ──────────────────────────────────────────────
CREATE TABLE Pago (
    Numero_control_Factura VARCHAR(50)   NOT NULL,
    fecha_operacion        TIMESTAMP     NOT NULL DEFAULT now(),
    canal_origen           VARCHAR(50)
        CHECK (canal_origen IN ('Digital','Presencial')),
    monto_liquidacion      NUMERIC(18,4) NOT NULL CHECK (monto_liquidacion > 0),
    tipo_pago              VARCHAR(20)   NOT NULL
        CHECK (tipo_pago IN ('Movil','Criptomonedas','Zelle','Efectivo','Tarjeta','TAI')),
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_pago_factura FOREIGN KEY (Numero_control_Factura)
        REFERENCES Factura(Numero_control) ON DELETE CASCADE,
    CONSTRAINT uq_pago_tipo UNIQUE (Numero_control_Factura, fecha_operacion, tipo_pago)
);

CREATE TABLE Pago_Movil (
    Numero_control_Factura VARCHAR(50) NOT NULL,
    fecha_operacion        TIMESTAMP   NOT NULL,
    tipo_pago              VARCHAR(20) NOT NULL CHECK (tipo_pago = 'Movil'),
    banco                  VARCHAR(100),
    referencia             VARCHAR(100),
    telefono_emisor        VARCHAR(20),
    fecha_movimiento       DATE,
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_movil_pago FOREIGN KEY (Numero_control_Factura, fecha_operacion, tipo_pago)
        REFERENCES Pago(Numero_control_Factura, fecha_operacion, tipo_pago) ON DELETE CASCADE
);

CREATE TABLE Criptomonedas (
    Numero_control_Factura VARCHAR(50)   NOT NULL,
    fecha_operacion        TIMESTAMP     NOT NULL,
    tipo_pago              VARCHAR(20)   NOT NULL CHECK (tipo_pago = 'Criptomonedas'),
    dxid                   VARCHAR(100),
    red_blockchain         VARCHAR(50),
    billetera              VARCHAR(200),
    tasa_conversion        NUMERIC(18,8),
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_cripto_pago FOREIGN KEY (Numero_control_Factura, fecha_operacion, tipo_pago)
        REFERENCES Pago(Numero_control_Factura, fecha_operacion, tipo_pago) ON DELETE CASCADE
);

CREATE TABLE Zelle (
    Numero_control_Factura VARCHAR(50) NOT NULL,
    fecha_operacion        TIMESTAMP   NOT NULL,
    tipo_pago              VARCHAR(20) NOT NULL CHECK (tipo_pago = 'Zelle'),
    nombre_titular         VARCHAR(200),
    correo_origen          VARCHAR(150),
    codigo_confirmacion    VARCHAR(50),
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_zelle_pago FOREIGN KEY (Numero_control_Factura, fecha_operacion, tipo_pago)
        REFERENCES Pago(Numero_control_Factura, fecha_operacion, tipo_pago) ON DELETE CASCADE
);

CREATE TABLE Efectivo (
    Numero_control_Factura VARCHAR(50)   NOT NULL,
    fecha_operacion        TIMESTAMP     NOT NULL,
    tipo_pago              VARCHAR(20)   NOT NULL CHECK (tipo_pago = 'Efectivo'),
    desglose_denominacion  JSONB,
    divisa                 VARCHAR(10),
    tasa_bcv               NUMERIC(18,8),
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_efectivo_pago FOREIGN KEY (Numero_control_Factura, fecha_operacion, tipo_pago)
        REFERENCES Pago(Numero_control_Factura, fecha_operacion, tipo_pago) ON DELETE CASCADE
);

CREATE TABLE Tarjeta (
    Numero_control_Factura VARCHAR(50) NOT NULL,
    fecha_operacion        TIMESTAMP   NOT NULL,
    tipo_pago              VARCHAR(20) NOT NULL CHECK (tipo_pago = 'Tarjeta'),
    tipo_red               VARCHAR(50)
        CHECK (tipo_red IN ('Nacional','Internacional')),
    fecha_vencimiento      DATE,
    compania               VARCHAR(100),
    num_tarjeta            VARCHAR(20),
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_tarjeta_pago FOREIGN KEY (Numero_control_Factura, fecha_operacion, tipo_pago)
        REFERENCES Pago(Numero_control_Factura, fecha_operacion, tipo_pago) ON DELETE CASCADE
);

CREATE TABLE Pago_TAI (
    Numero_control_Factura VARCHAR(50)   NOT NULL,
    fecha_operacion        TIMESTAMP     NOT NULL,
    tipo_pago              VARCHAR(20)   NOT NULL CHECK (tipo_pago = 'TAI'),
    pos_terminal           VARCHAR(50),
    recibo_digital         TEXT,
    saldo_restante         NUMERIC(18,4),
    uid_billetera          UUID          NOT NULL,
    PRIMARY KEY (Numero_control_Factura, fecha_operacion),
    CONSTRAINT fk_tai_pago FOREIGN KEY (Numero_control_Factura, fecha_operacion, tipo_pago)
        REFERENCES Pago(Numero_control_Factura, fecha_operacion, tipo_pago) ON DELETE CASCADE,
    CONSTRAINT fk_tai_billetera FOREIGN KEY (uid_billetera)
        REFERENCES Billetera_TAI(UID)
);


-- ================================================================
-- SECCIÓN 3: ALTER — CONSTRAINTS ADICIONALES
-- ================================================================

-- Miembro: Estado_cuenta válido desde enunciado
ALTER TABLE Miembro
    ADD CONSTRAINT chk_estado_cuenta
    CHECK (Estado_cuenta IN ('Activa','Suspendida','Bloqueada'));

-- Miembro: fecha_nacimiento no puede ser futura
ALTER TABLE Miembro
    ADD CONSTRAINT chk_nacimiento_pasado
    CHECK (fecha_nacimiento < CURRENT_DATE);

-- Miembro: Indice_Recurrencia no puede ser negativo
ALTER TABLE Miembro
    ADD CONSTRAINT chk_indice_recurrencia
    CHECK (Indice_Recurrencia IS NULL OR Indice_Recurrencia >= 0);

-- Beneficiario: fechas coherentes con el enunciado
ALTER TABLE Beneficiario
    ADD CONSTRAINT chk_nacimiento_benef
    CHECK (fecha_nacimiento_beneficiario < CURRENT_DATE);

-- Categoria_Fidelidad: descuento entre 0 y 100
ALTER TABLE Categoria_Fidelidad
    ADD CONSTRAINT chk_descuento
    CHECK (Descuento_global >= 0 AND Descuento_global <= 100);

-- Factura: Saldo_restante_pagar <= Monto_total
ALTER TABLE Factura
    ADD CONSTRAINT chk_saldo_factura
    CHECK (Saldo_restante_pagar <= Monto_total);

-- Pago: monto positivo (ya en CHECK inline, reforzamos a nivel ALTER)
ALTER TABLE Pago
    ADD CONSTRAINT chk_monto_positivo
    CHECK (monto_liquidacion > 0);

-- Solicitud_Servicio: Estado válido
ALTER TABLE Solicitud_Servicio
    ADD CONSTRAINT chk_estado_solicitud
    CHECK (Estado_actual IN ('Pendiente','En_Proceso','Completada','Cancelada'));

-- Espacio_Fisico: Capacidad positiva (ya inline, refuerzo semántico)
ALTER TABLE Espacio_Fisico
    ADD CONSTRAINT chk_aforo_positivo
    CHECK (Capacidad_aforo > 0);

-- Beneficiario: el enunciado indica que el vínculo familiar permanente
-- "está diseñado para el personal administrativo y docente que goza
-- de beneficios contractuales" — se valida con un trigger (ver más abajo,
-- pues requiere consultar tablas de subtipos, no representable en CHECK puro).

-- Oferta_Laboral: fecha de oferta no puede ser futura
ALTER TABLE Oferta_Laboral
    ADD CONSTRAINT chk_fecha_oferta
    CHECK (fecha_oferta <= CURRENT_DATE);

-- Paso_Actividad: Fecha_evento no puede ser anterior a hoy si está pendiente
-- (validación de coherencia, se complementa con el trigger de cierre automático)

-- Solicitud_Servicio: Fecha_Fin no puede ser anterior a Fecha_Inicio (ya existe);
-- agregamos que no puede haber Fecha_Fin si el estado no es terminal
ALTER TABLE Solicitud_Servicio
    ADD CONSTRAINT chk_fecha_fin_estado
    CHECK (Fecha_Fin IS NULL OR Estado_actual IN ('Completada','Cancelada'));


-- ================================================================
-- SECCIÓN 4: FUNCIONES Y TRIGGERS
-- ================================================================

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 1: Actualizar saldo de Factura al registrar un Pago
-- Enunciado: "ante la inserción de una nueva liquidación de pago,
-- el sistema debe actualizar automáticamente el saldo restante de
-- la factura vinculada y, si el monto acumulado iguala o supera
-- la deuda, modificar el estatus a 'Pagada' de forma instantánea."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_actualizar_saldo_factura()
RETURNS TRIGGER AS $$
DECLARE
    v_saldo_actual NUMERIC(18,4);
BEGIN
    -- Restar el monto del pago al saldo restante
    UPDATE Factura
    SET Saldo_restante_pagar = GREATEST(0, Saldo_restante_pagar - NEW.monto_liquidacion)
    WHERE Numero_control = NEW.Numero_control_Factura
    RETURNING Saldo_restante_pagar INTO v_saldo_actual;

    -- Si el saldo llegó a 0, marcar la factura como Pagada
    IF v_saldo_actual = 0 THEN
        UPDATE Factura
        SET Estatus_factura = 'Pagada'
        WHERE Numero_control = NEW.Numero_control_Factura;
    ELSE
        -- Si hay pagos parciales, marcar como Parcial
        UPDATE Factura
        SET Estatus_factura = 'Parcial'
        WHERE Numero_control = NEW.Numero_control_Factura
          AND Estatus_factura = 'Pendiente';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_actualizar_saldo_factura
AFTER INSERT ON Pago
FOR EACH ROW
EXECUTE FUNCTION fn_actualizar_saldo_factura();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 2: Suspender cuenta cuando no quedan vinculaciones activas
-- Enunciado: "si un miembro se queda sin vinculaciones vigentes,
-- el sistema debe proceder a suspender su acceso al portal."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_suspender_cuenta_sin_vinculacion()
RETURNS TRIGGER AS $$
DECLARE
    v_activas INTEGER;
BEGIN
    -- Contar vinculaciones activas restantes del miembro
    SELECT COUNT(*) INTO v_activas
    FROM Periodo_Vinculacion
    WHERE Cedula_Miembro = NEW.Cedula_Miembro
      AND fecha_fin IS NULL;

    -- Si no quedan vinculaciones activas, suspender la cuenta
    IF v_activas = 0 THEN
        UPDATE Miembro
        SET Estado_cuenta = 'Suspendida'
        WHERE Cedula_Miembro = NEW.Cedula_Miembro;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_suspender_sin_vinculacion
AFTER UPDATE OF fecha_fin ON Periodo_Vinculacion
FOR EACH ROW
WHEN (NEW.fecha_fin IS NOT NULL)
EXECUTE FUNCTION fn_suspender_cuenta_sin_vinculacion();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 3: Grabar fecha_evento automáticamente al completar paso
-- Enunciado: "Cada vez que un empleado marca un paso de actividad
-- como finalizado, el sistema graba por sí solo la fecha y hora
-- exactas de ese evento. Esto impide que los tiempos sean alterados
-- manualmente."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_cerrar_paso_actividad()
RETURNS TRIGGER AS $$
BEGIN
    -- Solo actuar cuando el estado cambia a 'Completado'
    IF NEW.Estado_paso = 'Completado' AND
       (OLD.Estado_paso IS DISTINCT FROM 'Completado') THEN
        NEW.Fecha_evento := CURRENT_DATE;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_cerrar_paso_actividad
BEFORE UPDATE OF Estado_paso ON Paso_Actividad
FOR EACH ROW
EXECUTE FUNCTION fn_cerrar_paso_actividad();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 4: Validar que el precio de la tarifa esté dentro
-- de los límites definidos para la categoría y sede.
-- Enunciado: "el sistema debe validar que el monto propuesto
-- se encuentre estrictamente dentro de los límites definidos
-- para la categoría y sede correspondiente."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_validar_precio_tarifa()
RETURNS TRIGGER AS $$
DECLARE
    v_limite NUMERIC(18,4);
BEGIN
    -- Obtener el límite de costo máximo para este servicio en cualquier sede
    SELECT MIN(ae.limite_costo) INTO v_limite
    FROM Aplica_En ae
    WHERE ae.codigo_servicio = NEW.Codigo_Servicio
      AND ae.perfil_solicitante = NEW.Perfil_solicitante;

    -- Si existe un límite y el precio base lo supera, rechazar
    IF v_limite IS NOT NULL AND NEW.Precio_base > v_limite THEN
        RAISE EXCEPTION
            'El precio_base (%) supera el límite de costo (%) definido para el servicio % en la sede.',
            NEW.Precio_base, v_limite, NEW.Codigo_Servicio;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_precio_tarifa
BEFORE INSERT OR UPDATE ON Tarifa_Servicio
FOR EACH ROW
EXECUTE FUNCTION fn_validar_precio_tarifa();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 5: Archivar acompañante al completarse/cancelarse solicitud
-- Enunciado: "Una vez finalizado el evento o el uso del recurso,
-- el vínculo con el acompañante se archiva junto a la solicitud."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_archivar_acompanante()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.Estado_actual IN ('Completada','Cancelada') THEN
        UPDATE Acompanante_Temporal
        SET Estado_activo = FALSE
        WHERE Identificador = NEW.Identificador;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_archivar_acompanante
AFTER UPDATE OF Estado_actual ON Solicitud_Servicio
FOR EACH ROW
EXECUTE FUNCTION fn_archivar_acompanante();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 6: Gestión de mayoría de edad en Beneficiario
-- Enunciado: "cuando un beneficiario cumple la mayoría de edad,
-- el sistema debe detectar este cambio para eliminar los atributos
-- de carga menor y exigir la constancia de estudios universitarios."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_upgradebeneficiario_mayoria_edad()
RETURNS TRIGGER AS $$
BEGIN
    -- Si el beneficiario ya cumplió 18 años
    IF (CURRENT_DATE >= NEW.fecha_nacimiento_beneficiario + INTERVAL '18 years') THEN
        NEW.Esquema_vacunacion  := NULL;
        NEW.Centro_edu_inicial  := NULL;
        -- Si no tiene constancia universitaria, suspender beneficio
        IF NEW.Constancia_est_Universitarios IS NULL THEN
            NEW.Estatus_beneficios := 'Suspendido';
        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_mayoria_edad_beneficiario
BEFORE INSERT OR UPDATE ON Beneficiario
FOR EACH ROW
EXECUTE FUNCTION fn_upgradebeneficiario_mayoria_edad();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 7: Marcar espacio como Ocupado al crear solicitud
-- Enunciado: "el registro de una nueva reservación debe marcar
-- el espacio físico como no disponible durante el bloque horario."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_marcar_espacio_ocupado()
RETURNS TRIGGER AS $$
BEGIN
    -- Marcar todos los espacios asignados al servicio como Ocupados
    UPDATE Espacio_Fisico ef
    SET Estatus = 'Ocupado'
    FROM Asignado_En ae
    WHERE ae.Codigo_Servicio = NEW.Codigo_Servicio
      AND ae.Nombre_Edif = ef.Nombre_Edif
      AND ae.Direccion_Interna = ef.Direccion_Interna
      AND ae.Num_identificador = ef.Num_identificador
      AND ef.Estatus = 'Disponible';

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_marcar_espacio_ocupado
AFTER INSERT ON Solicitud_Servicio
FOR EACH ROW
EXECUTE FUNCTION fn_marcar_espacio_ocupado();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 8: Liberar espacio al completar/cancelar solicitud
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_liberar_espacio()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.Estado_actual IN ('Completada','Cancelada') AND
       OLD.Estado_actual NOT IN ('Completada','Cancelada') THEN
        UPDATE Espacio_Fisico ef
        SET Estatus = 'Disponible'
        FROM Asignado_En ae
        WHERE ae.Codigo_Servicio = NEW.Codigo_Servicio
          AND ae.Nombre_Edif = ef.Nombre_Edif
          AND ae.Direccion_Interna = ef.Direccion_Interna
          AND ae.Num_identificador = ef.Num_identificador;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_liberar_espacio
AFTER UPDATE OF Estado_actual ON Solicitud_Servicio
FOR EACH ROW
EXECUTE FUNCTION fn_liberar_espacio();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 9: Evitar solapamiento de reservaciones en un mismo espacio
-- Enunciado: "El sistema de infraestructura mantiene un registro de
-- disponibilidad en tiempo real para evitar solapamientos en las
-- reservaciones." y "el registro de una nueva reservación debe marcar
-- el espacio físico como no disponible durante el bloque horario
-- solicitado, evitando duplicidades."
-- Se valida ANTES de marcar ocupado: si el espacio ya está 'Ocupado'
-- para el rango de fechas de la nueva solicitud, se rechaza.
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_validar_solapamiento_espacio()
RETURNS TRIGGER AS $$
DECLARE
    v_conflicto RECORD;
BEGIN
    -- Buscar si algún espacio asignado al servicio solicitado
    -- ya está reservado por otra solicitud activa en fechas que se solapan
    SELECT ss.Identificador, ss.Fecha_Inicio, ss.Fecha_Fin
    INTO v_conflicto
    FROM Asignado_En ae
    JOIN Solicitud_Servicio ss ON ss.Codigo_Servicio = ae.Codigo_Servicio
    WHERE ae.Codigo_Servicio = NEW.Codigo_Servicio
      AND ss.Identificador <> NEW.Identificador
      AND ss.Estado_actual IN ('Pendiente','En_Proceso')
      AND NEW.Fecha_Inicio IS NOT NULL
      AND ss.Fecha_Inicio IS NOT NULL
      AND (ss.Fecha_Fin IS NULL OR ss.Fecha_Fin >= NEW.Fecha_Inicio)
      AND (NEW.Fecha_Fin IS NULL OR NEW.Fecha_Fin >= ss.Fecha_Inicio)
    LIMIT 1;

    IF v_conflicto.Identificador IS NOT NULL THEN
        RAISE EXCEPTION
            'El espacio asignado al servicio % ya está reservado por la solicitud % en un rango de fechas solapado.',
            NEW.Codigo_Servicio, v_conflicto.Identificador;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_solapamiento_espacio
BEFORE INSERT ON Solicitud_Servicio
FOR EACH ROW
EXECUTE FUNCTION fn_validar_solapamiento_espacio();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 10: Impedir que un Paso_Actividad retroceda desde Completado
-- Enunciado: "impide que los tiempos de respuesta sean alterados
-- manualmente" — esto incluye no permitir revertir un paso ya cerrado.
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_bloquear_reapertura_paso()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.Estado_paso = 'Completado' AND NEW.Estado_paso <> 'Completado' THEN
        RAISE EXCEPTION
            'No se permite modificar el estado de un paso ya marcado como Completado (Identificador=%, Orden_paso=%).',
            OLD.Identificador, OLD.Orden_paso;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_bloquear_reapertura_paso
BEFORE UPDATE OF Estado_paso ON Paso_Actividad
FOR EACH ROW
EXECUTE FUNCTION fn_bloquear_reapertura_paso();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 11: Reclasificación automática de Categoria_Fidelidad
-- Enunciado: "Este cálculo permite clasificar automáticamente a los
-- miembros en categorías como Frecuente o Preferencial."
-- Se dispara cada vez que se recalcula el Indice_Recurrencia.
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_reclasificar_categoria_fidelidad()
RETURNS TRIGGER AS $$
DECLARE
    v_categoria VARCHAR(50);
BEGIN
    IF NEW.Indice_Recurrencia IS DISTINCT FROM OLD.Indice_Recurrencia THEN
        SELECT Tipo_Categoria INTO v_categoria
        FROM Categoria_Fidelidad
        WHERE NEW.Indice_Recurrencia BETWEEN Rango_indice_min AND Rango_indice_max
        ORDER BY Rango_indice_min DESC
        LIMIT 1;

        IF v_categoria IS NOT NULL THEN
            NEW.Tipo_Categoria := v_categoria;
        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_reclasificar_categoria_fidelidad
BEFORE UPDATE OF Indice_Recurrencia ON Miembro
FOR EACH ROW
EXECUTE FUNCTION fn_reclasificar_categoria_fidelidad();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 12: Bloqueo de cuenta por intentos fallidos de autenticación
-- Enunciado: "indicadores de vulnerabilidad como el conteo de intentos
-- fallidos de autenticación... active protocolos automáticos de
-- protección de datos y restricción de privilegios en tiempo real."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_bloquear_por_intentos_fallidos()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.Intentos_fallidos >= 5 THEN
        NEW.Estado_cuenta := 'Bloqueada';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_bloquear_por_intentos_fallidos
BEFORE UPDATE OF Intentos_fallidos ON Miembro
FOR EACH ROW
EXECUTE FUNCTION fn_bloquear_por_intentos_fallidos();

-- ────────────────────────────────────────────────────────────────
-- TRIGGER 13: Validar que Beneficiario solo se asocie a Docente o
-- Personal_Administrativo.
-- Enunciado: "Este esquema está diseñado para el personal
-- administrativo y docente que goza de beneficios contractuales."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_validar_rol_beneficiario()
RETURNS TRIGGER AS $$
DECLARE
    v_es_docente  BOOLEAN;
    v_es_admin    BOOLEAN;
BEGIN
    SELECT EXISTS(SELECT 1 FROM Docente WHERE Cedula_Miembro = NEW.Cedula_Miembro)
        INTO v_es_docente;
    SELECT EXISTS(SELECT 1 FROM Personal_Administrativo WHERE Cedula_Miembro = NEW.Cedula_Miembro)
        INTO v_es_admin;

    IF NOT (v_es_docente OR v_es_admin) THEN
        RAISE EXCEPTION
            'Solo Docentes o Personal Administrativo pueden registrar Beneficiarios (Cedula_Miembro=%).',
            NEW.Cedula_Miembro;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_rol_beneficiario
BEFORE INSERT ON Beneficiario
FOR EACH ROW
EXECUTE FUNCTION fn_validar_rol_beneficiario();

-- ────────────────────────────────────────────────────────────────
-- SEGURIDAD: Hash y verificación de contraseñas (pgcrypto / bcrypt)
--
-- Las contraseñas NUNCA se guardan en texto plano ni se "desencriptan".
-- Se aplica un hash de un solo sentido (bcrypt) con costo configurable
-- (work factor). Para validar un login se vuelve a hashear la clave
-- ingresada con la misma sal almacenada dentro del propio hash y se
-- compara el resultado — por eso crypt() sirve para ambas operaciones.
--
-- Uso típico desde el backend (vía consulta SQL parametrizada, NUNCA
-- concatenando strings):
--   Crear/cambiar clave:
--     SELECT fn_establecer_clave('V-12345678', 'MiClaveSegura123');
--   Verificar login:
--     SELECT fn_verificar_clave('V-12345678', 'MiClaveSegura123');  -- TRUE/FALSE
-- ────────────────────────────────────────────────────────────────

-- Establece (o cambia) la contraseña de un miembro, generando el hash.
CREATE OR REPLACE FUNCTION fn_establecer_clave(
    p_cedula      VARCHAR,
    p_clave_plana TEXT
)
RETURNS VOID AS $$
BEGIN
    UPDATE Miembro
    SET Clave_Hash = crypt(p_clave_plana, gen_salt('bf', 12)), -- bcrypt, factor de costo 12
        Fecha_Cambio_Clave = now()
    WHERE Cedula_Miembro = p_cedula;
END;
$$ LANGUAGE plpgsql;

-- Verifica una contraseña en texto plano contra el hash almacenado.
-- Devuelve TRUE si coincide, FALSE en caso contrario (o si no existe el miembro).
CREATE OR REPLACE FUNCTION fn_verificar_clave(
    p_cedula      VARCHAR,
    p_clave_plana TEXT
)
RETURNS BOOLEAN AS $$
DECLARE
    v_hash_guardado TEXT;
BEGIN
    SELECT Clave_Hash INTO v_hash_guardado
    FROM Miembro
    WHERE Cedula_Miembro = p_cedula;

    IF v_hash_guardado IS NULL THEN
        RETURN FALSE;
    END IF;

    -- crypt() con el hash existente como "salt" reconstruye el mismo hash
    -- si y solo si la clave en texto plano coincide.
    RETURN v_hash_guardado = crypt(p_clave_plana, v_hash_guardado);
END;
$$ LANGUAGE plpgsql;

-- ────────────────────────────────────────────────────────────────
-- SEGURIDAD: Segundo factor de autenticación (MFA por código)
--
-- Flujo de uso desde el backend:
--   1. Tras validar la contraseña, si Miembro.MFA_habilitado = TRUE:
--        SELECT fn_generar_codigo_mfa('V-12345678');  -- devuelve el código
--      El backend toma ese código y lo envía por correo institucional
--      (el envío de email NO ocurre en la base de datos).
--   2. El usuario escribe el código recibido; el backend valida con:
--        SELECT fn_verificar_codigo_mfa('V-12345678', '483920');
--      -> TRUE si es correcto, no expiró y no había sido usado antes.
-- ────────────────────────────────────────────────────────────────

-- Genera un código de 6 dígitos válido por 5 minutos para el miembro.
CREATE OR REPLACE FUNCTION fn_generar_codigo_mfa(p_cedula VARCHAR)
RETURNS VARCHAR AS $$
DECLARE
    v_codigo VARCHAR(6);
BEGIN
    -- Código numérico aleatorio de 6 dígitos, con ceros a la izquierda si aplica
    v_codigo := LPAD(FLOOR(random() * 1000000)::TEXT, 6, '0');

    INSERT INTO MFA_Codigo (Cedula_Miembro, Fecha_generado, Codigo, Fecha_expira)
    VALUES (p_cedula, now(), v_codigo, now() + INTERVAL '5 minutes');

    RETURN v_codigo;
END;
$$ LANGUAGE plpgsql;

-- Verifica el código ingresado por el usuario: debe existir, no haber
-- expirado y no haber sido usado. Si es válido, lo marca como usado
-- (evita reutilización del mismo código).
CREATE OR REPLACE FUNCTION fn_verificar_codigo_mfa(
    p_cedula VARCHAR,
    p_codigo VARCHAR
)
RETURNS BOOLEAN AS $$
DECLARE
    v_fecha_generado TIMESTAMP;
BEGIN
    SELECT Fecha_generado INTO v_fecha_generado
    FROM MFA_Codigo
    WHERE Cedula_Miembro = p_cedula
      AND Codigo = p_codigo
      AND Usado = FALSE
      AND Fecha_expira > now()
    ORDER BY Fecha_generado DESC
    LIMIT 1;

    IF v_fecha_generado IS NULL THEN
        RETURN FALSE;
    END IF;

    UPDATE MFA_Codigo
    SET Usado = TRUE
    WHERE Cedula_Miembro = p_cedula
      AND Fecha_generado = v_fecha_generado;

    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;


-- ================================================================
-- SECCIÓN 5: FUNCIONES DE CONSULTA / ALGORITMOS
-- ================================================================

-- ────────────────────────────────────────────────────────────────
-- FUNCIÓN 1: Calcular tiempo de resolución de una solicitud
-- Enunciado: "determinar el tiempo total de resolución restando
-- el momento de cierre del de apertura, descontando fines de semana."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_tiempo_resolucion_solicitud(p_identificador VARCHAR)
RETURNS INTERVAL AS $$
DECLARE
    v_inicio    TIMESTAMP;
    v_fin       TIMESTAMP;
    v_dias_hab  INTEGER := 0;
    v_cursor    DATE;
BEGIN
    SELECT Fecha_creacion, Fecha_Fin::TIMESTAMP
    INTO v_inicio, v_fin
    FROM Solicitud_Servicio
    WHERE Identificador = p_identificador;

    IF v_fin IS NULL THEN
        RETURN NULL; -- solicitud aún abierta
    END IF;

    -- Contar días hábiles (excluye sábado=6 y domingo=0)
    v_cursor := v_inicio::DATE;
    WHILE v_cursor < v_fin::DATE LOOP
        IF EXTRACT(DOW FROM v_cursor) NOT IN (0, 6) THEN
            v_dias_hab := v_dias_hab + 1;
        END IF;
        v_cursor := v_cursor + INTERVAL '1 day';
    END LOOP;

    RETURN (v_dias_hab || ' days')::INTERVAL
           + (v_fin - v_inicio::DATE::TIMESTAMP); -- suma fracción del día
END;
$$ LANGUAGE plpgsql;

-- ────────────────────────────────────────────────────────────────
-- FUNCIÓN 2: Calcular índice de recurrencia de un miembro
-- Enunciado: "evalúa el volumen de servicios consumidos exitosamente,
-- la puntualidad en los pagos y la frecuencia de reservaciones."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_calcular_indice_recurrencia(p_cedula VARCHAR)
RETURNS NUMERIC AS $$
DECLARE
    v_servicios_ok  INTEGER;
    v_pagos_puntuales INTEGER;
    v_reservaciones INTEGER;
    v_indice        NUMERIC(8,4);
BEGIN
    -- Servicios completados exitosamente
    SELECT COUNT(*) INTO v_servicios_ok
    FROM Solicitud_Servicio
    WHERE Cedula_Miembro = p_cedula
      AND Estado_actual = 'Completada';

    -- Facturas pagadas (proxy de puntualidad)
    SELECT COUNT(*) INTO v_pagos_puntuales
    FROM Factura
    WHERE Cedula_Miembro = p_cedula
      AND Estatus_factura = 'Pagada';

    -- Total de solicitudes (frecuencia de reservaciones)
    SELECT COUNT(*) INTO v_reservaciones
    FROM Solicitud_Servicio
    WHERE Cedula_Miembro = p_cedula;

    -- Fórmula ponderada (ajustable según criterio institucional)
    v_indice := (v_servicios_ok * 0.5)
              + (v_pagos_puntuales * 0.3)
              + (v_reservaciones * 0.2);

    -- Actualizar en Miembro
    UPDATE Miembro SET Indice_Recurrencia = v_indice
    WHERE Cedula_Miembro = p_cedula;

    RETURN v_indice;
END;
$$ LANGUAGE plpgsql;

-- ────────────────────────────────────────────────────────────────
-- FUNCIÓN 3: Devolver costo final de un servicio para un miembro
-- Enunciado: "devolver el costo final aplicando automáticamente
-- los descuentos o recargos según el perfil histórico del miembro."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION fn_costo_final_servicio(
    p_codigo_servicio VARCHAR,
    p_cedula          VARCHAR
)
RETURNS NUMERIC AS $$
DECLARE
    v_precio_base    NUMERIC(18,4);
    v_descuento      NUMERIC(5,2) := 0;
    v_perfil         VARCHAR(30);
    v_categoria      VARCHAR(50);
BEGIN
    -- Determinar perfil del miembro
    IF EXISTS (SELECT 1 FROM Egresado WHERE Cedula_Miembro = p_cedula) THEN
        v_perfil := 'Egresado';
    ELSIF EXISTS (SELECT 1 FROM Estudiante WHERE Cedula_Miembro = p_cedula) THEN
        v_perfil := 'Miembro_Activo';
    ELSIF EXISTS (SELECT 1 FROM Docente WHERE Cedula_Miembro = p_cedula)
       OR EXISTS (SELECT 1 FROM Personal_Administrativo WHERE Cedula_Miembro = p_cedula) THEN
        v_perfil := 'Miembro_Activo';
    ELSE
        v_perfil := 'Externo';
    END IF;

    -- Obtener precio base para ese perfil
    SELECT Precio_base INTO v_precio_base
    FROM Tarifa_Servicio
    WHERE Codigo_Servicio = p_codigo_servicio
      AND Perfil_solicitante = v_perfil;

    -- Obtener descuento según categoría de fidelidad del miembro
    SELECT cf.Descuento_global INTO v_descuento
    FROM Miembro m
    JOIN Categoria_Fidelidad cf ON cf.Tipo_Categoria = m.Tipo_Categoria
    WHERE m.Cedula_Miembro = p_cedula;

    v_descuento := COALESCE(v_descuento, 0);

    RETURN ROUND(v_precio_base * (1 - v_descuento / 100.0), 4);
END;
$$ LANGUAGE plpgsql;


-- ================================================================
-- SECCIÓN 6: PROCEDIMIENTOS ALMACENADOS (operaciones masivas)
-- ================================================================

-- ────────────────────────────────────────────────────────────────
-- PROCEDIMIENTO 1: Cierre masivo de folios al fin de mes
-- Enunciado: "ejecutar un cierre masivo de estados de cuenta al
-- final de cada mes, convirtiendo todos los folios pendientes
-- en facturas formales de manera simultánea."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE PROCEDURE sp_cierre_masivo_folios()
LANGUAGE plpgsql AS $$
DECLARE
    rec RECORD;
    v_numero_control VARCHAR(50);
    v_total          NUMERIC(18,4);
BEGIN
    FOR rec IN
        SELECT fc.Identificador, fc.Fecha_apertura, ss.Cedula_Miembro
        FROM Folio_Consumo fc
        JOIN Solicitud_Servicio ss ON ss.Identificador = fc.Identificador
        WHERE fc.Estado_cierre = 'Abierto'
    LOOP
        -- Calcular total del folio
        SELECT COALESCE(SUM(ic.Precio_unitario * ic.Cantidad * (1 + ic.Impuesto/100)), 0)
        INTO v_total
        FROM Item_consumo ic
        WHERE ic.Identificador = rec.Identificador
          AND ic.Fecha_apertura = rec.Fecha_apertura;

        -- Generar número de control único
        v_numero_control := 'FCT-' || rec.Identificador || '-' ||
                            TO_CHAR(rec.Fecha_apertura, 'YYYYMMDD');

        -- Crear factura si no existe
        INSERT INTO Factura (
            Numero_control, Identificador, Cedula_Miembro,
            Estatus_factura, Fecha_emision, Monto_total, Saldo_restante_pagar
        )
        VALUES (
            v_numero_control, rec.Identificador, rec.Cedula_Miembro,
            'Pendiente', now(), v_total, v_total
        )
        ON CONFLICT (Numero_control) DO NOTHING;

        -- Cerrar el folio
        UPDATE Folio_Consumo
        SET Estado_cierre = 'Cerrado'
        WHERE Identificador = rec.Identificador
          AND Fecha_apertura = rec.Fecha_apertura;
    END LOOP;
END;
$$;

-- ────────────────────────────────────────────────────────────────
-- PROCEDIMIENTO 2: Actualización global de tasas de conversión
-- Enunciado: "actualización global de las tasas de conversión para
-- criptomonedas y divisas, ajustando todos los precios activos."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE PROCEDURE sp_actualizar_tasas_conversion(p_nueva_tasa NUMERIC)
LANGUAGE plpgsql AS $$
BEGIN
    -- Actualizar tasa en todos los pagos en criptomonedas pendientes de confirmar
    UPDATE Criptomonedas
    SET tasa_conversion = p_nueva_tasa
    WHERE fecha_operacion::DATE = CURRENT_DATE;

    -- Actualizar tasa BCV en pagos de efectivo en divisas del día
    UPDATE Efectivo
    SET tasa_bcv = p_nueva_tasa
    WHERE fecha_operacion::DATE = CURRENT_DATE
      AND divisa <> 'VES';

    RAISE NOTICE 'Tasa de conversión actualizada a % para operaciones del día %',
                 p_nueva_tasa, CURRENT_DATE;
END;
$$;

-- ────────────────────────────────────────────────────────────────
-- PROCEDIMIENTO 3: Archivar vínculos expirados hace más de 1 año
-- Enunciado: "archiva permanentemente los datos de acompañantes
-- y beneficiarios cuyos vínculos han expirado hace más de un año."
-- ────────────────────────────────────────────────────────────────
CREATE OR REPLACE PROCEDURE sp_archivar_vinculos_expirados()
LANGUAGE plpgsql AS $$
BEGIN
    -- Desactivar acompañantes cuya solicitud cerró hace más de 1 año
    UPDATE Acompanante_Temporal at_
    SET Estado_activo = FALSE
    FROM Solicitud_Servicio ss
    WHERE at_.Identificador = ss.Identificador
      AND ss.Estado_actual IN ('Completada','Cancelada')
      AND ss.Fecha_Fin < (CURRENT_DATE - INTERVAL '1 year');

    -- Inactivar beneficiarios cuyo vínculo terminó hace más de 1 año
    UPDATE Beneficiario
    SET Estatus_beneficios = 'Inactivo'
    WHERE fecha_fin IS NOT NULL
      AND fecha_fin < (CURRENT_DATE - INTERVAL '1 year')
      AND Estatus_beneficios <> 'Inactivo';

    RAISE NOTICE 'Archivado completado para vínculos expirados antes de %',
                 (CURRENT_DATE - INTERVAL '1 year');
END;
$$;


-- ================================================================
-- SECCIÓN 7: DATOS DE PRUEBA (INSERT)

-- ── Sedes ────────────────────────────────────────────────────────
INSERT INTO Sede (nombre_sede) VALUES
    ('Montalban'),
    ('Guayana');

-- ── Edificaciones ────────────────────────────────────────────────
INSERT INTO Edificacion (Nombre_Edif, Direccion_Interna, nombre_sede) VALUES
    ('Edificio Cincuentenario',  'Av. Teheran, Urb. Montalbán', 'Montalban'),
    ('Edificio de Laboratorios', 'Av. Teheran, Urb. Montalbán', 'Montalban'),
    ('Edificio Central Guayana', 'Via Dalla Costa, Puerto Ordaz', 'Guayana');

-- ── Espacios Físicos ─────────────────────────────────────────────
-- Incluye los 3 estados posibles: Disponible, Ocupado, Mantenimiento
INSERT INTO Espacio_Fisico (Nombre_Edif, Direccion_Interna, Capacidad_aforo, Tipo_inmobiliario, Estatus) VALUES
    ('Edificio Cincuentenario', 'Av. Teheran, Urb. Montalbán', 200, 'Auditorio',   'Disponible'),
    ('Edificio Cincuentenario', 'Av. Teheran, Urb. Montalbán', 40,  'Salon',       'Disponible'),
    ('Edificio de Laboratorios','Av. Teheran, Urb. Montalbán', 30,  'Laboratorio', 'Disponible'),
    ('Edificio de Laboratorios','Av. Teheran, Urb. Montalbán', 25,  'Laboratorio', 'Mantenimiento'),
    ('Edificio Central Guayana','Via Dalla Costa, Puerto Ordaz',100, 'Auditorio',  'Disponible'),
    ('Edificio Central Guayana','Via Dalla Costa, Puerto Ordaz',50,  'Cancha',     'Ocupado');

-- ── Entidades Prestadoras ────────────────────────────────────────
-- 2 internas, 3 externas (variedad de categorías: Salud, Cultura, Deporte, Educacion Continua)
INSERT INTO Entidad_Prestadora (id_entidad, tipo_entidad) VALUES
    (1, 'Interna'),
    (2, 'Interna'),
    (3, 'Externa'),
    (4, 'Externa'),
    (5, 'Externa');

INSERT INTO Interna (id_entidad, tipo_entidad, codigo_presu, director_oficina) VALUES
    (1, 'Interna', 'PRES-DCEA-001', 'Dr. Luis Fuentes'),
    (2, 'Interna', 'PRES-CULT-002', 'Lic. Maria Gomez');

INSERT INTO Externa (id_entidad, tipo_entidad, rif, razon_social, contactos_legales, fecha_vencimiento_contrato) VALUES
    (3, 'Externa', 'J-12345678-9', 'TechCorp Venezuela C.A.',     'techcorp@legal.com',   '2027-12-31'),
    (4, 'Externa', 'J-98765432-1', 'Cafeteria El Buen Sabor',     'cafeteria@legal.com',  '2026-06-30'),
    (5, 'Externa', 'J-55667788-3', 'Centro Medico Vida Plena C.A.','vidaplena@legal.com', '2026-12-31');

-- ── Categorías de Fidelidad ──────────────────────────────────────
INSERT INTO Categoria_Fidelidad (Tipo_Categoria, Rango_indice_min, Rango_indice_max, Descuento_global, Prioridad_reservacion) VALUES
    ('Estandar',     0.00, 4.99,  0.00, 3),
    ('Frecuente',    5.00, 9.99,  5.00, 2),
    ('Preferencial', 10.00, 99.99, 10.00, 1);

-- ── Miembros (tabla base) ────────────────────────────────────────
-- Cubre los 3 estados de cuenta: Activa, Suspendida, Bloqueada
-- Cubre miembros con y sin MFA habilitado, y con intentos fallidos
INSERT INTO Miembro (Cedula_Miembro, Nombres_Completos, Apellidos_Completos, Sexo,
    fecha_nacimiento, Estado_cuenta, Fecha_Cambio_Clave, Direccion_habitacion,
    Correo_Institucional, Telefono_Personal, Fecha_apertura, Tipo_Categoria,
    Intentos_fallidos, MFA_habilitado) VALUES
    ('V-12345678', 'Ana Maria',    'Lopez Torres',   'F', '2001-03-15', 'Activa',     now(), 'Urb. La Castellana, Caracas', 'ana.lopez@ucab.edu.ve',     '04141234567', CURRENT_DATE, 'Frecuente',    1, TRUE),
    ('V-23456789', 'Carlos Jose',  'Perez Rivas',    'M', '1999-07-22', 'Activa',     now(), 'Urb. El Paraiso, Caracas',    'carlos.perez@ucab.edu.ve',  '04167654321', CURRENT_DATE, 'Preferencial', 0, TRUE),
    ('V-34567890', 'Maria Elena',  'Sanchez Blanco', 'F', '1985-11-30', 'Activa',     now(), 'Res. La Boyera, Caracas',     'maria.sanchez@ucab.edu.ve', '04121112233', CURRENT_DATE, 'Estandar',     0, FALSE),
    ('V-45678901', 'Pedro Luis',   'Martinez Gil',   'M', '1978-05-10', 'Activa',     now(), 'Urb. Los Chorros, Caracas',   'pedro.martinez@ucab.edu.ve','04169988776', CURRENT_DATE, 'Preferencial', 0, TRUE),
    ('V-56789012', 'Laura Beatriz','Herrera Mora',   'F', '2002-01-08', 'Activa',     now(), 'Urb. Bello Monte, Caracas',   'laura.herrera@ucab.edu.ve', '04143344556', CURRENT_DATE, 'Estandar',     2, FALSE),
    ('V-67890123', 'Jorge Andres', 'Dominguez Paz',  'M', '1990-09-18', 'Activa',     now(), 'Urb. Chuao, Caracas',         'jorge.dominguez@ucab.edu.ve','04125566778',CURRENT_DATE, 'Frecuente',    0, TRUE),
    ('V-78901234', 'Valentina',    'Rojas Castro',   'F', '2000-12-01', 'Suspendida', now(), 'Urb. Santa Fe, Caracas',      'valentina.rojas@ucab.edu.ve','04161237788',CURRENT_DATE - 800, 'Estandar', 0, FALSE),
    ('V-89012345', 'Ricardo',      'Vargas Leon',    'M', '1995-04-25', 'Bloqueada',  now(), 'Urb. Las Mercedes, Caracas',  'ricardo.vargas@ucab.edu.ve', '04249876543', CURRENT_DATE, 'Estandar',     5, FALSE);

-- ── Subtipos de Miembro ──────────────────────────────────────────
-- Cada miembro YA existe en la tabla Miembro (bloque anterior).
-- Aquí solo se inserta su Cedula_Miembro + los atributos PROPIOS
-- de cada subtipo — nunca se repiten los datos de Miembro.
INSERT INTO Estudiante (Cedula_Miembro, Promedio, Escuela, Estatus_beca, Semestre, facultad) VALUES
    ('V-12345678', 16.50, 'Ingenieria Informatica', 'Activa', 7, 'Ingenieria'),
    -- Estudiante con cuenta Suspendida (vínculo vencido, sin beca)
    ('V-78901234', 12.30, 'Comunicacion Social', NULL, 10, 'Humanidades');

-- Becario: requiere que Cedula_Miembro YA exista en Estudiante primero
-- (FK: Becario.Cedula_Miembro -> Estudiante.Cedula_Miembro)
INSERT INTO Estudiante (Cedula_Miembro, Promedio, Escuela, Estatus_beca, Semestre, facultad) VALUES
    ('V-56789012', 14.80, 'Derecho', 'Activa', 4, 'Ciencias Juridicas');

INSERT INTO Becario (Cedula_Miembro, Tipo_Beca, Estatus_Beneficio, Indice_Mantenimiento) VALUES
    ('V-56789012', 'Excelencia', 'Activo', 14.80);

-- Egresado: incluye uno con vínculo activo solamente como egresado (caso simple)
INSERT INTO Egresado (Cedula_Miembro, Titulo, Ano_graduacion, Indice_academico) VALUES
    ('V-23456789', 'Ingeniero en Informatica', 2022, 17.20),
    -- Egresado adicional, candidato a oferta finalizada
    ('V-89012345', 'Licenciado en Administracion', 2020, 15.40);

INSERT INTO Docente (Cedula_Miembro, Codigo_investigador, escalafon_docente, Carga_semanal) VALUES
    ('V-45678901', 'INV-2020-045', 'Asociado', 12.0);

INSERT INTO Personal_Administrativo (Cedula_Miembro, Unidad_Adscripcion, Cargo_Administrativo, Carga_semanal) VALUES
    ('V-34567890', 'Direccion de Servicios Estudiantiles', 'Coordinadora Administrativa', 40.0);

-- Preparador: requiere que Cedula_Miembro YA exista en Estudiante primero
-- (FK: Preparador.Cedula_Miembro -> Estudiante.Cedula_Miembro)
INSERT INTO Estudiante (Cedula_Miembro, Promedio, Escuela, Estatus_beca, Semestre, facultad) VALUES
    ('V-67890123', 15.90, 'Ingenieria Informatica', NULL, 9, 'Ingenieria');

INSERT INTO Preparador (Cedula_Miembro, Asignatura_Asignada, Horas_Ayudantia) VALUES
    ('V-67890123', 'Bases de Datos I', 6.0);

-- ── Periodos de Vinculacion ──────────────────────────────────────
-- Cubre: vinculación activa simple, vinculación finalizada + nueva activa
-- (trayectoria institucional: Estudiante → Egresado), y un miembro
-- sin ninguna vinculación activa (para validar el trigger de suspensión)
INSERT INTO Periodo_Vinculacion (Cedula_Miembro, fecha_inicio, Rol, fecha_fin) VALUES
    ('V-12345678', '2021-09-01', 'Estudiante',              NULL),
    ('V-56789012', '2022-09-01', 'Becario',                 NULL),
    ('V-23456789', '2018-09-01', 'Estudiante',              '2022-07-15'),
    ('V-23456789', '2022-07-16', 'Egresado',                NULL),
    ('V-45678901', '2010-01-15', 'Docente',                 NULL),
    ('V-34567890', '2015-03-01', 'Personal_Administrativo', NULL),
    ('V-67890123', '2023-01-10', 'Preparador',              NULL),
    -- Valentina: vínculo de estudiante ya finalizado, sin uno nuevo
    -- (motivo por el cual su Estado_cuenta quedó en 'Suspendida')
    ('V-78901234', '2016-09-01', 'Estudiante',              '2024-12-15'),
    -- Ricardo: egresado en 2020, vínculo finalizado, cuenta bloqueada
    -- por intentos fallidos (no relacionado a la vinculación)
    ('V-89012345', '2015-09-01', 'Estudiante',              '2020-07-20'),
    ('V-89012345', '2020-07-21', 'Egresado',                NULL);

-- ── Auditoría de sesiones ────────────────────────────────────────
INSERT INTO Auditoria_Sesion (Cedula_Miembro, Fecha_Hora_Acceso, Geolocalizacion, IP_Origen, UUID_Dispositivo) VALUES
    ('V-12345678', now() - INTERVAL '2 hours', '10.4819,-66.8792', '192.168.1.10', gen_random_uuid()),
    ('V-23456789', now() - INTERVAL '5 hours', '10.4819,-66.8792', '192.168.1.22', gen_random_uuid()),
    ('V-45678901', now() - INTERVAL '1 day',   '10.4819,-66.8792', '10.0.0.5',     gen_random_uuid()),
    ('V-89012345', now() - INTERVAL '3 hours', '8.3500,-62.6500',  '201.55.10.34', gen_random_uuid()),
    ('V-89012345', now() - INTERVAL '2 hours', '8.3500,-62.6500',  '201.55.10.34', gen_random_uuid());

-- ── Beneficiarios ────────────────────────────────────────────────
-- Caso 1: carga menor (con esquema de vacunación y centro educativo)
-- Caso 2: carga mayor que aún sigue estudiando (con constancia universitaria
--         y certificado de soltería; sin atributos de menor)
-- Ambos asociados a Docente o Personal_Administrativo, según restricción.
INSERT INTO Beneficiario (Documento_identidad, Cedula_Miembro, Nombre,
    fecha_nacimiento_beneficiario, Parentesco, Esquema_vacunacion,
    Centro_edu_inicial, Estatus_beneficios, fecha_inicio) VALUES
    ('V-87654321', 'V-45678901', 'Sofia Martinez', '2010-06-20',
     'Hija', 'Completo (MMR, Polio, DPT)', 'U.E. San Ignacio', 'Activo', '2015-01-01');

-- Beneficiario mayor de edad, ya con constancia universitaria cargada
INSERT INTO Beneficiario (Documento_identidad, Cedula_Miembro, Nombre,
    fecha_nacimiento_beneficiario, Parentesco,
    Constancia_est_Universitarios, Certificado_solteria,
    Estatus_beneficios, fecha_inicio) VALUES
    ('V-76543210', 'V-34567890', 'Diego Sanchez', '2003-11-15',
     'Hijo', 'constancias/diego_sanchez_2025.pdf', 'certificados/diego_sanchez_soltero.pdf',
     'Activo', '2015-03-01');

-- ── Billeteras TAI ───────────────────────────────────────────────
INSERT INTO Billetera_TAI (Cedula_Miembro, Saldo_Virtual, Saldo_Restante) VALUES
    ('V-12345678', 150.00, 150.00),
    ('V-23456789', 80.00,  80.00),
    ('V-45678901', 300.00, 300.00),
    ('V-34567890', 200.00, 200.00),
    ('V-56789012', 50.00,  50.00),
    ('V-67890123', 120.00, 120.00),
    ('V-78901234', 0.00,   0.00),
    ('V-89012345', 45.00,  45.00);

-- ── Servicios ────────────────────────────────────────────────────
INSERT INTO Servicio (Codigo_Servicio, Descripcion_detallada, Requisitos, Estado_Servicio, Perfil_solicitante, id_entidad) VALUES
    ('SRV-001', 'Alquiler de Auditorio Cincuentenario',  'Ser miembro activo o externo con contrato',  'Activo',    'Miembro_Activo,Egresado,Externo', 2),
    ('SRV-002', 'Solicitud de Titulo de Grado',          'Haber aprobado todos los creditos',          'Activo',    'Estudiante',                       1),
    ('SRV-003', 'Inscripcion Curso de Extension',        'Bachiller o universitario',                   'Activo',    'Miembro_Activo,Egresado,Externo', 2),
    ('SRV-004', 'Uso de Laboratorio de Computo',         'Ser estudiante o investigador activo',        'Activo',    'Miembro_Activo',                   1),
    ('SRV-005', 'Consulta Medica Preventiva',            'Ser miembro activo con carnet vigente',       'Activo',    'Miembro_Activo,Egresado',          5),
    ('SRV-006', 'Servicio descontinuado de Fotocopiado', 'N/A',                                          'Inactivo',  'Miembro_Activo,Externo',           1);

-- ── Tarifas de Servicio ──────────────────────────────────────────
INSERT INTO Tarifa_Servicio (Perfil_solicitante, Codigo_Servicio, Precio_base, Limite_costo, Fecha_Inicio) VALUES
    ('Miembro_Activo', 'SRV-001', 50.00,  100.00, '2025-01-01'),
    ('Egresado',       'SRV-001', 75.00,  120.00, '2025-01-01'),
    ('Externo',        'SRV-001', 150.00, 300.00, '2025-01-01'),
    ('Estudiante',     'SRV-002', 30.00,  60.00,  '2025-01-01'),
    ('Miembro_Activo', 'SRV-003', 40.00,  80.00,  '2025-01-01'),
    ('Egresado',       'SRV-003', 55.00,  110.00, '2025-01-01'),
    ('Externo',        'SRV-003', 90.00,  180.00, '2025-01-01'),
    ('Miembro_Activo', 'SRV-004', 0.00,   0.00,   '2025-01-01'),
    ('Miembro_Activo', 'SRV-005', 20.00,  40.00,  '2025-01-01'),
    ('Egresado',       'SRV-005', 35.00,  70.00,  '2025-01-01');

-- ── Aplica_En ────────────────────────────────────────────────────
INSERT INTO Aplica_En (nombre_sede, codigo_servicio, perfil_solicitante, precio_base, limite_costo) VALUES
    ('Montalban', 'SRV-001', 'Miembro_Activo', 50.00,  100.00),
    ('Montalban', 'SRV-001', 'Externo',        150.00, 300.00),
    ('Guayana',   'SRV-001', 'Miembro_Activo', 35.00,  80.00),
    ('Guayana',   'SRV-001', 'Externo',        100.00, 200.00),
    ('Montalban', 'SRV-002', 'Estudiante',     30.00,  60.00),
    ('Guayana',   'SRV-002', 'Estudiante',     20.00,  40.00),
    ('Montalban', 'SRV-005', 'Miembro_Activo', 20.00,  40.00),
    ('Guayana',   'SRV-005', 'Miembro_Activo', 15.00,  30.00);

-- ── Clasificado_En ───────────────────────────────────────────────
INSERT INTO Clasificado_En (Perfil_solicitante, Codigo_Servicio, Tipo_Categoria) VALUES
    ('Miembro_Activo', 'SRV-001', 'Frecuente'),
    ('Miembro_Activo', 'SRV-001', 'Preferencial'),
    ('Estudiante',     'SRV-002', 'Estandar'),
    ('Miembro_Activo', 'SRV-005', 'Preferencial');

-- ── Asignado_En ──────────────────────────────────────────────────
INSERT INTO Asignado_En (Codigo_Servicio, Nombre_Edif, Direccion_Interna, Num_identificador) VALUES
    ('SRV-001', 'Edificio Cincuentenario',  'Av. Teheran, Urb. Montalbán', 1),
    ('SRV-004', 'Edificio de Laboratorios', 'Av. Teheran, Urb. Montalbán', 3);

-- ── Oferta Laboral ───────────────────────────────────────────────
-- Cubre los dos estatus de vacante: Disponible y Finalizada
INSERT INTO Oferta_Laboral (id_entidad_externa, cargo_laboral, perfil_buscado, beneficios,
    rango_fecha_graduacion, fecha_oferta, responsabilidades, estatus_vacante, indice_academico_min) VALUES
    (3, 'Desarrollador Backend',  'Ingeniero en Informatica recien graduado', 'Seguro HCM, bono de alimentacion, trabajo remoto', '2022-2024',
     CURRENT_DATE, 'Desarrollo de APIs REST, mantenimiento de bases de datos', 'Disponible', 15.00),
    (3, 'Analista de Datos',      'Egresado de Estadistica o Informatica',    'Seguro HCM, bono trimestral', '2021-2024',
     CURRENT_DATE, 'Modelado de datos, reportes de BI', 'Disponible', 14.00),
    (5, 'Asistente Administrativo','Licenciado en Administracion egresado',   'Seguro HCM, ticket de alimentacion', '2018-2021',
     CURRENT_DATE - INTERVAL '60 days', 'Gestion de expedientes y atencion al publico', 'Finalizada', 13.00);

INSERT INTO Sugerida_A (id_entidad_externa, cargo_laboral, Cedula_Miembro) VALUES
    (3, 'Desarrollador Backend',   'V-23456789'),
    (5, 'Asistente Administrativo','V-89012345');

-- ── Solicitudes de Servicio ──────────────────────────────────────
-- Cubre los 4 estados: Pendiente, En_Proceso, Completada, Cancelada
INSERT INTO Solicitud_Servicio (Identificador, Cedula_Miembro, Codigo_Servicio,
    Estado_actual, Fecha_Inicio, Fecha_Fin, Fecha_creacion) VALUES
    ('SOL-2025-001', 'V-12345678', 'SRV-002', 'En_Proceso', '2025-05-01', NULL,         now() - INTERVAL '10 days'),
    ('SOL-2025-002', 'V-23456789', 'SRV-001', 'Completada', '2025-04-15', '2025-04-15', now() - INTERVAL '30 days'),
    ('SOL-2025-003', 'V-45678901', 'SRV-004', 'Pendiente',  '2025-05-10', NULL,         now() - INTERVAL '3 days'),
    ('SOL-2025-004', 'V-56789012', 'SRV-003', 'En_Proceso', '2025-05-12', NULL,         now() - INTERVAL '2 days'),
    ('SOL-2025-005', 'V-67890123', 'SRV-003', 'Cancelada',  '2025-05-05', '2025-05-06', now() - INTERVAL '15 days'),
    ('SOL-2025-006', 'V-34567890', 'SRV-005', 'Completada', '2025-05-08', '2025-05-08', now() - INTERVAL '12 days');

-- ── Acompañantes Temporales ──────────────────────────────────────
INSERT INTO Acompanante_Temporal (Documento_identidad_Acom, Identificador, Nombre_acompanante, Estado_activo) VALUES
    ('E-11223344', 'SOL-2025-002', 'Roberto Perez Gutierrez', FALSE), -- archivado: solicitud ya completada
    ('V-55667788', 'SOL-2025-002', 'Claudia Ramos Mendez',    FALSE), -- archivado: solicitud ya completada
    ('V-99887766', 'SOL-2025-004', 'Andres Felipe Soto',      TRUE);  -- activo: solicitud en proceso

-- ── Pasos de Actividad ───────────────────────────────────────────
INSERT INTO Paso_Actividad (Identificador, Orden_paso, Estado_paso, Responsable, Observaciones) VALUES
    ('SOL-2025-001', 1, 'Completado', 'Unidad de Caja',       'Pago de aranceles verificado'),
    ('SOL-2025-001', 2, 'En_Proceso', 'Secretaria Academica', 'Validacion de creditos en curso'),
    ('SOL-2025-001', 3, 'Pendiente',  'Rectorado',            'Emision de documento pendiente'),
    ('SOL-2025-002', 1, 'Completado', 'Unidad de Infraestructura', 'Reservacion de auditorio confirmada'),
    ('SOL-2025-005', 1, 'Rechazado',  'Unidad de Caja',       'Solicitud cancelada por el usuario antes de pagar');

-- ── Facturas ─────────────────────────────────────────────────────
-- Cubre los 4 estatus: Pendiente, Parcial, Pagada, Anulada
INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro,
    Estatus_factura, Fecha_emision, Monto_total, Saldo_restante_pagar) VALUES
    ('FCT-2025-001', 'SOL-2025-001', 'V-12345678', 'Pendiente', now() - INTERVAL '9 days',  30.00, 30.00),
    ('FCT-2025-002', 'SOL-2025-002', 'V-23456789', 'Pagada',    now() - INTERVAL '28 days', 75.00, 0.00),
    ('FCT-2025-003', 'SOL-2025-004', 'V-56789012', 'Parcial',   now() - INTERVAL '1 day',   40.00, 20.00),
    ('FCT-2025-004', 'SOL-2025-006', 'V-34567890', 'Pagada',    now() - INTERVAL '11 days', 20.00, 0.00),
    ('FCT-2025-005', 'SOL-2025-005', 'V-67890123', 'Anulada',   now() - INTERVAL '14 days', 40.00, 40.00);

-- ── Folios de Consumo ────────────────────────────────────────────
INSERT INTO Folio_Consumo (Identificador, Fecha_apertura, Estado_cierre) VALUES
    ('SOL-2025-001', '2025-05-01', 'Cerrado'),
    ('SOL-2025-002', '2025-04-15', 'Cerrado'),
    ('SOL-2025-004', '2025-05-12', 'Abierto'),
    ('SOL-2025-006', '2025-05-08', 'Cerrado'),
    ('SOL-2025-005', '2025-05-05', 'Cerrado');

-- ── Items de Consumo ─────────────────────────────────────────────
INSERT INTO Item_consumo (Identificador, Fecha_apertura, Concepto, Precio_unitario, Cantidad, Impuesto, Fecha_cargo) VALUES
    ('SOL-2025-001', '2025-05-01', 'Arancel de Titulo de Grado',   25.00, 1, 16.00, '2025-05-01'),
    ('SOL-2025-001', '2025-05-01', 'Derecho de Secretaria',         5.00, 1,  0.00, '2025-05-01'),
    ('SOL-2025-002', '2025-04-15', 'Alquiler Auditorio 4 horas',   75.00, 1,  0.00, '2025-04-15'),
    ('SOL-2025-004', '2025-05-12', 'Inscripcion Curso Extension',  40.00, 1, 16.00, '2025-05-12'),
    ('SOL-2025-006', '2025-05-08', 'Consulta Medica Preventiva',   20.00, 1,  0.00, '2025-05-08'),
    ('SOL-2025-005', '2025-05-05', 'Inscripcion Curso Extension (cancelado)', 40.00, 1, 0.00, '2025-05-05');

-- ── Pagos (cubre los 6 métodos: Movil, Criptomonedas, Zelle, Efectivo, Tarjeta, TAI) ──
INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) VALUES
    ('FCT-2025-002', now() - INTERVAL '27 days', 'Digital',     75.00, 'Zelle'),
    ('FCT-2025-003', now() - INTERVAL '12 hours','Presencial',  20.00, 'Efectivo'),
    ('FCT-2025-004', now() - INTERVAL '11 days', 'Presencial',  20.00, 'TAI'),
    ('FCT-2025-001', now() - INTERVAL '8 days',  'Presencial',  15.00, 'Tarjeta'),
    ('FCT-2025-001', now() - INTERVAL '7 days',  'Presencial',  15.00, 'Movil'),
    ('FCT-2025-003', now() - INTERVAL '20 hours','Digital',     20.00, 'Criptomonedas');

INSERT INTO Zelle (Numero_control_Factura, fecha_operacion, tipo_pago,
    nombre_titular, correo_origen, codigo_confirmacion) VALUES
    ('FCT-2025-002', now() - INTERVAL '27 days', 'Zelle',
     'Carlos Jose Perez Rivas', 'cperez@gmail.com', 'ZLL-2025-ABC123');

INSERT INTO Efectivo (Numero_control_Factura, fecha_operacion, tipo_pago,
    divisa, tasa_bcv, desglose_denominacion) VALUES
    ('FCT-2025-003', now() - INTERVAL '12 hours', 'Efectivo',
     'USD', 36.50, '{"20": 1}'::JSONB);

INSERT INTO Tarjeta (Numero_control_Factura, fecha_operacion, tipo_pago,
    tipo_red, fecha_vencimiento, compania, num_tarjeta) VALUES
    ('FCT-2025-001', now() - INTERVAL '8 days', 'Tarjeta',
     'Nacional', '2027-08-31', 'Banco Mercantil', '4521********9087');

INSERT INTO Pago_Movil (Numero_control_Factura, fecha_operacion, tipo_pago,
    banco, referencia, telefono_emisor, fecha_movimiento) VALUES
    ('FCT-2025-001', now() - INTERVAL '7 days', 'Movil',
     'Banesco', '000123456789', '04141234567', (CURRENT_DATE - 7));

INSERT INTO Pago_TAI (Numero_control_Factura, fecha_operacion, tipo_pago,
    pos_terminal, recibo_digital, saldo_restante, uid_billetera) VALUES
    ('FCT-2025-004', now() - INTERVAL '11 days', 'TAI',
     'POS-CAJA-03', 'recibos/FCT-2025-004.pdf', 180.00,
     (SELECT UID FROM Billetera_TAI WHERE Cedula_Miembro = 'V-34567890'));

INSERT INTO Criptomonedas (Numero_control_Factura, fecha_operacion, tipo_pago,
    dxid, red_blockchain, billetera, tasa_conversion) VALUES
    ('FCT-2025-003', now() - INTERVAL '20 hours', 'Criptomonedas',
     '0x7f4a9c3e1b2d5e6f8a9b0c1d2e3f4a5b6c7d8e9f', 'TRC20',
     'TQn9Y2khEsLMG6jzWG3qXKQwxhfaJVqBdz', 36.50);

-- ── Establecimiento de claves de prueba (hash bcrypt) ───────────
-- Todas las claves de prueba son 'Ucab2026*' solo para fines de
-- testing. En producción cada usuario define la suya desde el
-- formulario de registro del backend.
SELECT fn_establecer_clave('V-12345678', 'Ucab2026*');
SELECT fn_establecer_clave('V-23456789', 'Ucab2026*');
SELECT fn_establecer_clave('V-34567890', 'Ucab2026*');
SELECT fn_establecer_clave('V-45678901', 'Ucab2026*');
SELECT fn_establecer_clave('V-56789012', 'Ucab2026*');
SELECT fn_establecer_clave('V-67890123', 'Ucab2026*');
SELECT fn_establecer_clave('V-78901234', 'Ucab2026*');
SELECT fn_establecer_clave('V-89012345', 'Ucab2026*');

-- Ejemplo de verificación de login (debería devolver TRUE):
-- SELECT fn_verificar_clave('V-12345678', 'Ucab2026*');