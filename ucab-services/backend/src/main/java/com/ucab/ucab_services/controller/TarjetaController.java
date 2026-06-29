package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.TarjetaRequest;
import com.ucab.ucab_services.entity.Tarjeta;
import com.ucab.ucab_services.service.TarjetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/pagos-tarjeta")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<?> registrarPago(@Valid @RequestBody TarjetaRequest request) {
        try {
            Tarjeta pago = tarjetaService.registrarPago(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(pago);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
