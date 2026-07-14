package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.entity.RolMiembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Gestión de Miembro. No existe creación vía API pública.
 *
 * DECISIÓN DE ARQUITECTURA: el hash de contraseña se genera SIEMPRE
 * vía fn_establecer_clave() (PostgreSQL/pgcrypto) — NO se usa
 * BCryptPasswordEncoder de Java aquí. Esto evita tener dos sistemas
 * de hash compitiendo con formatos potencialmente incompatibles.
 */
@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    @Transactional(readOnly = true)
    public MiembroDetalleDTO buscarPorCedula(String cedula) {
        Miembro miembro = miembroRepository.findById(cedula)
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encontró ningún miembro con la cédula " + cedula));
        return aDetalleDTO(miembro);
    }

    @Override
    @Transactional(readOnly = true)
    public MiembroDetalleDTO buscarPorCorreo(String correo) {
        Miembro miembro = miembroRepository.findByCorreoInstitucional(correo)
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encontró ningún miembro con el correo " + correo));
        return aDetalleDTO(miembro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MiembroDetalleDTO> buscarPorNombreOApellido(String texto) {
        return miembroRepository.buscarPorNombreOApellido(texto)
                .stream()
                .map(this::aDetalleDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MiembroDetalleDTO> listarTodos() {
        return miembroRepository.findAll()
                .stream()
                .map(this::aDetalleDTO)
                .toList();
    }

    private MiembroDetalleDTO aDetalleDTO(Miembro miembro) {
        String tipoCategoria = miembro.getTipoCategoria() != null
                ? miembro.getTipoCategoria().getTipoCategoria()
                : null;

        // Antes esto se dejaba como null fijo, lo que rompía el frontend
        // (Profile no podía decidir qué sección mostrar). Se calcula el
        // rol real a partir del subdominio del correo institucional.
        RolMiembro rol = RolMiembro.detectarDesdeCorreo(miembro.getCorreoInstitucional());

        return new MiembroDetalleDTO(
                miembro.getCedulaMiembro(),
                miembro.getNombresCompletos(),
                miembro.getApellidosCompletos(),
                miembro.getSexo(),
                miembro.getFechaNacimiento() != null ? miembro.getFechaNacimiento().toLocalDate() : null,
                miembro.getEstadoCuenta(),
                miembro.getDireccionHabitacion(),
                miembro.getCorreoInstitucional(),
                miembro.getTelefonoPersonal(),
                miembro.getUltimaConexion() != null ? miembro.getUltimaConexion().toLocalDateTime() : null,
                miembro.getIndiceRecurrencia(),
                miembro.getFechaApertura() != null ? miembro.getFechaApertura().toLocalDate() : null,
                tipoCategoria,
                rol
        );
    }
}