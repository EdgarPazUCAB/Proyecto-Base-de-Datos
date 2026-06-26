package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private List<String> roles;
    private String nombre;
    private String correo;
    private String cedulaMiembro;

    @JsonProperty("requiereMfa") // Evita discrepancias con el generador de Lombok
    private boolean requiereMfa;

    private String mensaje;
}