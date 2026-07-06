package com.ucab.ucab_services.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Controlador para generación y descarga de reportes JasperReports.
 *
 * Flujo de uso con Jaspersoft Studio:
 * 1. Diseña el reporte .jrxml en Jaspersoft Studio.
 * 2. Compila el .jrxml a .jasper (clic derecho → Compile Report) o déjalo como .jrxml.
 * 3. Coloca el archivo .jasper (o .jrxml) en src/main/resources/reports/
 * 4. Llama al endpoint correspondiente para descargar el PDF.
 *
 * Endpoints disponibles:
 *   GET /api/reportes/{nombreReporte}/pdf    → Descarga el reporte en PDF
 *   GET /api/reportes/{nombreReporte}/excel  → Descarga el reporte en Excel
 *
 * Parámetros: se pasan como query params, ej: ?cedula=V-12345678&fecha=2026-01-01
 */
@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:4200")
public class ReporteController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    /**
     * Genera y descarga un reporte en formato PDF.
     *
     * @param nombreReporte Nombre del archivo sin extensión (ej: "reporte_pagos")
     * @param parametros    Query params que se pasan al reporte como parámetros JasperReports
     */
    @GetMapping("/{nombreReporte}/pdf")
    public ResponseEntity<byte[]> descargarPdf(
            @PathVariable String nombreReporte,
            @RequestParam Map<String, String> parametros) {
        try {
            byte[] pdfBytes = generarReporte(nombreReporte, parametros, "pdf");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + nombreReporte + ".pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Genera y descarga un reporte en formato Excel (.xlsx).
     */
    @GetMapping("/{nombreReporte}/excel")
    public ResponseEntity<byte[]> descargarExcel(
            @PathVariable String nombreReporte,
            @RequestParam Map<String, String> parametros) {
        try {
            byte[] excelBytes = generarReporte(nombreReporte, parametros, "xlsx");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + nombreReporte + ".xlsx\"")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ──────────────────────────────────────────────────────────────
    // Endpoints especializados con control de acceso por rol
    // ──────────────────────────────────────────────────────────────

    /**
     * Reporte de Resolución de Servicios.
     * ACCESO RESTRINGIDO: solo PERSONAL_ADMINISTRATIVO (correo @adm.ucab.edu.ve).
     *
     * Parámetros query:
     *   cedula      → cédula del usuario que solicita (para verificar el rol)
     *   fechaInicio → filtro de fecha inicio (YYYY-MM-DD), opcional
     *   fechaFin    → filtro de fecha fin    (YYYY-MM-DD), opcional
     *   formato     → "pdf" (default) o "excel"
     */
    @GetMapping("/resolucion-servicios/{formato}")
    public ResponseEntity<byte[]> reporteResolucionServicios(
            @PathVariable String formato,
            @RequestParam Map<String, String> parametros) {

        // ── Verificación de rol: solo adm.ucab.edu.ve ──
        String cedula = parametros.get("cedula");
        if (cedula == null || cedula.isBlank()) {
            return ResponseEntity.status(401).body("Sin identificación".getBytes());
        }

        // Consulta el correo del miembro para verificar el subdominio
        String sqlRol = "SELECT correo_institucional FROM Miembro WHERE cedula_miembro = ?";
        try {
            String correo = jdbcTemplate.queryForObject(sqlRol, String.class, cedula);
            if (correo == null || !correo.contains("@adm.")) {
                return ResponseEntity.status(403)
                        .body("Acceso denegado: se requiere rol PERSONAL_ADMINISTRATIVO".getBytes());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(403)
                    .body("No se pudo verificar el rol del usuario".getBytes());
        }

        // ── Preparar parámetros para el reporte ──
        Map<String, String> reportParams = new HashMap<>();
        reportParams.put("fechaInicio", parametros.getOrDefault("fechaInicio", "2000-01-01"));
        reportParams.put("fechaFin",    parametros.getOrDefault("fechaFin",    "2099-12-31"));

        try {
            boolean esPdf = !"excel".equalsIgnoreCase(formato);
            byte[] bytes = generarReporte("reporte_resolucion_servicios", reportParams, esPdf ? "pdf" : "xlsx");
            String nombreArchivo = "resolucion_servicios_" + reportParams.get("fechaInicio") + "_" + reportParams.get("fechaFin");

            if (esPdf) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + ".pdf\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(bytes);
            } else {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + ".xlsx\"")
                        .contentType(MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .body(bytes);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("Error al generar reporte: " + e.getMessage()).getBytes());
        }
    }

    /**
     * Reporte de Conciliación Financiera Diaria.
     * ACCESO RESTRINGIDO: solo PERSONAL_ADMINISTRATIVO (correo @adm.).
     *
     * Parámetros query:
     *   cedula        → cédula del usuario que solicita (para verificar rol)
     *   fechaReporte  → fecha del reporte (YYYY-MM-DD), por defecto hoy
     *   formato       → "pdf" (default) o "excel"
     */
    @GetMapping("/conciliacion-diaria/{formato}")
    public ResponseEntity<byte[]> reporteConciliacionDiaria(
            @PathVariable String formato,
            @RequestParam Map<String, String> parametros) {

        // ── Verificación de rol ──
        String cedula = parametros.get("cedula");
        if (cedula == null || cedula.isBlank()) {
            return ResponseEntity.status(401).body("Sin identificación".getBytes());
        }
        try {
            String correo = jdbcTemplate.queryForObject(
                    "SELECT correo_institucional FROM Miembro WHERE cedula_miembro = ?",
                    String.class, cedula);
            if (correo == null || !correo.contains("@adm.")) {
                return ResponseEntity.status(403)
                        .body("Acceso denegado: se requiere rol PERSONAL_ADMINISTRATIVO".getBytes());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(403)
                    .body("No se pudo verificar el rol del usuario".getBytes());
        }

        // ── Fecha del reporte ──
        String fecha = parametros.getOrDefault("fechaReporte",
                java.time.LocalDate.now().toString());

        Map<String, String> reportParams = new HashMap<>();
        reportParams.put("fechaReporte", fecha);

        try {
            boolean esPdf = !"excel".equalsIgnoreCase(formato);
            byte[] bytes = generarReporte("reporte_conciliacion_diaria", reportParams, esPdf ? "pdf" : "xlsx");
            String nombreArchivo = "conciliacion_diaria_" + fecha;

            if (esPdf) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + nombreArchivo + ".pdf\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(bytes);
            } else {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + nombreArchivo + ".xlsx\"")
                        .contentType(MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .body(bytes);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("Error al generar reporte: " + e.getMessage()).getBytes());
        }
    }

    /**
     * Reporte de Evolución y Proyección Demográfica.
     * ACCESO RESTRINGIDO: solo PERSONAL_ADMINISTRATIVO (correo @adm.).
     *
     * Parámetros query:
     *   cedula        → cédula del usuario que solicita (para verificar rol)
     *   rangoFecha    → filtro de fecha
     *   tipoPersonal  → filtro
     *   dependencia   → filtro
     *   formato       → "pdf" (default) o "excel"
     */
    @GetMapping("/evolucion-demografica/{formato}")
    public ResponseEntity<byte[]> reporteEvolucionDemografica(
            @PathVariable String formato,
            @RequestParam Map<String, String> parametros) {

        // ── Verificación de rol ──
        String cedula = parametros.get("cedula");
        if (cedula == null || cedula.isBlank()) {
            return ResponseEntity.status(401).body("Sin identificación".getBytes());
        }
        try {
            String correo = jdbcTemplate.queryForObject(
                    "SELECT correo_institucional FROM Miembro WHERE cedula_miembro = ?",
                    String.class, cedula);
            if (correo == null || !correo.contains("@adm.")) {
                return ResponseEntity.status(403)
                        .body("Acceso denegado: se requiere rol PERSONAL_ADMINISTRATIVO".getBytes());
            }
        } catch (Exception ex) {
            return ResponseEntity.status(403)
                    .body("No se pudo verificar el rol del usuario".getBytes());
        }

        // ── Parámetros del reporte ──
        Map<String, String> reportParams = new HashMap<>();
        reportParams.put("rangoFecha", parametros.getOrDefault("rangoFecha", "2024"));
        reportParams.put("tipoPersonal", parametros.getOrDefault("tipoPersonal", "Todos"));
        reportParams.put("dependencia", parametros.getOrDefault("dependencia", "Todas las Facultades"));

        try {
            boolean esPdf = !"excel".equalsIgnoreCase(formato);
            byte[] bytes = generarReporte("reporte_demografico", reportParams, esPdf ? "pdf" : "xlsx");
            String nombreArchivo = "evolucion_demografica_" + reportParams.get("rangoFecha");

            if (esPdf) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + nombreArchivo + ".pdf\"")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(bytes);
            } else {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + nombreArchivo + ".xlsx\"")
                        .contentType(MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .body(bytes);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("Error al generar reporte: " + e.getMessage()).getBytes());
        }
    }

    /**
     * Datos para el Dashboard de Evolución y Proyección Demográfica.
     * ACCESO RESTRINGIDO: solo PERSONAL_ADMINISTRATIVO.
     */
    @GetMapping("/evolucion-demografica/datos")
    public ResponseEntity<?> obtenerDatosDemograficos(@RequestParam Map<String, String> parametros) {
        String cedula = parametros.get("cedula");
        if (cedula == null || cedula.isBlank()) {
            return ResponseEntity.status(401).body(Map.of("error", "Sin identificación"));
        }
        try {
            String correo = jdbcTemplate.queryForObject(
                    "SELECT correo_institucional FROM Miembro WHERE cedula_miembro = ?",
                    String.class, cedula);
            if (correo == null || !correo.contains("@adm.")) {
                return ResponseEntity.status(403)
                        .body(Map.of("error", "Acceso denegado"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(403)
                    .body(Map.of("error", "No se pudo verificar el rol"));
        }

        String sql = "SELECT " +
                "b.Nombre AS beneficiario_nombre, " +
                "m.Nombres_Completos || ' ' || m.Apellidos_Completos AS titular_nombre, " +
                "CASE " +
                "    WHEN d.Cedula_Miembro IS NOT NULL THEN 'Docente' " +
                "    WHEN pa.Cedula_Miembro IS NOT NULL THEN 'Administrativo' " +
                "    ELSE 'Otro' " +
                "END AS rol_titular, " +
                "TO_CHAR(b.fecha_nacimiento_beneficiario, 'YYYY-MM-DD') AS fecha_nac, " +
                "((b.fecha_nacimiento_beneficiario + INTERVAL '18 years')::DATE - CURRENT_DATE) AS dias_para_18, " +
                "EXTRACT(YEAR FROM age(CURRENT_DATE, b.fecha_nacimiento_beneficiario)) AS edad " +
                "FROM Beneficiario b " +
                "JOIN Miembro m ON b.Cedula_Miembro = m.Cedula_Miembro " +
                "LEFT JOIN Docente d ON m.Cedula_Miembro = d.Cedula_Miembro " +
                "LEFT JOIN Personal_Administrativo pa ON m.Cedula_Miembro = pa.Cedula_Miembro " +
                "WHERE b.fecha_nacimiento_beneficiario > CURRENT_DATE - INTERVAL '18 years' " +
                "ORDER BY dias_para_18 ASC";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(resultados);
    }


    // Lógica interna de compilación y llenado del reporte
    // ──────────────────────────────────────────────────────────────

    private byte[] generarReporte(String nombreReporte, Map<String, String> parametros, String formato)
            throws Exception {

        // 1. Cargar el archivo .jasper o .jrxml desde resources/reports/
        JasperReport jasperReport = cargarReporte(nombreReporte);

        // 2. Convertir parámetros String a Map<String, Object> para JasperReports
        Map<String, Object> params = new HashMap<>(parametros);

        // 3. Llenar el reporte usando la conexión a la base de datos PostgreSQL
        JasperPrint jasperPrint;
        try (Connection connection = dataSource.getConnection()) {
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
        }

        // 4. Exportar al formato solicitado
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if ("pdf".equals(formato)) {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
        } else {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
        }

        return outputStream.toByteArray();
    }

    /**
     * Carga el reporte: primero intenta .jasper (precompilado), si no, compila .jrxml al vuelo.
     */
    private JasperReport cargarReporte(String nombreReporte) throws Exception {
        // Intentar cargar .jasper precompilado (más rápido)
        String rutaJasper = "reports/" + nombreReporte + ".jasper";
        try {
            ClassPathResource resource = new ClassPathResource(rutaJasper);
            if (resource.exists()) {
                try (InputStream is = resource.getInputStream()) {
                    return (JasperReport) JRLoader.loadObject(is);
                }
            }
        } catch (Exception ignored) { }

        // Si no existe .jasper, compilar desde .jrxml al vuelo
        String rutaJrxml = "reports/" + nombreReporte + ".jrxml";
        ClassPathResource resource = new ClassPathResource(rutaJrxml);
        if (!resource.exists()) {
            throw new RuntimeException("No se encontró el reporte: " + nombreReporte
                    + " (.jasper ni .jrxml) en src/main/resources/reports/");
        }
        try (InputStream is = resource.getInputStream()) {
            return JasperCompileManager.compileReport(is);
        }
    }
}
