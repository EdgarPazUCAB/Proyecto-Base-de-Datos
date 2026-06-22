package com.ucab.ucab_services.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Envío de correos institucionales.
 *
 * VERSIÓN ACTUAL (placeholder): solo imprime el código en el log del
 * servidor, para poder probar el flujo de MFA sin configurar un
 * servidor SMTP real todavía.
 *
 * CUANDO TENGAMOS CREDENCIALES SMTP REALES: se reemplaza el cuerpo de
 * enviarCodigoMfa() para usar JavaMailSender (spring-boot-starter-mail),
 * sin tocar el resto del código — AuthService no necesita cambiar,
 * porque solo conoce esta interfaz, no el detalle de implementación.
 */
@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void enviarCodigoMfa(String correoDestino, String codigo) {
        // TODO: reemplazar por envío real con JavaMailSender cuando
        // se configuren las credenciales SMTP de la UCAB (o un servicio
        // como Gmail/SendGrid para pruebas).
        log.info("[MFA] Código para {}: {}", correoDestino, codigo);
    }
}