package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.TarjetaRequest;
import com.ucab.ucab_services.entity.Tarjeta;

public interface TarjetaService {
    Tarjeta registrarPago(TarjetaRequest request);
}
