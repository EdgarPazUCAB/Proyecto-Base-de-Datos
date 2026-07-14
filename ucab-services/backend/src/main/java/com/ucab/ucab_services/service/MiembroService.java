package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.MiembroDetalleDTO;

import java.util.List;

public interface MiembroService {
    MiembroDetalleDTO buscarPorCedula(String cedula);
    MiembroDetalleDTO buscarPorCorreo(String correo);
    List<MiembroDetalleDTO> buscarPorNombreOApellido(String texto);
    List<MiembroDetalleDTO> listarTodos();
}