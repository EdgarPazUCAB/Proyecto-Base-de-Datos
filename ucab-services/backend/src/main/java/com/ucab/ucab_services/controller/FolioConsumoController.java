package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.FolioConsumo;
import com.ucab.ucab_services.repository.FolioConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/folios-consumo")
@CrossOrigin(origins = "http://localhost:4200")
public class FolioConsumoController {

    @Autowired
    private FolioConsumoRepository folioConsumoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllFolios() {
        List<FolioConsumo> folios = folioConsumoRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();
        for (FolioConsumo folio : folios) {
            response.add(mapFolio(folio));
        }
        return response;
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<List<Map<String, Object>>> getFolioByIdentificador(@PathVariable String identificador) {
        List<FolioConsumo> folios = folioConsumoRepository.findByIdentificador(identificador);
        if (folios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Map<String, Object>> response = new ArrayList<>();
        for (FolioConsumo folio : folios) {
            response.add(mapFolio(folio));
        }
        return ResponseEntity.ok(response);
    }

    private Map<String, Object> mapFolio(FolioConsumo folio) {
        Map<String, Object> map = new HashMap<>();
        map.put("identificador", folio.getIdentificador());
        map.put("fechaApertura", folio.getFechaApertura());
        map.put("estadoFolio", folio.getEstadoCierre()); // Frontend uses estadoFolio
        
        String folioId = folio.getIdentificador();
        
        try {
            // Buscar factura pendiente
            String sqlFactura = "SELECT Saldo_restante_pagar, Numero_control FROM Factura WHERE Identificador = ? AND Estatus_factura != 'Pagada' AND Estatus_factura != 'Anulada' LIMIT 1";
            List<Map<String, Object>> facturas = jdbcTemplate.queryForList(sqlFactura, folioId);
            
            if (!facturas.isEmpty()) {
                Object saldoObj = facturas.get(0).get("saldo_restante_pagar");
                if (saldoObj == null) saldoObj = facturas.get(0).get("Saldo_restante_pagar");
                
                Object numCtrlObj = facturas.get(0).get("numero_control");
                if (numCtrlObj == null) numCtrlObj = facturas.get(0).get("Numero_control");
                
                map.put("saldoRestante", saldoObj);
                map.put("numeroControlFactura", numCtrlObj);
            } else {
                // Sumar items si no hay factura
                String sqlItems = "SELECT SUM((precio_unitario * cantidad) + impuesto) as total FROM item_consumo WHERE identificador = ?";
                Double total = jdbcTemplate.queryForObject(sqlItems, Double.class, folioId);
                map.put("saldoRestante", total != null ? total : 0.0);
            }
        } catch (Exception e) {
            System.err.println("Error al calcular saldo para folio " + folioId + ": " + e.getMessage());
            map.put("saldoRestante", 0.0);
        }
        
        return map;
    }
}