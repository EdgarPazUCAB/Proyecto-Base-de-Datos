package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.BilleteraTai;
import com.ucab.ucab_services.service.BilleteraTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/billeteras-tai")
public class BilleteraTaiController {

    @Autowired
    private BilleteraTaiService billeteraTaiService;

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
        BilleteraTai billeteraTai = billeteraTaiService.findByCedulaMiembro(cedula);
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
        // Update fields (excluding immutable ones like uid and cedulaMiembro if they shouldn't change)
        billeteraTai.setSaldoVirtual(billeteraTaiDetails.getSaldoVirtual());
        billeteraTai.setSaldoRestante(billeteraTaiDetails.getSaldoRestante());
        BilleteraTai updatedBilleteraTai = billeteraTaiService.save(billeteraTai);
        return ResponseEntity.ok(updatedBilleteraTai);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilleteraTai(@PathVariable UUID id) {
        billeteraTaiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}