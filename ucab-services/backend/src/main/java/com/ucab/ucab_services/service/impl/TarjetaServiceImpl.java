package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.dto.TarjetaRequest;
import com.ucab.ucab_services.entity.Factura;
import com.ucab.ucab_services.entity.Tarjeta;
import com.ucab.ucab_services.repository.FacturaRepository;
import com.ucab.ucab_services.repository.TarjetaRepository;
import com.ucab.ucab_services.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    @Transactional
    public Tarjeta registrarPago(TarjetaRequest request) {
        Factura factura = facturaRepository.findById(request.getFolioId())
                .orElseThrow(() -> new NoSuchElementException(
                        "No existe una factura con número de control " + request.getFolioId()));

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumeroControlFactura(factura.getNumeroControl());
        tarjeta.setFechaOperacion(LocalDateTime.now());
        tarjeta.setTipoPago("Tarjeta");
        tarjeta.setCanalOrigen("Digital"); // Asumido
        tarjeta.setMontoLiquidacion(BigDecimal.valueOf(request.getTotalPagado()));

        tarjeta.setTipoRed(request.getTipoRed());
        tarjeta.setFechaVencimiento(request.getFechaVencimiento());
        tarjeta.setCompania(request.getCompania());
        tarjeta.setNumTarjeta(request.getNumTarjeta());

        return tarjetaRepository.save(tarjeta);
    }
}
