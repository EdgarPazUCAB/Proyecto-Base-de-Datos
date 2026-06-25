package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private List<String> roles;
    private String nombre;
    private String correo;
}