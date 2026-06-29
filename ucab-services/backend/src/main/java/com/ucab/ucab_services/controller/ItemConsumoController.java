package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.ItemConsumo;
import com.ucab.ucab_services.repository.ItemConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemConsumoController {

    @Autowired
    private ItemConsumoRepository itemConsumoRepository;

    @GetMapping("/buscar")
    public ResponseEntity<List<Map<String, Object>>> getCargosByIdentificador(@RequestParam String identificador) {
        List<ItemConsumo> items = itemConsumoRepository.findByIdentificador(identificador);
        List<Map<String, Object>> response = new ArrayList<>();

        for (ItemConsumo item : items) {
            Map<String, Object> cargo = new HashMap<>();
            cargo.put("fechaCargo", item.getFechaCargo());
            cargo.put("concepto", item.getConcepto());
            
            // Calculamos el monto = (precioUnitario * cantidad) + impuesto
            BigDecimal montoBase = item.getPrecioUnitario().multiply(item.getCantidad());
            BigDecimal montoTotal = montoBase.add(item.getImpuesto());
            cargo.put("monto", montoTotal);

            response.add(cargo);
        }

        return ResponseEntity.ok(response);
    }
}
