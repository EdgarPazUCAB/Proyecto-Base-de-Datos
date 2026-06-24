package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.CrearMiembroRequest;
import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.entity.RolMiembro;
import com.ucab.ucab_services.exception.AutenticacionException;
import com.ucab.ucab_services.repository.MiembroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CRUD de Miembro. La operación de creación está pensada para ser
 * usada únicamente por el rol ADMIN_SISTEMA (ver convención de
 * RolMiembro) — la restricción real de acceso se aplica en el
 * controlador/filtro, no aquí (este servicio asume que ya se validó
 * el permiso antes de llegar a él).
 *
 * IMPORTANTE: no existe un endpoint de "autoregistro". Los miembros
 * los crea la administración con datos ya provistos por la UCAB
 * (cédula, nombre, correo institucional con su prefijo de rol, etc.).
 */
@Service
public class MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    @Transactional
    public MiembroDetalleDTO crear(CrearMiembroRequest request) {
        if (miembroRepository.existsById(request.getCedulaMiembro())) {
            throw new IllegalArgumentException(
                    "Ya existe un miembro registrado con la cédula " + request.getCedulaMiembro());
        }
        if (miembroRepository.existsByCorreoInstitucional(request.getCorreoInstitucional())) {
            throw new IllegalArgumentException(
                    "Ya existe un miembro registrado con el correo " + request.getCorreoInstitucional());
        }

        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro(request.getCedulaMiembro());
        miembro.setNombresCompletos(request.getNombresCompletos());
        miembro.setApellidosCompletos(request.getApellidosCompletos());
        miembro.setSexo(request.getSexo());
        miembro.setFechaNacimiento(request.getFechaNacimiento());
        miembro.setDireccionHabitacion(request.getDireccionHabitacion());
        miembro.setCorreoInstitucional(request.getCorreoInstitucional());
        miembro.setTelefonoPersonal(request.getTelefonoPersonal());
        miembro.setEstadoCuenta("Activa");
        miembro.setFechaApertura(java.time.LocalDate.now());
        miembro.setIntentosFallidos((short) 0);
        miembro.setMfaHabilitado(false);

        // El INSERT inicial se hace sin clave; el hash se establece
        // en un segundo paso vía fn_establecer_clave() (PostgreSQL),
        // igual que hicimos para los datos de prueba. Esto evita que
        // el hashing dependa de que JPA sepa llamar funciones nativas
        // dentro del mismo save().
        miembro = miembroRepository.save(miembro);
        miembroRepository.establecerClave(miembro.getCedulaMiembro(), request.getClaveInicial());

        return aDetalleDTO(miembro);
    }

    @Transactional(readOnly = true)
    public MiembroDetalleDTO buscarPorCedula(String cedula) {
        Miembro miembro = miembroRepository.findById(cedula)
                .orElseThrow(() -> new AutenticacionException(
                        "No se encontró ningún miembro con la cédula " + cedula));
        return aDetalleDTO(miembro);
    }

    @Transactional(readOnly = true)
    public MiembroDetalleDTO buscarPorCorreo(String correo) {
        Miembro miembro = miembroRepository.findByCorreoInstitucional(correo)
                .orElseThrow(() -> new AutenticacionException(
                        "No se encontró ningún miembro con el correo " + correo));
        return aDetalleDTO(miembro);
    }

    @Transactional(readOnly = true)
    public List<MiembroDetalleDTO> buscarPorNombreOApellido(String texto) {
        return miembroRepository.buscarPorNombreOApellido(texto)
                .stream()
                .map(this::aDetalleDTO)
                .toList();
    }

    private MiembroDetalleDTO aDetalleDTO(Miembro miembro) {
        String tipoCategoria = miembro.getCategoriaFidelidad() != null
                ? miembro.getCategoriaFidelidad().getTipoCategoria()
                : null;

        RolMiembro rol = RolMiembro.detectarDesdeCorreo(miembro.getCorreoInstitucional());

        return new MiembroDetalleDTO(
                miembro.getCedulaMiembro(),
                miembro.getNombresCompletos(),
                miembro.getApellidosCompletos(),
                miembro.getSexo(),
                miembro.getFechaNacimiento(),
                miembro.getEstadoCuenta(),
                miembro.getDireccionHabitacion(),
                miembro.getCorreoInstitucional(),
                miembro.getTelefonoPersonal(),
                miembro.getUltimaConexion(),
                miembro.getIndiceRecurrencia(),
                miembro.getFechaApertura(),
                tipoCategoria,
                rol
        );
    }
}