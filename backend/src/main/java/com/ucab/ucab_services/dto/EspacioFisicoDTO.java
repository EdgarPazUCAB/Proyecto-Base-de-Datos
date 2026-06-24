package com.ucab.ucab_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EspacioFisicoDTO {
    private String nombreEdif;
    private String direccionInterna;
    private Integer numIdentificador;
    private Integer capacidadAforo;
    private String tipoInmobiliario;
    private String estatus;
}