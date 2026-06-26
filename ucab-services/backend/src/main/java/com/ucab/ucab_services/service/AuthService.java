package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.repository.DocenteRepository;
import com.ucab.ucab_services.repository.PersonalAdministrativoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AuthService {

    private static final Logger LOG = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Miembro miembro = miembroRepository.findByCorreoInstitucional(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos."));

        boolean claveValida = miembroRepository.verificarClave(miembro.getCedulaMiembro(), request.getClave());

        if (!claveValida) {
            int intentosActuales = miembro.getIntentosFallidos() != null ? miembro.getIntentosFallidos() : 0;
            miembro.setIntentosFallidos(intentosActuales + 1);
            miembroRepository.save(miembro);
            throw new RuntimeException("Usuario o contraseña incorrectos.");
        }

        miembro.setIntentosFallidos(0);
        miembroRepository.save(miembro);

        validarEstadoCuenta(miembro);

        if (Boolean.TRUE.equals(miembro.getMfaHabilitado())) {
            String codigo = miembroRepository.generarCodigoMfa(miembro.getCedulaMiembro());
            LOG.info("[MFA] Código para " + miembro.getCorreoInstitucional() + ": " + codigo);

            LoginResponse response = new LoginResponse();
            response.setRequiereMfa(true);
            response.setCedulaMiembro(miembro.getCedulaMiembro());
            response.setMensaje("Te enviamos un código de verificación a tu correo institucional.");
            return response;
        }

        return construirRespuestaCompleta(miembro);
    }

    @Transactional
    public LoginResponse verificarMfa(VerificarMfaRequest request) {
        Miembro miembro = miembroRepository.findById(request.getCedula())
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado."));

        boolean codigoValido = miembroRepository.verificarCodigoMfa(
                request.getCedula(), request.getCodigo());

        if (!codigoValido) {
            throw new RuntimeException("Código incorrecto o expirado.");
        }

        validarEstadoCuenta(miembro);

        return construirRespuestaCompleta(miembro);
    }

    private void validarEstadoCuenta(Miembro miembro) {
        switch (miembro.getEstadoCuenta()) {
            case "Suspendida" -> throw new RuntimeException(
                    "Tu cuenta está suspendida. Contacta a la administración.");
            case "Bloqueada" -> throw new RuntimeException(
                    "Tu cuenta está bloqueada por intentos fallidos. Contacta a la administración.");
            default -> { /* 'Activa' */ }
        }
    }

    private LoginResponse construirRespuestaCompleta(Miembro miembro) {
        List<String> roles = determinarRoles(miembro.getCedulaMiembro());

        LoginResponse response = new LoginResponse();
        response.setRequiereMfa(false);
        response.setToken("dummy-jwt-token-generado");
        response.setRoles(roles);
        response.setNombre(miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos());
        response.setCorreo(miembro.getCorreoInstitucional());
        response.setCedulaMiembro(miembro.getCedulaMiembro());
        response.setMensaje("Login exitoso.");
        return response;
    }

    private List<String> determinarRoles(String cedula) {
        List<String> roles = new ArrayList<>();

        if (estudianteRepository.existsById(cedula)) {
            roles.add("ESTUDIANTE");
        } else if (docenteRepository.existsById(cedula)) {
            roles.add("DOCENTE");
        } else if (personalAdministrativoRepository.existsById(cedula)) {
            roles.add("PERSONAL_ADMINISTRATIVO");
        } else {
            throw new RuntimeException("Acceso denegado: su rol no tiene permisos para ingresar a la aplicación Web.");
        }

        return roles;
    }
}