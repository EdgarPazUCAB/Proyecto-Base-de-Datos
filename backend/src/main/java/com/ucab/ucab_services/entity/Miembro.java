package com.ucab.ucab_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Mapea la tabla Miembro (tabla raíz de la jerarquía de subtipos:
 * Estudiante, Becario, Preparador, Egresado, Docente,
 * Personal_Administrativo — cada uno en su propia tabla, vinculados
 * por Cedula_Miembro vía FK, NO por INHERITS).
 *
 * IMPORTANTE sobre seguridad:
 *  - claveHash NUNCA se serializa hacia el frontend (@JsonIgnore).
 *  - La verificación de contraseña se hace llamando a la función
 *    fn_verificar_clave() de PostgreSQL desde el repositorio/servicio,
 *    nunca comparando el hash manualmente en Java.
 */
@Entity
@Table(name = "miembro")
@Getter
@Setter
@NoArgsConstructor
public class Miembro {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @Column(name = "nombres_completos", nullable = false, length = 200)
    private String nombresCompletos;

    @Column(name = "apellidos_completos", nullable = false, length = 200)
    private String apellidosCompletos;

    @Column(name = "sexo", nullable = false, columnDefinition = "char(1)")
    private String sexo; // 'M', 'F', 'O'

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "estado_cuenta", nullable = false, length = 30)
    private String estadoCuenta; // 'Activa', 'Suspendida', 'Bloqueada'

    @JsonIgnore
    @Column(name = "clave_hash")
    private String claveHash;

    @Column(name = "fecha_cambio_clave")
    private LocalDateTime fechaCambioClave;

    @Column(name = "direccion_habitacion", nullable = false)
    private String direccionHabitacion;

    @Column(name = "correo_institucional", nullable = false, unique = true, length = 150)
    private String correoInstitucional;

    @Column(name = "telefono_personal", nullable = false, length = 20)
    private String telefonoPersonal;

    @Column(name = "ultima_conexion")
    private LocalDateTime ultimaConexion;

    @Column(name = "indice_recurrencia", precision = 5, scale = 2)
    private BigDecimal indiceRecurrencia;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_categoria", referencedColumnName = "tipo_categoria")
    private CategoriaFidelidad categoriaFidelidad;

    @Column(name = "intentos_fallidos")
    private Short intentosFallidos;

    @Column(name = "mfa_habilitado")
    private Boolean mfaHabilitado;
}