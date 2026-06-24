package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beneficiario")
@Getter @Setter @NoArgsConstructor
public class Beneficiario {

    @Id
    @Column(name = "documento_identidad", length = 30, nullable = false, unique = true)
    private String documentoIdentidad;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false)
    private Miembro miembro;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimiento_beneficiario", nullable = false)
    private java.sql.Date fechaNacimientoBeneficiario;

    @Column(name = "parentesco", length = 50, nullable = false)
    private String parentesco;

    @Column(name = "esquema_vacunacion", columnDefinition = "TEXT")
    private String esquemaVacunacion;

    @Column(name = "centro_edu_inicial", length = 200)
    private String centroEduInicial;

    @Column(name = "constancia_est_universitarios", columnDefinition = "TEXT")
    private String constanciaEstUniversitarios;

    @Column(name = "certificado_solteria", columnDefinition = "TEXT")
    private String certificadoSolteria;

    @Column(name = "estatus_beneficios", length = 30)
    private String estatusBeneficios;

    @Column(name = "fecha_inicio")
    private java.sql.Date fechaInicio;

    @Column(name = "fecha_fin")
    private java.sql.Date fechaFin;

}