package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, String> {
    // Spring Data JPA deduce la consulta por el nombre del atributo exacto
    Optional<Miembro> findByCorreoInstitucional(String correoInstitucional);

    boolean existsByCorreoInstitucional(String correoInstitucional);

    @Query(value = """
            SELECT * FROM miembro
            WHERE nombres_completos ILIKE CONCAT('%', :texto, '%')
               OR apellidos_completos ILIKE CONCAT('%', :texto, '%')
            """, nativeQuery = true)
    List<Miembro> buscarPorNombreOApellido(@Param("texto") String texto);

    // ────────────────────────────────────────────────────────────
    // Llamadas a las funciones de seguridad de PostgreSQL.
    // NUNCA se compara/desencripta la contraseña en Java: siempre
    // se delega en la función de la base de datos, que usa pgcrypto.
    // Esta es la fuente de verdad ÚNICA para hash/verificación de
    // contraseñas en el proyecto (no se usa PasswordEncoder de Java).
    // ────────────────────────────────────────────────────────────

    @Query(value = "SELECT fn_verificar_clave(:cedula, :clavePlana)", nativeQuery = true)
    boolean verificarClave(@Param("cedula") String cedula, @Param("clavePlana") String clavePlana);

    @Query(value = "SELECT fn_generar_codigo_mfa(:cedula)", nativeQuery = true)
    String generarCodigoMfa(@Param("cedula") String cedula);

    @Query(value = "SELECT fn_verificar_codigo_mfa(:cedula, :codigo)", nativeQuery = true)
    boolean verificarCodigoMfa(@Param("cedula") String cedula, @Param("codigo") String codigo);

    /**
     * Llama a fn_establecer_clave(cedula, clave_plana) -> void.
     * NO se usa @Modifying aquí: la función se ejecuta como SELECT,
     * por lo que el driver JDBC siempre espera un result set;
     * @Modifying causa el error "Se retornó un resultado cuando no
     * se esperaba ninguno".
     */
    @Query(value = "SELECT fn_establecer_clave(:cedula, :clavePlana)", nativeQuery = true)
    void establecerClave(@Param("cedula") String cedula, @Param("clavePlana") String clavePlana);
}