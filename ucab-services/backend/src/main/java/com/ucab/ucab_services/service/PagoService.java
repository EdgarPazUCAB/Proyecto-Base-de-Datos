package com.ucab.ucab_services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private com.ucab.ucab_services.repository.FolioConsumoRepository folioConsumoRepository;

    @Autowired
    private com.ucab.ucab_services.repository.SolicitudServicioRepository solicitudServicioRepository;

    @Transactional
    public void procesarPagoMovil(
        String identificador,
        Double monto,
        String banco,
        String telefono,
        String referencia,
        Double montoTotalVes
    ) {
        try {
            // 1. Buscamos el Numero_control de la Factura y el saldo adeudado
            String sqlFactura =
                "SELECT Numero_control, Saldo_restante_pagar FROM Factura " +
                "WHERE (Identificador = ? OR Numero_control = ?) AND Saldo_restante_pagar > 0 LIMIT 1";

            java.util.List<java.util.Map<String, Object>> facturas =
                jdbcTemplate.queryForList(
                    sqlFactura,
                    identificador,
                    identificador
                );

            String numeroControlFactura;
            double totalAdeudado;

            if (!facturas.isEmpty()) {
                numeroControlFactura = (String) facturas
                    .get(0)
                    .get("numero_control"); // Postgres returns keys in lowercase
                if (numeroControlFactura == null) numeroControlFactura =
                    (String) facturas.get(0).get("Numero_control");

                java.math.BigDecimal saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("saldo_restante_pagar");
                if (saldoBd == null) saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("Saldo_restante_pagar");

                totalAdeudado = saldoBd != null ? saldoBd.doubleValue() : 0.0;

                

                
            } else {
                // Generar la factura a partir del Folio/Solicitud
                String checkFolio =
                    "SELECT COUNT(*) FROM Folio_Consumo WHERE Identificador = ?";
                Integer count = jdbcTemplate.queryForObject(
                    checkFolio,
                    Integer.class,
                    identificador
                );

                if (count != null && count > 0) {
                    // Obtener la cedula
                    String cedula = jdbcTemplate.queryForObject(
                        "SELECT Cedula_Miembro FROM Solicitud_Servicio WHERE Identificador = ?",
                        String.class,
                        identificador
                    );

                    // Calcular el monto total en USD desde la tabla item_consumo
                    Double totalUsd = jdbcTemplate.queryForObject(
                        "SELECT SUM((precio_unitario * cantidad) + impuesto) FROM item_consumo WHERE identificador = ?",
                        Double.class,
                        identificador
                    );
                    totalAdeudado = totalUsd != null ? totalUsd : 0.0;

                    // Generar nuevo Numero_control
                    numeroControlFactura = "FCT-" + System.currentTimeMillis();

                    jdbcTemplate.update(
                        "INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro, Estatus_factura, Monto_total, Saldo_restante_pagar) VALUES (?, ?, ?, \'Pendiente\', ?, ?)",
                        numeroControlFactura,
                        identificador,
                        cedula,
                        totalAdeudado,
                        totalAdeudado
                    );
                } else {
                    throw new RuntimeException(
                        "No existen facturas con deuda pendiente ni folios abiertos para: " +
                            identificador
                    );
                }
            }

            

            // 4. Registramos el Pago base
            java.sql.Timestamp now = new java.sql.Timestamp(
                System.currentTimeMillis()
            );
            String insertPago =
                "INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) " +
                "VALUES (?, ?, 'Digital', ?, 'Movil')";
            jdbcTemplate.update(insertPago, numeroControlFactura, now, monto);

            // 5. Registramos el Pago_Movil
            String insertPagoMovil =
                "INSERT INTO Pago_Movil (Numero_control_Factura, fecha_operacion, tipo_pago, banco, referencia, telefono_emisor, fecha_movimiento) " +
                "VALUES (?, ?, 'Movil', ?, ?, ?, ?)";
            jdbcTemplate.update(
                insertPagoMovil,
                numeroControlFactura,
                now,
                banco,
                referencia,
                telefono,
                new java.sql.Date(System.currentTimeMillis())
            );


            // 6. Verificar si la factura quedó pagada (según el trigger de BD) y cerrar folio
            String estatusFacturaActual = null;
            try {
                estatusFacturaActual = jdbcTemplate.queryForObject(
                    "SELECT Estatus_factura FROM Factura WHERE Numero_control = ?",
                    String.class, numeroControlFactura
                );
            } catch (Exception ex) {
                System.err.println("Error consultando estatus de factura: " + ex.getMessage());
            }
            if ("Pagada".equals(estatusFacturaActual)) {
                try {
                    String selectIdent =
                        "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                    String identFactura = jdbcTemplate.queryForObject(
                        selectIdent,
                        String.class,
                        numeroControlFactura
                    );

                    if (identFactura != null) {
                        java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                            folioConsumoRepository.findByIdentificador(
                                identFactura
                            );
                        for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                            folio.setEstadoCierre("Cerrado");
                            folioConsumoRepository.save(folio);
                        }

                        java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                            solicitudServicioRepository.findById(identFactura);
                        if (optSolicitud.isPresent()) {
                            com.ucab.ucab_services.entity.SolicitudServicio sol =
                                optSolicitud.get();
                            sol.setEstadoActual("Completada");
                            solicitudServicioRepository.save(sol);
                            System.out.println(
                                "Solicitud " +
                                    identFactura +
                                    " actualizada a Completada exitosamente."
                            );
                        } else {
                            System.err.println(
                                "ADVERTENCIA: No se encontró la solicitud " +
                                    identFactura +
                                    " en JPA."
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println(
                        "Nota: Error al cerrar folio_consumo o solicitud: " +
                            e.getMessage()
                    );
                    e.printStackTrace();
                }
            }

            System.out.println(
                "Pago móvil procesado con éxito para factura: " +
                    numeroControlFactura
            );
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(
                "Error crítico en PagoService: " + e.getMessage()
            );
            throw new RuntimeException(
                "Fallo en el servicio de Base de Datos al procesar el pago.",
                e
            );
        }
    }

    @Transactional
    public void procesarPagoTarjeta(
        String identificador,
        Double monto,
        String tipoRed,
        java.time.LocalDate fechaVencimiento,
        String compania,
        String numTarjeta,
        Double montoTotalVes
    ) {
        try {
            // 1. Buscamos el Numero_control de la Factura y el saldo adeudado
            String sqlFactura =
                "SELECT Numero_control, Saldo_restante_pagar FROM Factura " +
                "WHERE (Identificador = ? OR Numero_control = ?) AND Saldo_restante_pagar > 0 LIMIT 1";

            java.util.List<java.util.Map<String, Object>> facturas =
                jdbcTemplate.queryForList(
                    sqlFactura,
                    identificador,
                    identificador
                );

            String numeroControlFactura;
            double totalAdeudado;

            if (!facturas.isEmpty()) {
                numeroControlFactura = (String) facturas
                    .get(0)
                    .get("numero_control"); // Postgres returns keys in lowercase
                if (numeroControlFactura == null) numeroControlFactura =
                    (String) facturas.get(0).get("Numero_control");

                java.math.BigDecimal saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("saldo_restante_pagar");
                if (saldoBd == null) saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("Saldo_restante_pagar");

                totalAdeudado = saldoBd != null ? saldoBd.doubleValue() : 0.0;

                

                
            } else {
                // Generar la factura a partir del Folio/Solicitud
                String checkFolio =
                    "SELECT COUNT(*) FROM Folio_Consumo WHERE Identificador = ?";
                Integer count = jdbcTemplate.queryForObject(
                    checkFolio,
                    Integer.class,
                    identificador
                );

                if (count != null && count > 0) {
                    // Obtener la cedula
                    String cedula = jdbcTemplate.queryForObject(
                        "SELECT Cedula_Miembro FROM Solicitud_Servicio WHERE Identificador = ?",
                        String.class,
                        identificador
                    );

                    // Calcular el monto total en USD desde la tabla item_consumo
                    Double totalUsd = jdbcTemplate.queryForObject(
                        "SELECT SUM((precio_unitario * cantidad) + impuesto) FROM item_consumo WHERE identificador = ?",
                        Double.class,
                        identificador
                    );
                    totalAdeudado = totalUsd != null ? totalUsd : 0.0;

                    // Generar nuevo Numero_control
                    numeroControlFactura = "FCT-" + System.currentTimeMillis();

                    jdbcTemplate.update(
                        "INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro, Estatus_factura, Monto_total, Saldo_restante_pagar) VALUES (?, ?, ?, \'Pendiente\', ?, ?)",
                        numeroControlFactura,
                        identificador,
                        cedula,
                        totalAdeudado,
                        totalAdeudado
                    );
                } else {
                    throw new RuntimeException(
                        "No existen facturas con deuda pendiente ni folios abiertos para: " +
                            identificador
                    );
                }
            }

            

            // 4. Registramos el Pago base
            java.sql.Timestamp now = new java.sql.Timestamp(
                System.currentTimeMillis()
            );
            String insertPago =
                "INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) " +
                "VALUES (?, ?, 'Digital', ?, 'Tarjeta')";
            jdbcTemplate.update(insertPago, numeroControlFactura, now, monto);

            // 5. Registramos el Tarjeta
            String insertTarjeta =
                "INSERT INTO Tarjeta (Numero_control_Factura, fecha_operacion, tipo_pago, tipo_red, fecha_vencimiento, compania, num_tarjeta) " +
                "VALUES (?, ?, 'Tarjeta', ?, ?, ?, ?)";
            jdbcTemplate.update(
                insertTarjeta,
                numeroControlFactura,
                now,
                tipoRed,
                java.sql.Date.valueOf(fechaVencimiento),
                compania,
                numTarjeta
            );


            // 6. Verificar si la factura quedó pagada (según el trigger de BD) y cerrar folio
            String estatusFacturaActual = null;
            try {
                estatusFacturaActual = jdbcTemplate.queryForObject(
                    "SELECT Estatus_factura FROM Factura WHERE Numero_control = ?",
                    String.class, numeroControlFactura
                );
            } catch (Exception ex) {
                System.err.println("Error consultando estatus de factura: " + ex.getMessage());
            }
            if ("Pagada".equals(estatusFacturaActual)) {
                try {
                    String selectIdent =
                        "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                    String identFactura = jdbcTemplate.queryForObject(
                        selectIdent,
                        String.class,
                        numeroControlFactura
                    );

                    if (identFactura != null) {
                        java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                            folioConsumoRepository.findByIdentificador(
                                identFactura
                            );
                        for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                            folio.setEstadoCierre("Cerrado");
                            folioConsumoRepository.save(folio);
                        }

                        java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                            solicitudServicioRepository.findById(identFactura);
                        if (optSolicitud.isPresent()) {
                            com.ucab.ucab_services.entity.SolicitudServicio sol =
                                optSolicitud.get();
                            sol.setEstadoActual("Completada");
                            solicitudServicioRepository.save(sol);
                            System.out.println(
                                "Solicitud " +
                                    identFactura +
                                    " actualizada a Completada exitosamente."
                            );
                        } else {
                            System.err.println(
                                "ADVERTENCIA: No se encontró la solicitud " +
                                    identFactura +
                                    " en JPA."
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println(
                        "Nota: Error al cerrar folio_consumo o solicitud: " +
                            e.getMessage()
                    );
                    e.printStackTrace();
                }
            }

            System.out.println(
                "Pago con tarjeta procesado con éxito para factura: " +
                    numeroControlFactura
            );
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(
                "Error crítico en PagoService: " + e.getMessage()
            );
            throw new RuntimeException(
                "Fallo en el servicio de Base de Datos al procesar el pago con tarjeta.",
                e
            );
        }
    }

    @Transactional
    public void procesarPagoCriptomonedas(
        String identificador,
        Double monto,
        String dxid,
        String redBlockchain,
        String billetera,
        Double tasaConversion,
        Double montoTotalVes
    ) {
        try {
            // 1. Buscamos el Numero_control de la Factura y el saldo adeudado
            String sqlFactura =
                "SELECT Numero_control, Saldo_restante_pagar FROM Factura " +
                "WHERE (Identificador = ? OR Numero_control = ?) AND Saldo_restante_pagar > 0 LIMIT 1";

            java.util.List<java.util.Map<String, Object>> facturas =
                jdbcTemplate.queryForList(
                    sqlFactura,
                    identificador,
                    identificador
                );

            String numeroControlFactura;
            double totalAdeudado;

            if (!facturas.isEmpty()) {
                numeroControlFactura = (String) facturas
                    .get(0)
                    .get("numero_control");
                if (numeroControlFactura == null) numeroControlFactura =
                    (String) facturas.get(0).get("Numero_control");

                java.math.BigDecimal saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("saldo_restante_pagar");
                if (saldoBd == null) saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("Saldo_restante_pagar");

                totalAdeudado = saldoBd != null ? saldoBd.doubleValue() : 0.0;

                

                
            } else {
                // Generar la factura a partir del Folio/Solicitud
                String checkFolio =
                    "SELECT COUNT(*) FROM Folio_Consumo WHERE Identificador = ?";
                Integer count = jdbcTemplate.queryForObject(
                    checkFolio,
                    Integer.class,
                    identificador
                );

                if (count != null && count > 0) {
                    String cedula = jdbcTemplate.queryForObject(
                        "SELECT Cedula_Miembro FROM Solicitud_Servicio WHERE Identificador = ?",
                        String.class,
                        identificador
                    );

                    // Calcular el monto total en USD desde la tabla item_consumo
                    Double totalUsd = jdbcTemplate.queryForObject(
                        "SELECT SUM((precio_unitario * cantidad) + impuesto) FROM item_consumo WHERE identificador = ?",
                        Double.class,
                        identificador
                    );
                    totalAdeudado = totalUsd != null ? totalUsd : 0.0;

                    numeroControlFactura = "FCT-" + System.currentTimeMillis();

                    jdbcTemplate.update(
                        "INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro, Estatus_factura, Monto_total, Saldo_restante_pagar) VALUES (?, ?, ?, \'Pendiente\', ?, ?)",
                        numeroControlFactura,
                        identificador,
                        cedula,
                        totalAdeudado,
                        totalAdeudado
                    );
                } else {
                    throw new RuntimeException(
                        "No existen facturas con deuda pendiente ni folios abiertos para: " +
                            identificador
                    );
                }
            }

            // 3. Actualizamos Folio_Consumo y Solicitud_Servicio
            try {
                String selectIdent =
                    "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                String identFactura = jdbcTemplate.queryForObject(
                    selectIdent,
                    String.class,
                    numeroControlFactura
                );

                if (identFactura != null) {
                    java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                        folioConsumoRepository.findByIdentificador(
                            identFactura
                        );
                    for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                        folio.setEstadoCierre("Cerrado");
                        folioConsumoRepository.save(folio);
                    }

                    java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                        solicitudServicioRepository.findById(identFactura);
                    if (optSolicitud.isPresent()) {
                        com.ucab.ucab_services.entity.SolicitudServicio sol =
                            optSolicitud.get();
                        sol.setEstadoActual("Completada");
                        solicitudServicioRepository.save(sol);
                    }
                }
            } catch (Exception e) {
                System.out.println(
                    "Nota: Error al cerrar folio_consumo o solicitud: " +
                        e.getMessage()
                );
            }

            // 4. Registramos el Pago base
            java.sql.Timestamp now = new java.sql.Timestamp(
                System.currentTimeMillis()
            );
            String insertPago =
                "INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) " +
                "VALUES (?, ?, 'Digital', ?, 'Criptomonedas')";
            jdbcTemplate.update(insertPago, numeroControlFactura, now, monto);

            // 5. Registramos en Criptomonedas
            String insertCripto =
                "INSERT INTO Criptomonedas (Numero_control_Factura, fecha_operacion, tipo_pago, dxid, red_blockchain, billetera, tasa_conversion) " +
                "VALUES (?, ?, 'Criptomonedas', ?, ?, ?, ?)";
            jdbcTemplate.update(
                insertCripto,
                numeroControlFactura,
                now,
                dxid,
                redBlockchain,
                billetera,
                tasaConversion
            );


            // 6. Verificar si la factura quedó pagada (según el trigger de BD) y cerrar folio
            String estatusFacturaActual = null;
            try {
                estatusFacturaActual = jdbcTemplate.queryForObject(
                    "SELECT Estatus_factura FROM Factura WHERE Numero_control = ?",
                    String.class, numeroControlFactura
                );
            } catch (Exception ex) {
                System.err.println("Error consultando estatus de factura: " + ex.getMessage());
            }
            if ("Pagada".equals(estatusFacturaActual)) {
                try {
                    String selectIdent =
                        "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                    String identFactura = jdbcTemplate.queryForObject(
                        selectIdent,
                        String.class,
                        numeroControlFactura
                    );

                    if (identFactura != null) {
                        java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                            folioConsumoRepository.findByIdentificador(
                                identFactura
                            );
                        for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                            folio.setEstadoCierre("Cerrado");
                            folioConsumoRepository.save(folio);
                        }

                        java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                            solicitudServicioRepository.findById(identFactura);
                        if (optSolicitud.isPresent()) {
                            com.ucab.ucab_services.entity.SolicitudServicio sol =
                                optSolicitud.get();
                            sol.setEstadoActual("Completada");
                            solicitudServicioRepository.save(sol);
                            System.out.println(
                                "Solicitud " +
                                    identFactura +
                                    " actualizada a Completada exitosamente."
                            );
                        } else {
                            System.err.println(
                                "ADVERTENCIA: No se encontró la solicitud " +
                                    identFactura +
                                    " en JPA."
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println(
                        "Nota: Error al cerrar folio_consumo o solicitud: " +
                            e.getMessage()
                    );
                    e.printStackTrace();
                }
            }

            System.out.println(
                "Pago con criptomonedas procesado con éxito para factura: " +
                    numeroControlFactura
            );
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(
                "Error crítico en PagoService: " + e.getMessage()
            );
            throw new RuntimeException(
                "Fallo en el servicio de Base de Datos al procesar el pago con criptomonedas.",
                e
            );
        }
    }

    @Transactional
    public void procesarPagoZelle(
        String identificador,
        Double monto,
        String nombreTitular,
        String correoOrigen,
        String codigoConfirmacion,
        Double montoTotalVes
    ) {
        try {
            // 1. Buscamos el Numero_control de la Factura y el saldo adeudado
            String sqlFactura =
                "SELECT Numero_control, Saldo_restante_pagar FROM Factura " +
                "WHERE (Identificador = ? OR Numero_control = ?) AND Saldo_restante_pagar > 0 LIMIT 1";

            java.util.List<java.util.Map<String, Object>> facturas =
                jdbcTemplate.queryForList(
                    sqlFactura,
                    identificador,
                    identificador
                );

            String numeroControlFactura;
            double totalAdeudado;

            if (!facturas.isEmpty()) {
                numeroControlFactura = (String) facturas
                    .get(0)
                    .get("numero_control");
                if (numeroControlFactura == null) numeroControlFactura =
                    (String) facturas.get(0).get("Numero_control");

                java.math.BigDecimal saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("saldo_restante_pagar");
                if (saldoBd == null) saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("Saldo_restante_pagar");

                totalAdeudado = saldoBd != null ? saldoBd.doubleValue() : 0.0;

                

                
            } else {
                // Generar la factura a partir del Folio/Solicitud
                String checkFolio =
                    "SELECT COUNT(*) FROM Folio_Consumo WHERE Identificador = ?";
                Integer count = jdbcTemplate.queryForObject(
                    checkFolio,
                    Integer.class,
                    identificador
                );

                if (count != null && count > 0) {
                    String cedula = jdbcTemplate.queryForObject(
                        "SELECT Cedula_Miembro FROM Solicitud_Servicio WHERE Identificador = ?",
                        String.class,
                        identificador
                    );

                    // Calcular el monto total en USD desde la tabla item_consumo
                    Double totalUsd = jdbcTemplate.queryForObject(
                        "SELECT SUM((precio_unitario * cantidad) + impuesto) FROM item_consumo WHERE identificador = ?",
                        Double.class,
                        identificador
                    );
                    totalAdeudado = totalUsd != null ? totalUsd : 0.0;

                    numeroControlFactura = "FCT-" + System.currentTimeMillis();

                    jdbcTemplate.update(
                        "INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro, Estatus_factura, Monto_total, Saldo_restante_pagar) VALUES (?, ?, ?, \'Pendiente\', ?, ?)",
                        numeroControlFactura,
                        identificador,
                        cedula,
                        totalAdeudado,
                        totalAdeudado
                    );
                } else {
                    throw new RuntimeException(
                        "No existen facturas con deuda pendiente ni folios abiertos para: " +
                            identificador
                    );
                }
            }

            // 3. Actualizamos Folio_Consumo y Solicitud_Servicio
            try {
                String selectIdent =
                    "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                String identFactura = jdbcTemplate.queryForObject(
                    selectIdent,
                    String.class,
                    numeroControlFactura
                );

                if (identFactura != null) {
                    java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                        folioConsumoRepository.findByIdentificador(
                            identFactura
                        );
                    for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                        folio.setEstadoCierre("Cerrado");
                        folioConsumoRepository.save(folio);
                    }

                    java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                        solicitudServicioRepository.findById(identFactura);
                    if (optSolicitud.isPresent()) {
                        com.ucab.ucab_services.entity.SolicitudServicio sol =
                            optSolicitud.get();
                        sol.setEstadoActual("Completada");
                        solicitudServicioRepository.save(sol);
                    }
                }
            } catch (Exception e) {
                System.out.println(
                    "Nota: Error al cerrar folio_consumo o solicitud: " +
                        e.getMessage()
                );
            }

            // 4. Registramos el Pago base
            java.sql.Timestamp now = new java.sql.Timestamp(
                System.currentTimeMillis()
            );
            String insertPago =
                "INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) " +
                "VALUES (?, ?, 'Digital', ?, 'Zelle')";
            jdbcTemplate.update(insertPago, numeroControlFactura, now, monto);

            // 5. Registramos en Zelle
            String insertZelle =
                "INSERT INTO Zelle (Numero_control_Factura, fecha_operacion, tipo_pago, nombre_titular, correo_origen, codigo_confirmacion) " +
                "VALUES (?, ?, 'Zelle', ?, ?, ?)";
            jdbcTemplate.update(
                insertZelle,
                numeroControlFactura,
                now,
                nombreTitular,
                correoOrigen,
                codigoConfirmacion
            );


            // 6. Verificar si la factura quedó pagada (según el trigger de BD) y cerrar folio
            String estatusFacturaActual = null;
            try {
                estatusFacturaActual = jdbcTemplate.queryForObject(
                    "SELECT Estatus_factura FROM Factura WHERE Numero_control = ?",
                    String.class, numeroControlFactura
                );
            } catch (Exception ex) {
                System.err.println("Error consultando estatus de factura: " + ex.getMessage());
            }
            if ("Pagada".equals(estatusFacturaActual)) {
                try {
                    String selectIdent =
                        "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                    String identFactura = jdbcTemplate.queryForObject(
                        selectIdent,
                        String.class,
                        numeroControlFactura
                    );

                    if (identFactura != null) {
                        java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                            folioConsumoRepository.findByIdentificador(
                                identFactura
                            );
                        for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                            folio.setEstadoCierre("Cerrado");
                            folioConsumoRepository.save(folio);
                        }

                        java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                            solicitudServicioRepository.findById(identFactura);
                        if (optSolicitud.isPresent()) {
                            com.ucab.ucab_services.entity.SolicitudServicio sol =
                                optSolicitud.get();
                            sol.setEstadoActual("Completada");
                            solicitudServicioRepository.save(sol);
                            System.out.println(
                                "Solicitud " +
                                    identFactura +
                                    " actualizada a Completada exitosamente."
                            );
                        } else {
                            System.err.println(
                                "ADVERTENCIA: No se encontró la solicitud " +
                                    identFactura +
                                    " en JPA."
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println(
                        "Nota: Error al cerrar folio_consumo o solicitud: " +
                            e.getMessage()
                    );
                    e.printStackTrace();
                }
            }

            System.out.println(
                "Pago con Zelle procesado con éxito para factura: " +
                    numeroControlFactura
            );
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(
                "Error crítico en PagoService: " + e.getMessage()
            );
            throw new RuntimeException(
                "Fallo en el servicio de Base de Datos al procesar el pago con Zelle.",
                e
            );
        }
    }

    @Transactional
    public void procesarPagoTai(
        String identificador,
        Double monto,
        String posTerminal,
        String reciboDigital,
        Double montoTotalVes
    ) {
        try {
            // 1. Buscamos el Numero_control de la Factura y el saldo adeudado
            String sqlFactura =
                "SELECT Numero_control, Saldo_restante_pagar, Cedula_Miembro FROM Factura " +
                "WHERE (Identificador = ? OR Numero_control = ?) AND Saldo_restante_pagar > 0 LIMIT 1";

            java.util.List<java.util.Map<String, Object>> facturas =
                jdbcTemplate.queryForList(
                    sqlFactura,
                    identificador,
                    identificador
                );

            String numeroControlFactura;
            double totalAdeudado;
            String cedula = null;

            if (!facturas.isEmpty()) {
                numeroControlFactura = (String) facturas
                    .get(0)
                    .get("numero_control");
                if (numeroControlFactura == null) numeroControlFactura =
                    (String) facturas.get(0).get("Numero_control");

                java.math.BigDecimal saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("saldo_restante_pagar");
                if (saldoBd == null) saldoBd = (java.math.BigDecimal) facturas
                    .get(0)
                    .get("Saldo_restante_pagar");

                totalAdeudado = saldoBd != null ? saldoBd.doubleValue() : 0.0;
                
                cedula = (String) facturas.get(0).get("cedula_miembro");
                if (cedula == null) cedula = (String) facturas.get(0).get("Cedula_Miembro");

                

                
            } else {
                // Generar la factura a partir del Folio/Solicitud
                String checkFolio =
                    "SELECT COUNT(*) FROM Folio_Consumo WHERE Identificador = ?";
                Integer count = jdbcTemplate.queryForObject(
                    checkFolio,
                    Integer.class,
                    identificador
                );

                if (count != null && count > 0) {
                    cedula = jdbcTemplate.queryForObject(
                        "SELECT Cedula_Miembro FROM Solicitud_Servicio WHERE Identificador = ?",
                        String.class,
                        identificador
                    );

                    // Calcular el monto total en USD desde la tabla item_consumo
                    Double totalUsd = jdbcTemplate.queryForObject(
                        "SELECT SUM((precio_unitario * cantidad) + impuesto) FROM item_consumo WHERE identificador = ?",
                        Double.class,
                        identificador
                    );
                    totalAdeudado = totalUsd != null ? totalUsd : 0.0;

                    if (monto < totalAdeudado - 1.0) {
                        throw new RuntimeException(
                            "El monto enviado (Bs. " +
                                monto +
                                ") es menor a la deuda actual (Bs. " +
                                totalAdeudado +
                                ")."
                        );
                    }

                    numeroControlFactura = "FCT-" + System.currentTimeMillis();

                    jdbcTemplate.update(
                        "INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro, Estatus_factura, Monto_total, Saldo_restante_pagar) VALUES (?, ?, ?, \'Pendiente\', ?, ?)",
                        numeroControlFactura,
                        identificador,
                        cedula,
                        totalAdeudado,
                        totalAdeudado
                    );
                } else {
                    throw new RuntimeException(
                        "No existen facturas con deuda pendiente ni folios abiertos para: " +
                            identificador
                    );
                }
            }

            // Validar Billetera TAI
            String sqlBilletera = "SELECT UID, Saldo_Restante FROM Billetera_TAI WHERE Cedula_Miembro = ?";
            java.util.List<java.util.Map<String, Object>> billeteras = jdbcTemplate.queryForList(sqlBilletera, cedula);
            if (billeteras.isEmpty()) {
                throw new RuntimeException("El miembro no tiene una Billetera TAI activa.");
            }
            
            Object uidObj = billeteras.get(0).get("uid");
            if(uidObj == null) uidObj = billeteras.get(0).get("UID");
            java.util.UUID uidBilletera = java.util.UUID.fromString(uidObj.toString());

            java.math.BigDecimal saldoBilleteraBd = (java.math.BigDecimal) billeteras.get(0).get("saldo_restante");
            if (saldoBilleteraBd == null) saldoBilleteraBd = (java.math.BigDecimal) billeteras.get(0).get("Saldo_Restante");
            double saldoBilletera = saldoBilleteraBd != null ? saldoBilleteraBd.doubleValue() : 0.0;

            if (saldoBilletera < monto) {
                throw new RuntimeException("Saldo insuficiente en la Billetera TAI. Saldo actual: " + saldoBilletera);
            }

            // Descontar saldo de Billetera TAI (ambas columnas para mantener consistencia)
            double nuevoSaldo = saldoBilletera - monto;
            String updateBilletera = "UPDATE Billetera_TAI SET Saldo_Restante = ?, Saldo_Virtual = ? WHERE UID = ?";
            jdbcTemplate.update(updateBilletera, nuevoSaldo, nuevoSaldo, uidBilletera);


            // 3. Actualizamos Folio_Consumo y Solicitud_Servicio
            try {
                String selectIdent =
                    "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                String identFactura = jdbcTemplate.queryForObject(
                    selectIdent,
                    String.class,
                    numeroControlFactura
                );

                if (identFactura != null) {
                    java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                        folioConsumoRepository.findByIdentificador(
                            identFactura
                        );
                    for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                        folio.setEstadoCierre("Cerrado");
                        folioConsumoRepository.save(folio);
                    }

                    java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                        solicitudServicioRepository.findById(identFactura);
                    if (optSolicitud.isPresent()) {
                        com.ucab.ucab_services.entity.SolicitudServicio sol =
                            optSolicitud.get();
                        sol.setEstadoActual("Completada");
                        solicitudServicioRepository.save(sol);
                    }
                }
            } catch (Exception e) {
                System.out.println(
                    "Nota: Error al cerrar folio_consumo o solicitud: " +
                        e.getMessage()
                );
            }

            // 4. Registramos el Pago base
            java.sql.Timestamp now = new java.sql.Timestamp(
                System.currentTimeMillis()
            );
            String insertPago =
                "INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) " +
                "VALUES (?, ?, 'Digital', ?, 'TAI')";
            jdbcTemplate.update(insertPago, numeroControlFactura, now, monto);

            // 5. Registramos en Pago_TAI
            String insertTai =
                "INSERT INTO Pago_TAI (Numero_control_Factura, fecha_operacion, tipo_pago, pos_terminal, recibo_digital, saldo_restante, uid_billetera) " +
                "VALUES (?, ?, 'TAI', ?, ?, ?, ?)";
            jdbcTemplate.update(
                insertTai,
                numeroControlFactura,
                now,
                posTerminal,
                reciboDigital,
                nuevoSaldo,
                uidBilletera
            );


            // 6. Verificar si la factura quedó pagada (según el trigger de BD) y cerrar folio
            String estatusFacturaActual = null;
            try {
                estatusFacturaActual = jdbcTemplate.queryForObject(
                    "SELECT Estatus_factura FROM Factura WHERE Numero_control = ?",
                    String.class, numeroControlFactura
                );
            } catch (Exception ex) {
                System.err.println("Error consultando estatus de factura: " + ex.getMessage());
            }
            if ("Pagada".equals(estatusFacturaActual)) {
                try {
                    String selectIdent =
                        "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                    String identFactura = jdbcTemplate.queryForObject(
                        selectIdent,
                        String.class,
                        numeroControlFactura
                    );

                    if (identFactura != null) {
                        java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios =
                            folioConsumoRepository.findByIdentificador(
                                identFactura
                            );
                        for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                            folio.setEstadoCierre("Cerrado");
                            folioConsumoRepository.save(folio);
                        }

                        java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud =
                            solicitudServicioRepository.findById(identFactura);
                        if (optSolicitud.isPresent()) {
                            com.ucab.ucab_services.entity.SolicitudServicio sol =
                                optSolicitud.get();
                            sol.setEstadoActual("Completada");
                            solicitudServicioRepository.save(sol);
                            System.out.println(
                                "Solicitud " +
                                    identFactura +
                                    " actualizada a Completada exitosamente."
                            );
                        } else {
                            System.err.println(
                                "ADVERTENCIA: No se encontró la solicitud " +
                                    identFactura +
                                    " en JPA."
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println(
                        "Nota: Error al cerrar folio_consumo o solicitud: " +
                            e.getMessage()
                    );
                    e.printStackTrace();
                }
            }

            System.out.println(
                "Pago con TAI procesado con éxito para factura: " +
                    numeroControlFactura
            );
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(
                "Error crítico en PagoService: " + e.getMessage()
            );
            throw new RuntimeException(
                "Fallo en el servicio de Base de Datos al procesar el pago con TAI.",
                e
            );
        }
    }

    public java.util.List<java.util.Map<String, Object>> obtenerHistorialPagos(String cedula) {
        try {
            String sql = 
                "SELECT p.Numero_control_Factura as \"numeroControlFactura\", " +
                "       p.fecha_operacion as \"fechaOperacion\", " +
                "       p.tipo_pago as \"tipoPago\", " +
                "       p.monto_liquidacion as \"montoLiquidacion\", " +
                "       f.Estatus_factura as \"estatusFactura\", " +
                "       f.Monto_total as \"montoTotal\", " +
                "       f.Identificador as \"identificadorFolio\" " +
                "FROM Pago p " +
                "JOIN Factura f ON p.Numero_control_Factura = f.Numero_control " +
                "WHERE f.Cedula_Miembro = ? " +
                "ORDER BY p.fecha_operacion DESC";
                
            return jdbcTemplate.queryForList(sql, cedula);
        } catch (Exception e) {
            System.err.println("Error obteniendo historial de pagos: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    public java.util.Map<String, Object> obtenerDetalleFactura(String numeroControl) {
        java.util.Map<String, Object> resultado = new java.util.HashMap<>();
        try {
            String sqlFactura = "SELECT Numero_control as \"numeroControl\", " +
                                "Identificador as \"identificador\", " +
                                "Estatus_factura as \"estatusFactura\", " +
                                "Monto_total as \"montoTotal\", " +
                                "Saldo_restante_pagar as \"saldoRestante\" " +
                                "FROM Factura WHERE Numero_control = ?";
            
            java.util.List<java.util.Map<String, Object>> facturas = jdbcTemplate.queryForList(sqlFactura, numeroControl);
            
            if (facturas.isEmpty()) {
                resultado.put("error", "Factura no encontrada");
                return resultado;
            }
            
            resultado.put("factura", facturas.get(0));
            
            String sqlPagos = "SELECT fecha_operacion as \"fechaOperacion\", " +
                              "tipo_pago as \"tipoPago\", " +
                              "monto_liquidacion as \"montoLiquidacion\", " +
                              "canal_origen as \"canalOrigen\" " +
                              "FROM Pago WHERE Numero_control_Factura = ? " +
                              "ORDER BY fecha_operacion DESC";
            
            java.util.List<java.util.Map<String, Object>> pagos = jdbcTemplate.queryForList(sqlPagos, numeroControl);
            resultado.put("pagos", pagos);
            
        } catch (Exception e) {
            System.err.println("Error obteniendo detalle de factura: " + e.getMessage());
            resultado.put("error", e.getMessage());
        }
        return resultado;
    }
}

