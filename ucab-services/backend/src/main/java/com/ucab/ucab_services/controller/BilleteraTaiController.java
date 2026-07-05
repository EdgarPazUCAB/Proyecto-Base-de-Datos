package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.BilleteraTai;
import com.ucab.ucab_services.service.BilleteraTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/billeteras-tai")
@CrossOrigin(origins = "http://localhost:4200")
public class BilleteraTaiController {

    @Autowired
    private BilleteraTaiService billeteraTaiService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<BilleteraTai> getAllBilleterasTai() {
        return billeteraTaiService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BilleteraTai> getBilleteraTaiById(@PathVariable UUID id) {
        BilleteraTai billeteraTai = billeteraTaiService.findById(id);
        return billeteraTai != null ? ResponseEntity.ok(billeteraTai) : ResponseEntity.notFound().build();
    }

    @GetMapping("/miembro/{cedula}")
    public ResponseEntity<BilleteraTai> getBilleteraTaiByMiembro(@PathVariable String cedula) {
        BilleteraTai billeteraTai = billeteraTaiService.findByMiembroCedulaMiembro(cedula);
        return billeteraTai != null ? ResponseEntity.ok(billeteraTai) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public BilleteraTai createBilleteraTai(@RequestBody BilleteraTai billeteraTai) {
        return billeteraTaiService.save(billeteraTai);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BilleteraTai> updateBilleteraTai(@PathVariable UUID id, @RequestBody BilleteraTai billeteraTaiDetails) {
        BilleteraTai billeteraTai = billeteraTaiService.findById(id);
        if (billeteraTai == null) {
            return ResponseEntity.notFound().build();
        }
        billeteraTai.setSaldoVirtual(billeteraTaiDetails.getSaldoVirtual());
        billeteraTai.setSaldoRestante(billeteraTaiDetails.getSaldoRestante());
        BilleteraTai updatedBilleteraTai = billeteraTaiService.save(billeteraTai);
        return ResponseEntity.ok(updatedBilleteraTai);
    }

    /**
     * Endpoint dedicado para recargar saldo TAI usando JDBC directo.
     * Garantiza que tanto Saldo_Virtual como Saldo_Restante se actualicen
     * correctamente en la base de datos sin depender del mapeo JPA.
     */
    @PostMapping("/recargar/{cedula}")
    public ResponseEntity<Map<String, Object>> recargarSaldo(
            @PathVariable String cedula,
            @RequestBody Map<String, Double> body) {
        try {
            Double montoRecarga = body.get("monto");
            if (montoRecarga == null || montoRecarga <= 0) {
                return ResponseEntity.badRequest().body(Map.of("error", "Monto de recarga invalido"));
            }

            String sqlSelect = "SELECT UID, Saldo_Virtual, Saldo_Restante FROM Billetera_TAI WHERE Cedula_Miembro = ?";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelect, cedula);
            if (rows.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontro billetera TAI para la cedula: " + cedula));
            }

            // Incrementar ambos saldos con SQL directo
            String sqlUpdate = "UPDATE Billetera_TAI SET Saldo_Virtual = Saldo_Virtual + ?, Saldo_Restante = Saldo_Restante + ? WHERE Cedula_Miembro = ?";
            jdbcTemplate.update(sqlUpdate, montoRecarga, montoRecarga, cedula);

            // Devolver el estado actualizado
            List<Map<String, Object>> updated = jdbcTemplate.queryForList(sqlSelect, cedula);
            Map<String, Object> result = updated.get(0);

            Object uid = result.get("uid");
            if (uid == null) uid = result.get("UID");

            return ResponseEntity.ok(Map.of(
                "uid", uid.toString(),
                "saldoVirtual", result.get("saldo_virtual") != null ? result.get("saldo_virtual") : result.get("Saldo_Virtual"),
                "saldoRestante", result.get("saldo_restante") != null ? result.get("saldo_restante") : result.get("Saldo_Restante")
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al recargar saldo: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilleteraTai(@PathVariable UUID id) {
        billeteraTaiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}