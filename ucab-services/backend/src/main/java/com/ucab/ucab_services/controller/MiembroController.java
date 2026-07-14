package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Endpoints de gestión de Miembro.
 *
 * GET  /api/miembros/{cedula}        -> consultar por cédula
 * GET  /api/miembros/correo/{correo} -> consultar por correo
 * GET  /api/miembros/buscar?texto=.. -> consultar por nombre/apellido
 *
 * No hay PUT/DELETE genéricos de "actualizar cualquier campo": antes
 * permitían mandar un claveHash arbitrario desde el cliente porque
 * recibían la entidad Miembro completa. La edición de perfil del
 * usuario se debe manejar con endpoints específicos más adelante.
 */
@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @GetMapping
    public ResponseEntity<List<MiembroDetalleDTO>> listarTodos() {
        return ResponseEntity.ok(miembroService.listarTodos());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> buscarPorCedula(@PathVariable String cedula) {
        try {
            return ResponseEntity.ok(miembroService.buscarPorCedula(cedula));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {
        try {
            return ResponseEntity.ok(miembroService.buscarPorCorreo(correo));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MiembroDetalleDTO>> buscarPorNombreOApellido(@RequestParam String texto) {
        return ResponseEntity.ok(miembroService.buscarPorNombreOApellido(texto));
    }
}