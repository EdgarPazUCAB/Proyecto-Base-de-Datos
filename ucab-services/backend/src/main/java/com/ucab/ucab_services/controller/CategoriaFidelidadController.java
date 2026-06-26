package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import com.ucab.ucab_services.service.CategoriaFidelidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * SOLO CONSULTA: Categoria_Fidelidad (descuentos, prioridades de
 * reservación) es un dato institucional que la UCAB define
 * directamente en la base de datos — no se crea, edita ni elimina
 * desde la app web. La reclasificación automática de un miembro
 * dentro de una categoría existente ocurre vía trigger en PostgreSQL,
 * no por edición manual de las categorías mismas.
 */
@RestController
@RequestMapping("/api/categorias-fidelidad")
public class CategoriaFidelidadController {

    @Autowired
    private CategoriaFidelidadService categoriaFidelidadService;

    @GetMapping
    public List<CategoriaFidelidad> getAllCategoriasFidelidad() {
        return categoriaFidelidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaFidelidad> getCategoriaFidelidadById(@PathVariable String id) {
        Optional<CategoriaFidelidad> categoriaFidelidad = categoriaFidelidadService.findById(id);
        return categoriaFidelidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}