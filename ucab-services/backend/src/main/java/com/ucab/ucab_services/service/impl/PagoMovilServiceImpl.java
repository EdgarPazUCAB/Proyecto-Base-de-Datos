package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.dto.PagoMovilRequest;
import com.ucab.ucab_services.entity.Factura;
import com.ucab.ucab_services.entity.PagoId;
import com.ucab.ucab_services.entity.PagoMovil;
import com.ucab.ucab_services.repository.FacturaRepository;
import com.ucab.ucab_services.repository.PagoMovilRepository;
import com.ucab.ucab_services.service.PagoMovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Registra un pago vía Pago Móvil.
 *
 * IMPORTANTE — supuesto a confirmar: PagoMovilRequest.folioId se
 * interpreta como el Numero_control de la Factura a pagar (no el
 * folio de consumo — esa es una tabla distinta que ya se convirtió
 * en factura antes de poder pagarse). Si "folioId" en realidad debía
 * referirse a otra cosa, ajustar este mapeo.
 *
 * Pago y PagoMovil son una jerarquía JOINED (ver entity/Pago.java):
 * Hibernate inserta automáticamente en ambas tablas (pago y
 * pago_movil) al guardar una sola instancia de PagoMovil — no hace
 * falta guardar el padre por separado.
 */
@Service
public class PagoMovilServiceImpl implements PagoMovilService {

    @Autowired
    private PagoMovilRepository pagoMovilRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    @Transactional
    public PagoMovil registrarPago(PagoMovilRequest request) {
        Factura factura = facturaRepository.findById(request.getFolioId())
                .orElseThrow(() -> new NoSuchElementException(
                        "No existe una factura con número de control " + request.getFolioId()));

        PagoMovil pagoMovil = new PagoMovil();
        pagoMovil.setNumeroControlFactura(factura.getNumeroControl());
        pagoMovil.setFechaOperacion(LocalDateTime.now());
        pagoMovil.setTipoPago("Movil");
        pagoMovil.setCanalOrigen("Digital");
        pagoMovil.setMontoLiquidacion(BigDecimal.valueOf(request.getTotalPagado()));

        pagoMovil.setBanco(request.getBancoEmisor());
        pagoMovil.setTelefonoEmisor(request.getTelefonoEmisor());
        pagoMovil.setReferencia(request.getReferencia());
        pagoMovil.setFechaMovimiento(java.time.LocalDate.now());

        // El trigger trg_actualizar_saldo_factura en PostgreSQL se
        // encarga de descontar este monto del saldo restante de la
        // factura automáticamente al insertar — no se actualiza aquí.
        return pagoMovilRepository.save(pagoMovil);
    }
}