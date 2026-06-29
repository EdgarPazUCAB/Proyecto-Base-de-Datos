package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.PagoMovilRequest;
import com.ucab.ucab_services.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "http://localhost:4200") // URL por defecto de Angular
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/movil")
    public ResponseEntity<String> procesarPagoMovil(@RequestBody PagoMovilRequest request) {
        try {
            // CORRECCIÓN: Mapeo exacto con los atributos de tu PagoMovilRequest
            // request.getFolioId() actúa como el 'identificador' de la factura/solicitud
            // request.getTotalPagado() actúa como el 'monto'
            pagoService.procesarPagoMovil(
                request.getFolioId(), 
                request.getTotalPagado(),
                request.getBancoEmisor(),
                request.getTelefonoEmisor(),
                request.getReferencia()
            );

            return ResponseEntity.ok("Pago móvil procesado exitosamente.");
        } catch (RuntimeException e) {
            // Captura errores de negocio (Ej: El monto supera la deuda, o no existe la factura)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Captura fallos inesperados del sistema
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor al procesar el pago.");
        }
    }
}