package com.ucab.ucab_services.entity;

/**
 * Roles detectables a partir del subdominio del correo institucional (después del @).
 *
 * CONVENCIÓN DE PROYECTO (decisión de diseño, no es una convención
 * oficial de la UCAB real — este es un proyecto académico):
 *
 * est.    -> ESTUDIANTE              (ej: ana.lopez@est.ucab.edu.ve)
 * egr.    -> EGRESADO                (ej: carlos.perez@egr.ucab.edu.ve)
 * prof.   -> DOCENTE                 (ej: pedro.martinez@prof.ucab.edu.ve)
 * adm.    -> PERSONAL_ADMINISTRATIVO (ej: maria.sanchez@adm.ucab.edu.ve)
 * admin.  -> ADMIN_SISTEMA           (ej: usuario@admin.ucab.edu.ve)
 *
 * IMPORTANTE: "adm." y "admin." son conceptos DISTINTOS:
 * - adm.   = el miembro es Personal_Administrativo (un SUBTIPO normal
 * de Miembro, con su propia ficha en la tabla homónima).
 * - admin. = privilegio de SISTEMA: acceso a pantallas de gestión de
 * la plataforma (ej. agregar-miembro). Un admin. del
 * sistema puede o no ser también Personal_Administrativo
 * — son dos cosas independientes.
 *
 * El correo es la fuente de verdad RÁPIDA para decidir qué interfaz
 * mostrar en el frontend (evita consultar las 6 tablas de subtipos en
 * cada login). No se valida contra las tablas de subtipos: si algún
 * día el correo de alguien no coincidiera con su registro real en
 * Estudiante/Docente/etc., eso sería un error de carga de datos a
 * corregir manualmente, no algo que el login intente reconciliar.
 */
public enum RolMiembro {
    ESTUDIANTE("est."),
    EGRESADO("egr."),
    DOCENTE("prof."),
    PERSONAL_ADMINISTRATIVO("adm."),
    ADMIN_SISTEMA("admin."),
    DESCONOCIDO(null);

    private final String prefijo;
    
    // Definimos el dominio base para concatenarlo fácilmente
    private static final String DOMINIO_BASE = "ucab.edu.ve";

    RolMiembro(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPrefijo() {
        return prefijo;
    }

    /**
     * Detecta el rol a partir del correo institucional completo.
     * Ej: detectarDesdeCorreo("ana.lopez@est.ucab.edu.ve") -> ESTUDIANTE
     *
     * Se evalúa si el correo termina exactamente con "@" + prefijo + "ucab.edu.ve".
     * Al usar la validación con '@' al inicio y el dominio al final, no hay riesgo 
     * de colisión entre "adm." y "admin.", ya que se buscan cadenas exactas como 
     * "@adm.ucab.edu.ve" y "@admin.ucab.edu.ve".
     */
    public static RolMiembro detectarDesdeCorreo(String correoInstitucional) {
        if (correoInstitucional == null) {
            return DESCONOCIDO;
        }

        String correoMinusculas = correoInstitucional.toLowerCase();

        // Se revisa ADMIN_SISTEMA primero como buena práctica, aunque con este formato
        // de sufijo exacto ("@admin.ucab.edu.ve" vs "@adm.ucab.edu.ve") ya no hay 
        // riesgo lógico de colisión.
        if (correoMinusculas.endsWith("@" + ADMIN_SISTEMA.prefijo + DOMINIO_BASE)) {
            return ADMIN_SISTEMA;
        }
        if (correoMinusculas.endsWith("@" + PERSONAL_ADMINISTRATIVO.prefijo + DOMINIO_BASE)) {
            return PERSONAL_ADMINISTRATIVO;
        }
        if (correoMinusculas.endsWith("@" + ESTUDIANTE.prefijo + DOMINIO_BASE)) {
            return ESTUDIANTE;
        }
        if (correoMinusculas.endsWith("@" + EGRESADO.prefijo + DOMINIO_BASE)) {
            return EGRESADO;
        }
        if (correoMinusculas.endsWith("@" + DOCENTE.prefijo + DOMINIO_BASE)) {
            return DOCENTE;
        }
        
        return DESCONOCIDO;
    }
}