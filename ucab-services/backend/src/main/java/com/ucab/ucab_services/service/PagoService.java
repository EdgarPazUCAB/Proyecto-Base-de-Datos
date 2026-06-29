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
    public void procesarPagoMovil(String identificador, double monto, String banco, String telefono, String referencia) {
        try {
            // 1. Buscamos el Numero_control de la Factura y el saldo adeudado
            String sqlFactura = "SELECT Numero_control, Saldo_restante_pagar FROM Factura " +
                    "WHERE (Identificador = ? OR Numero_control = ?) AND Saldo_restante_pagar > 0 LIMIT 1";

            java.util.List<java.util.Map<String, Object>> facturas = jdbcTemplate.queryForList(sqlFactura, identificador, identificador);

            String numeroControlFactura;
            double totalAdeudado;

            if (!facturas.isEmpty()) {
                numeroControlFactura = (String) facturas.get(0).get("numero_control"); // Postgres returns keys in lowercase
                if (numeroControlFactura == null) numeroControlFactura = (String) facturas.get(0).get("Numero_control");

                java.math.BigDecimal saldoBd = (java.math.BigDecimal) facturas.get(0).get("saldo_restante_pagar");
                if (saldoBd == null) saldoBd = (java.math.BigDecimal) facturas.get(0).get("Saldo_restante_pagar");
                
                totalAdeudado = saldoBd != null ? saldoBd.doubleValue() : 0.0;
                
                // Margen de tolerancia de 1 Bolívar
                if (monto < (totalAdeudado - 1.0)) {
                    throw new RuntimeException("El monto enviado (Bs. " + monto + ") es menor a la deuda actual (Bs. " + totalAdeudado + ").");
                }

                // 2. Actualizamos la Factura
                String updateFactura = "UPDATE Factura SET Estatus_factura = 'Pagada', Saldo_restante_pagar = 0 " +
                        "WHERE Numero_control = ?";
                jdbcTemplate.update(updateFactura, numeroControlFactura);
            } else {
                // Generar la factura a partir del Folio/Solicitud
                String checkFolio = "SELECT COUNT(*) FROM Folio_Consumo WHERE Identificador = ?";
                Integer count = jdbcTemplate.queryForObject(checkFolio, Integer.class, identificador);
                
                if (count != null && count > 0) {
                    // Obtener la cedula
                    String cedula = jdbcTemplate.queryForObject(
                            "SELECT Cedula_Miembro FROM Solicitud_Servicio WHERE Identificador = ?",
                            String.class, identificador
                    );
                    
                    // Calcular deuda de Items
                    java.math.BigDecimal totalItemsBd = jdbcTemplate.queryForObject(
                            "SELECT COALESCE(SUM(Precio_unitario * Cantidad + Impuesto), 0) FROM Item_consumo WHERE Identificador = ?",
                            java.math.BigDecimal.class, identificador
                    );
                    totalAdeudado = totalItemsBd != null ? totalItemsBd.doubleValue() : 0.0;
                    
                    // Generar nuevo Numero_control
                    numeroControlFactura = "FCT-" + System.currentTimeMillis();
                    
                    jdbcTemplate.update(
                            "INSERT INTO Factura (Numero_control, Identificador, Cedula_Miembro, Estatus_factura, Monto_total, Saldo_restante_pagar) VALUES (?, ?, ?, 'Pagada', ?, 0)",
                            numeroControlFactura, identificador, cedula, totalAdeudado
                    );
                } else {
                    throw new RuntimeException("No existen facturas con deuda pendiente ni folios abiertos para: " + identificador);
                }
            }

            // 3. Actualizamos Folio_Consumo y Solicitud_Servicio
            try {
                String selectIdent = "SELECT Identificador FROM Factura WHERE Numero_control = ?";
                String identFactura = jdbcTemplate.queryForObject(selectIdent, String.class, numeroControlFactura);
                
                // Usamos los Repositories para asegurar que la caché de Hibernate se actualice
                if (identFactura != null) {
                    java.util.List<com.ucab.ucab_services.entity.FolioConsumo> folios = folioConsumoRepository.findByIdentificador(identFactura);
                    for (com.ucab.ucab_services.entity.FolioConsumo folio : folios) {
                        folio.setEstadoCierre("Cerrado");
                        folioConsumoRepository.save(folio);
                    }
                    
                    java.util.Optional<com.ucab.ucab_services.entity.SolicitudServicio> optSolicitud = solicitudServicioRepository.findById(identFactura);
                    if (optSolicitud.isPresent()) {
                        com.ucab.ucab_services.entity.SolicitudServicio sol = optSolicitud.get();
                        sol.setEstadoActual("Completada");
                        solicitudServicioRepository.save(sol);
                        System.out.println("Solicitud " + identFactura + " actualizada a Completada exitosamente.");
                    } else {
                        System.err.println("ADVERTENCIA: No se encontró la solicitud " + identFactura + " en JPA.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Nota: Error al cerrar folio_consumo o solicitud: " + e.getMessage());
                e.printStackTrace();
            }
            
            // 4. Registramos el Pago base
            java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
            String insertPago = "INSERT INTO Pago (Numero_control_Factura, fecha_operacion, canal_origen, monto_liquidacion, tipo_pago) " +
                                "VALUES (?, ?, 'Digital', ?, 'Movil')";
            jdbcTemplate.update(insertPago, numeroControlFactura, now, monto);

            // 5. Registramos el Pago_Movil
            String insertPagoMovil = "INSERT INTO Pago_Movil (Numero_control_Factura, fecha_operacion, tipo_pago, banco, referencia, telefono_emisor, fecha_movimiento) " +
                                     "VALUES (?, ?, 'Movil', ?, ?, ?, ?)";
            jdbcTemplate.update(insertPagoMovil, numeroControlFactura, now, banco, referencia, telefono, new java.sql.Date(System.currentTimeMillis()));

            System.out.println("Pago móvil procesado con éxito para factura: " + numeroControlFactura);

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error crítico en PagoService: " + e.getMessage());
            throw new RuntimeException("Fallo en el servicio de Base de Datos al procesar el pago.", e);
        }
    }
}