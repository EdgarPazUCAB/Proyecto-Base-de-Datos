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

    // Spring Data genera la consulta automáticamente a partir del
    // nombre del método (busca por la columna correo_institucional).
    Optional<Miembro> findByCorreoInstitucional(String correoInstitucional);

    boolean existsByCorreoInstitucional(String correoInstitucional);

    /**
     * Búsqueda flexible por nombre o apellido (insensible a mayúsculas),
     * para la pantalla "consultar-miembro". Usa ILIKE de PostgreSQL.
     */
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

    /**
     * Llama a fn_establecer_clave(cedula, clave_plana) -> void.
     * Genera el hash bcrypt y lo guarda en Clave_Hash, actualizando
     * también Fecha_Cambio_Clave. Se usa al crear un miembro nuevo
     * o cuando alguien cambia su contraseña.
     *
     * NOTA IMPORTANTE: aunque la función SQL retorna VOID, se ejecuta
     * como un SELECT, así que el driver JDBC de PostgreSQL siempre
     * devuelve un result set (aunque con una sola fila de valor void).
     * Por eso NO se usa @Modifying aquí — @Modifying espera un
     * executeUpdate() sin result set (como un INSERT/UPDATE/DELETE
     * real), y eso es justamente lo que rompía con error "Se retornó
     * un resultado cuando no se esperaba ninguno". Tratándolo como
     * una consulta normal (sin @Modifying), Hibernate sí acepta que
     * venga un resultado, y simplemente lo descarta.
     */
    @Query(value = "SELECT fn_establecer_clave(:cedula, :clavePlana)", nativeQuery = true)
    void establecerClave(@Param("cedula") String cedula, @Param("clavePlana") String clavePlana);
}