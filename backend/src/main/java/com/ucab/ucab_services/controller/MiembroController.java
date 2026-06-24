package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.CrearMiembroRequest;
import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.exception.AutenticacionException;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints de gestión de Miembro.
 *
 * POST /api/miembros                 -> crear (pantalla "agregar-miembro")
 * GET  /api/miembros/{cedula}        -> consultar por cédula
 * GET  /api/miembros/correo/{correo} -> consultar por correo
 * GET  /api/miembros/buscar?texto=.. -> consultar por nombre/apellido
 *
 * PENDIENTE (ver AccesoDenegadoException): el endpoint de creación
 * debería estar restringido al rol ADMIN_SISTEMA. Por ahora no hay
 * mecanismo de sesión/token que permita verificar "quién" llama, así
 * que esta restricción todavía no se aplica a nivel de backend —
 * solo existe como ocultamiento de la ruta en el frontend. Se debe
 * resolver antes de considerar el CRUD "seguro" de verdad.
 */
@RestController
@RequestMapping("/api/miembros")
@CrossOrigin(origins = "http://localhost:4200")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearMiembroRequest request) {
        try {
            MiembroDetalleDTO creado = miembroService.crear(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> buscarPorCedula(@PathVariable String cedula) {
        try {
            return ResponseEntity.ok(miembroService.buscarPorCedula(cedula));
        } catch (AutenticacionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {
        try {
            return ResponseEntity.ok(miembroService.buscarPorCorreo(correo));
        } catch (AutenticacionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MiembroDetalleDTO>> buscarPorNombreOApellido(
            @RequestParam String texto) {
        return ResponseEntity.ok(miembroService.buscarPorNombreOApellido(texto));
    }
}