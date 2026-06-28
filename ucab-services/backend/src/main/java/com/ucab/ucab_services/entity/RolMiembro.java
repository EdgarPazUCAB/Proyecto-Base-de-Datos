package com.ucab.ucab_services.entity;

/**
 * Roles detectables a partir del subdominio del correo institucional.
 *
 * CONVENCIÓN DE PROYECTO (decisión de diseño, no es una convención
 * oficial de la UCAB real — este es un proyecto académico):
 *
 *   est.    -> ESTUDIANTE              (ej: ana.lopez@est.ucab.edu.ve)
 *   egr.    -> EGRESADO                (ej: carlos.perez@egr.ucab.edu.ve)
 *   prof.   -> DOCENTE                 (ej: pedro.martinez@prof.ucab.edu.ve)
 *   adm.    -> PERSONAL_ADMINISTRATIVO (ej: maria.sanchez@adm.ucab.edu.ve)
 *   admin.  -> ADMIN_SISTEMA           (ej: usuario@admin.ucab.edu.ve)
 *
 * El correo es la fuente de verdad RÁPIDA para decidir qué interfaz
 * mostrar en el frontend (evita consultar las 6 tablas de subtipos en
 * cada login/consulta). No se valida contra las tablas de subtipos.
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
     * Detecta el rol a partir del subdominio en el correo institucional.
     * Ej: detectarDesdeCorreo("maria.sanchez@adm.ucab.edu.ve") -> PERSONAL_ADMINISTRATIVO
     *
     * IMPORTANTE: se usa contains("@adm.") (subdominio), NO
     * startsWith("adm.") (prefijo) — la convención de correo cambió
     * de prefijo a subdominio en una iteración posterior del proyecto.
     */
    public static RolMiembro detectarDesdeCorreo(String correoInstitucional) {
        if (correoInstitucional == null) {
            return DESCONOCIDO;
        }

        String correoMinusculas = correoInstitucional.toLowerCase();

        if (correoMinusculas.contains("@admin.")) {
            return ADMIN_SISTEMA;
        }
        if (correoMinusculas.contains("@adm.")) {
            return PERSONAL_ADMINISTRATIVO;
        }
        if (correoMinusculas.contains("@est.")) {
            return ESTUDIANTE;
        }
        if (correoMinusculas.contains("@egr.")) {
            return EGRESADO;
        }
        if (correoMinusculas.contains("@prof.")) {
            return DOCENTE;
        }
        return DESCONOCIDO;
    }
}