package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.repository.DocenteRepository;
import com.ucab.ucab_services.repository.PersonalAdministrativoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Nota: Si manejas un servicio para JWT, inyéctalo aquí
    // @Autowired
    // private JwtService jwtService;

    public LoginResponse authenticate(LoginRequest request) {
        // 1. Buscar al miembro base por su correo electrónico institucional
        Miembro miembro = miembroRepository.findByCorreoInstitucional(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en el sistema"));

        // 2. Validar que la contraseña coincida con el hash BCrypt guardado (claveHash)
        if (!passwordEncoder.matches(request.getClave(), miembro.getClaveHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Obtener el rol restringido a los 3 tipos permitidos
        List<String> roles = determinarRoles(miembro.getCedulaMiembro());

        // 4. Mapear los datos a la respuesta esperada por Angular
        LoginResponse response = new LoginResponse();

        // Simulación de Token (Sustitúyelo por tu generador de JWT si aplica)
        // response.setToken(jwtService.generateToken(miembro, roles));
        response.setToken("dummy-jwt-token-generado");

        response.setRoles(roles);

        // Se concatena nombre y apellido para enviarlo al Frontend
        response.setNombre(miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos());

        // Se extrae el correo usando el getter correcto de la entidad
        response.setCorreo(miembro.getCorreoInstitucional());

        return response;
    }

    /**
     * Evalúa la cédula de forma restrictiva.
     * Solo permite los roles de Estudiante, Docente o Personal Administrativo.
     */
    private List<String> determinarRoles(String cedula) {
        List<String> roles = new ArrayList<>();

        if (estudianteRepository.existsById(cedula)) {
            roles.add("ESTUDIANTE");
        } else if (docenteRepository.existsById(cedula)) {
            roles.add("DOCENTE");
        } else if (personalAdministrativoRepository.existsById(cedula)) {
            roles.add("PERSONAL_ADMINISTRATIVO");
        } else {
            // Si el usuario existe en la tabla general pero no en estas tres tablas
            throw new RuntimeException("Acceso denegado: Su rol no tiene permisos para ingresar a la aplicación Web.");
        }

        return roles;
    }
}