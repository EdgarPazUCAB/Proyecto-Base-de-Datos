package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.entity.RolMiembro;
import com.ucab.ucab_services.exception.AutenticacionException;
import com.ucab.ucab_services.repository.MiembroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Orquesta el flujo completo de autenticación:
 *   1. login(correo, clave)        -> valida credenciales, decide si pide MFA
 *   2. verificarMfa(cedula, codigo) -> completa el login si el código es válido
 *
 * IMPORTANTE: no hay endpoint de "registro". Los miembros ya existen
 * en la base de datos (cargados por administración con credenciales
 * provistas por la UCAB) — este servicio solo autentica, nunca crea
 * cuentas nuevas.
 */
@Service
public class AuthService {

    private final MiembroRepository miembroRepository;
    private final EmailService emailService; // simulado por ahora (ver EmailService)

    public AuthService(MiembroRepository miembroRepository, EmailService emailService) {
        this.miembroRepository = miembroRepository;
        this.emailService = emailService;
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Miembro miembro = miembroRepository.findByCorreoInstitucional(request.getCorreoInstitucional())
                .orElseThrow(() -> new AutenticacionException("Correo o contraseña incorrectos."));

        // No revelamos si fue el correo o la clave lo que falló
        // (buena práctica: no dar pistas a un atacante).
        boolean claveValida = miembroRepository.verificarClave(
                miembro.getCedulaMiembro(), request.getClave());

        if (!claveValida) {
            // Aquí, en una versión más completa, incrementaríamos
            // Intentos_fallidos (el trigger trg_bloquear_por_intentos_fallidos
            // se encarga de bloquear la cuenta automáticamente al llegar a 5).
            throw new AutenticacionException("Correo o contraseña incorrectos.");
        }

        validarEstadoCuenta(miembro);

        if (Boolean.TRUE.equals(miembro.getMfaHabilitado())) {
            String codigo = miembroRepository.generarCodigoMfa(miembro.getCedulaMiembro());
            emailService.enviarCodigoMfa(miembro.getCorreoInstitucional(), codigo);

            return new LoginResponse(
                    true,
                    true,
                    "Te enviamos un código de verificación a tu correo institucional.",
                    null
            );
        }

        // Sin MFA: login completo de inmediato
        return new LoginResponse(true, false, "Login exitoso.", aSesionDTO(miembro));
    }

    @Transactional
    public LoginResponse verificarMfa(VerificarMfaRequest request) {
        Miembro miembro = miembroRepository.findById(request.getCedulaMiembro())
                .orElseThrow(() -> new AutenticacionException("Miembro no encontrado."));

        boolean codigoValido = miembroRepository.verificarCodigoMfa(
                miembro.getCedulaMiembro(), request.getCodigo());

        if (!codigoValido) {
            throw new AutenticacionException("Código incorrecto o expirado.");
        }

        validarEstadoCuenta(miembro);

        return new LoginResponse(true, false, "Login exitoso.", aSesionDTO(miembro));
    }

    private void validarEstadoCuenta(Miembro miembro) {
        switch (miembro.getEstadoCuenta()) {
            case "Suspendida" -> throw new AutenticacionException(
                    "Tu cuenta está suspendida. Contacta a la administración.");
            case "Bloqueada" -> throw new AutenticacionException(
                    "Tu cuenta está bloqueada por intentos fallidos. Contacta a la administración.");
            default -> { /* 'Activa': continúa normalmente */ }
        }
    }

    private MiembroSesionDTO aSesionDTO(Miembro miembro) {
        String tipoCategoria = miembro.getCategoriaFidelidad() != null
                ? miembro.getCategoriaFidelidad().getTipoCategoria()
                : null;

        RolMiembro rol = RolMiembro.detectarDesdeCorreo(miembro.getCorreoInstitucional());

        return new MiembroSesionDTO(
                miembro.getCedulaMiembro(),
                miembro.getNombresCompletos(),
                miembro.getApellidosCompletos(),
                miembro.getCorreoInstitucional(),
                miembro.getEstadoCuenta(),
                tipoCategoria,
                rol
        );
    }
}