package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.PagoMovilRequest;
import com.ucab.ucab_services.entity.PagoMovil;
import com.ucab.ucab_services.service.PagoMovilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Endpoint para registrar pagos vía Pago Móvil.
 *
 * POST /api/pagos-movil -> registra el pago contra una factura
 * existente, identificada por PagoMovilRequest.folioId (= número de
 * control de la factura).
 */
@RestController
@RequestMapping("/api/pagos-movil")
public class PagoMovilController {

    @Autowired
    private PagoMovilService pagoMovilService;

    @PostMapping
    public ResponseEntity<?> registrarPago(@Valid @RequestBody PagoMovilRequest request) {
        try {
            PagoMovil pago = pagoMovilService.registrarPago(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(pago);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}