package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.PagoMovilRequest;
import com.ucab.ucab_services.entity.PagoMovil;

public interface PagoMovilService {
    PagoMovil registrarPago(PagoMovilRequest request);
}