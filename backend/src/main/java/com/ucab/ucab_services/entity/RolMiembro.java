package com.ucab.ucab_services.entity;

/**
 * Roles detectables a partir del prefijo del correo institucional.
 *
 * CONVENCIÓN DE PROYECTO (decisión de diseño, no es una convención
 * oficial de la UCAB real — este es un proyecto académico):
 *
 *   est.    -> ESTUDIANTE              (ej: est.ana.lopez@ucab.edu.ve)
 *   egr.    -> EGRESADO                (ej: egr.carlos.perez@ucab.edu.ve)
 *   prof.   -> DOCENTE                 (ej: prof.pedro.martinez@ucab.edu.ve)
 *   adm.    -> PERSONAL_ADMINISTRATIVO (ej: adm.maria.sanchez@ucab.edu.ve)
 *   admin.  -> ADMIN_SISTEMA           (ej: admin.maria.sanchez@ucab.edu.ve)
 *
 * IMPORTANTE: "adm." y "admin." son conceptos DISTINTOS:
 *   - adm.   = el miembro es Personal_Administrativo (un SUBTIPO normal
 *              de Miembro, con su propia ficha en la tabla homónima).
 *   - admin. = privilegio de SISTEMA: acceso a pantallas de gestión de
 *              la plataforma (ej. agregar-miembro). Un admin. del
 *              sistema puede o no ser también Personal_Administrativo
 *              — son dos cosas independientes.
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

    RolMiembro(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPrefijo() {
        return prefijo;
    }

    /**
     * Detecta el rol a partir del correo institucional completo.
     * Ej: detectarDesdeCorreo("est.ana.lopez@ucab.edu.ve") -> ESTUDIANTE
     *
     * IMPORTANTE: se revisa "admin." ANTES que "adm." porque "admin."
     * también empezaría a matchear con startsWith("adm.") si se
     * revisara en el orden incorrecto (admin. contiene "adm" + "in.").
     * En realidad "adm." y "admin." no colisionan porque el punto va
     * pegado distinto ("adm." vs "admin."), pero se revisa admin.
     * primero por claridad y para evitar futuros bugs si se ajustan
     * los prefijos.
     */
    public static RolMiembro detectarDesdeCorreo(String correoInstitucional) {
        if (correoInstitucional == null) {
            return DESCONOCIDO;
        }

        String correoMinusculas = correoInstitucional.toLowerCase();

        if (correoMinusculas.startsWith(ADMIN_SISTEMA.prefijo)) {
            return ADMIN_SISTEMA;
        }
        if (correoMinusculas.startsWith(PERSONAL_ADMINISTRATIVO.prefijo)) {
            return PERSONAL_ADMINISTRATIVO;
        }
        if (correoMinusculas.startsWith(ESTUDIANTE.prefijo)) {
            return ESTUDIANTE;
        }
        if (correoMinusculas.startsWith(EGRESADO.prefijo)) {
            return EGRESADO;
        }
        if (correoMinusculas.startsWith(DOCENTE.prefijo)) {
            return DOCENTE;
        }
        return DESCONOCIDO;
    }
}