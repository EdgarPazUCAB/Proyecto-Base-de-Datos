package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, String> {

    // Spring Data genera la consulta automáticamente a partir del
    // nombre del método (busca por la columna correo_institucional).
    Optional<Miembro> findByCorreoInstitucional(String correoInstitucional);

    boolean existsByCorreoInstitucional(String correoInstitucional);

    // ────────────────────────────────────────────────────────────
    // Llamadas a las funciones de seguridad de PostgreSQL.
    // NUNCA se compara/desencripta la contraseña en Java: siempre
    // se delega en la función de la base de datos, que usa pgcrypto.
    // ────────────────────────────────────────────────────────────

    /**
     * Llama a fn_verificar_clave(cedula, clave_plana) -> boolean.
     */
    @Query(value = "SELECT fn_verificar_clave(:cedula, :clavePlana)", nativeQuery = true)
    boolean verificarClave(@Param("cedula") String cedula, @Param("clavePlana") String clavePlana);

    /**
     * Llama a fn_generar_codigo_mfa(cedula) -> varchar(6).
     * Inserta una fila nueva en MFA_Codigo y devuelve el código generado,
     * para que el servicio lo envíe por correo.
     */
    @Query(value = "SELECT fn_generar_codigo_mfa(:cedula)", nativeQuery = true)
    String generarCodigoMfa(@Param("cedula") String cedula);

    /**
     * Llama a fn_verificar_codigo_mfa(cedula, codigo) -> boolean.
     * Si es válido, marca el código como usado dentro de la misma función.
     */
    @Query(value = "SELECT fn_verificar_codigo_mfa(:cedula, :codigo)", nativeQuery = true)
    boolean verificarCodigoMfa(@Param("cedula") String cedula, @Param("codigo") String codigo);
}