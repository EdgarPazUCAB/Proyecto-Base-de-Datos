package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Intenta autenticar al usuario con su cédula y clave.
     * Si la autenticación es exitosa y el MFA está habilitado, genera y devuelve un código MFA.
     * Si el MFA no está habilitado, devuelve los datos de la sesión del miembro.
     *
     * @param cedula   Cédula del miembro
     * @param clave    Clave en texto plano
     * @return Respuesta de inicio de sesión
     */
    public LoginResponse login(String cedula, String clave) {
        // Verificar la credencial usando la función de la base de datos
        Boolean credencialValida = jdbcTemplate.queryForObject(
                "SELECT fn_verificar_clave(?, ?)", Boolean.class, cedula, clave);

        if (Boolean.TRUE.equals(credencialValida)) {
            // Obtener el miembro
            Miembro miembro = miembroRepository.findById(cedula).orElse(null);
            if (miembro != null) {
                // Resetear intentos fallidos (opcional, pero podemos hacerlo aquí)
                miembro.setIntentosFallidos(0);
                miembroRepository.save(miembro);

                // Verificar si MFA está habilitado
                if (miembro.getMfaHabilitado()) {
                    // Generar código MFA
                    String codigoMfa = jdbcTemplate.queryForObject(
                            "SELECT fn_generar_codigo_mfa(?)", String.class, cedula);
                    return new LoginResponse(true, "MFA requerido", codigoMfa);
                } else {
                    // MFA no habilitado, devolver datos de sesión
                    MiembroSesionDTO sesionDto = new MiembroSesionDTO(
                            miembro.getCedulaMiembro(),
                            miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos(),
                            miembro.getCorreoInstitucional(),
                            miembro.getEstadoCuenta()
                    );
                    return new LoginResponse(true, "Inicio de sesión exitoso", sesionDto);
                }
            } else {
                return new LoginResponse(false, "Miembro no encontrado", null);
            }
        } else {
            // Credenciales inválidas, incrementar intentos fallidos
            Miembro miembro = miembroRepository.findById(cedula).orElse(null);
            if (miembro != null) {
                int intentosActuales = miembro.getIntentosFallidos();
                miembro.setIntentosFallidos(intentosActuales + 1);
                miembroRepository.save(miembro);
            }
            return new LoginResponse(false, "Credenciales inválidas", null);
        }
    }

    /**
     * Verifica el código MFA ingresado por el usuario.
     * Si es válido, devuelve los datos de la sesión del miembro.
     *
     * @param cedula   Cédula del miembro
     * @param codigo   Código MFA ingresado
     * @return Respuesta de inicio de sesión
     */
    public LoginResponse verificarCodigoMfa(String cedula, String codigo) {
        // Verificar el código MFA usando la función de la base de datos
        Boolean codigoValido = jdbcTemplate.queryForObject(
                "SELECT fn_verificar_codigo_mfa(?, ?)", Boolean.class, cedula, codigo);

        if (Boolean.TRUE.equals(codigoValido)) {
            // Código válido, obtener el miembro y devolver datos de sesión
            Miembro miembro = miembroRepository.findById(cedula).orElse(null);
            if (miembro != null) {
                MiembroSesionDTO sesionDto = new MiembroSesionDTO(
                        miembro.getCedulaMiembro(),
                        miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos(),
                        miembro.getCorreoInstitucional(),
                        miembro.getEstadoCuenta()
                );
                return new LoginResponse(true, "MFA verificado correctamente", sesionDto);
            } else {
                return new LoginResponse(false, "Miembro no encontrado", null);
            }
        } else {
            // Código inválido, incrementar intentos fallidos (opcional)
            Miembro miembro = miembroRepository.findById(cedula).orElse(null);
            if (miembro != null) {
                int intentosActuales = miembro.getIntentosFallidos();
                miembro.setIntentosFallidos(intentosActuales + 1);
                miembroRepository.save(miembro);
            }
            return new LoginResponse(false, "Código MFA inválido", null);
        }
    }
}