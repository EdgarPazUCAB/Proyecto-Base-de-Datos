package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.CriptomonedasRequest;
import com.ucab.ucab_services.dto.PagoMovilRequest;
import com.ucab.ucab_services.dto.TarjetaRequest;
import com.ucab.ucab_services.dto.ZelleRequest;
import com.ucab.ucab_services.dto.TaiRequest;
import com.ucab.ucab_services.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "http://localhost:4200") // URL por defecto de Angular
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/movil")
    public ResponseEntity<String> procesarPagoMovil(
        @RequestBody PagoMovilRequest request
    ) {
        try {
            // CORRECCIÓN: Mapeo exacto con los atributos de tu PagoMovilRequest
            // request.getFolioId() actúa como el 'identificador' de la factura/solicitud
            // request.getTotalPagado() actúa como el 'monto'
            pagoService.procesarPagoMovil(
                request.getFolioId(),
                request.getTotalPagado(),
                request.getBancoEmisor(),
                request.getTelefonoEmisor(),
                request.getReferencia(),
                request.getMontoTotalVes()
            );

            return ResponseEntity.ok("Pago móvil procesado exitosamente.");
        } catch (RuntimeException e) {
            // Captura errores de negocio (Ej: El monto supera la deuda, o no existe la factura)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getMessage()
            );
        } catch (Exception e) {
            // Captura fallos inesperados del sistema
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "Error interno del servidor al procesar el pago."
            );
        }
    }

    @PostMapping("/tarjeta")
    public ResponseEntity<String> procesarPagoTarjeta(
        @RequestBody TarjetaRequest request
    ) {
        try {
            pagoService.procesarPagoTarjeta(
                request.getFolioId(),
                request.getTotalPagado(),
                request.getTipoRed(),
                request.getFechaVencimiento(),
                request.getCompania(),
                request.getNumTarjeta(),
                request.getMontoTotalVes()
            );

            return ResponseEntity.ok(
                "Pago con tarjeta procesado exitosamente."
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "Error interno del servidor al procesar el pago."
            );
        }
    }

    @PostMapping("/cripto")
    public ResponseEntity<String> procesarPagoCriptomonedas(
        @RequestBody CriptomonedasRequest request
    ) {
        try {
            pagoService.procesarPagoCriptomonedas(
                request.getFolioId(),
                request.getTotalPagado(),
                request.getDxid(),
                request.getRedBlockchain(),
                request.getBilletera(),
                request.getTasaConversion(),
                request.getMontoTotalVes()
            );

            return ResponseEntity.ok(
                "Pago con criptomonedas procesado exitosamente."
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "Error interno del servidor al procesar el pago."
            );
        }
    }

    @PostMapping("/zelle")
    public ResponseEntity<String> procesarPagoZelle(
        @RequestBody ZelleRequest request
    ) {
        try {
            pagoService.procesarPagoZelle(
                request.getFolioId(),
                request.getTotalPagado(),
                request.getNombreTitular(),
                request.getCorreoOrigen(),
                request.getCodigoConfirmacion(),
                request.getMontoTotalVes()
            );

            return ResponseEntity.ok(
                "Pago con Zelle procesado exitosamente."
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "Error interno del servidor al procesar el pago."
            );
        }
    }

    @PostMapping("/tai")
    public ResponseEntity<String> procesarPagoTai(
        @RequestBody TaiRequest request
    ) {
        try {
            pagoService.procesarPagoTai(
                request.getFolioId(),
                request.getTotalPagado(),
                request.getPosTerminal(),
                request.getReciboDigital(),
                request.getMontoTotalVes()
            );

            return ResponseEntity.ok(
                "Pago con tarjeta TAI procesado exitosamente."
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                e.getMessage()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "Error interno del servidor al procesar el pago."
            );
        }
    }

    @GetMapping("/historial/{cedula}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> obtenerHistorialPagos(
        @PathVariable String cedula
    ) {
        try {
            java.util.List<java.util.Map<String, Object>> historial = pagoService.obtenerHistorialPagos(cedula);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/saldo-folio/{identificador}")
    public ResponseEntity<java.util.Map<String, Object>> obtenerSaldoFacturaPorFolio(
        @PathVariable String identificador
    ) {
        try {
            java.util.Map<String, Object> resultado = pagoService.obtenerSaldoFacturaPorFolio(identificador);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/factura/{numeroControl}")
    public ResponseEntity<java.util.Map<String, Object>> obtenerDetalleFactura(
        @PathVariable String numeroControl
    ) {
        try {
            java.util.Map<String, Object> detalle = pagoService.obtenerDetalleFactura(numeroControl);
            if (detalle.containsKey("error")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalle);
            }
            return ResponseEntity.ok(detalle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
