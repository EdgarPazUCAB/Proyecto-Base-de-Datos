package com.ucab.ucab_services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OfertaLaboralService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> obtenerTodasLasOfertas() {
        String sql = 
            "SELECT o.id_entidad_externa as \"idEntidadExterna\", " +
            "       o.cargo_laboral as \"cargoLaboral\", " +
            "       o.perfil_buscado as \"perfilBuscado\", " +
            "       o.beneficios as \"beneficios\", " +
            "       o.rango_fecha_graduacion as \"rangoFechaGraduacion\", " +
            "       o.fecha_oferta as \"fechaOferta\", " +
            "       o.responsabilidades as \"responsabilidades\", " +
            "       o.estatus_vacante as \"estatusVacante\", " +
            "       o.indice_academico_min as \"indiceAcademicoMin\", " +
            "       e.razon_social as \"empresa\" " +
            "FROM Oferta_Laboral o " +
            "JOIN Externa e ON o.id_entidad_externa = e.id_entidad " +
            "ORDER BY o.fecha_oferta DESC";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> obtenerOfertaDetalle(int idEntidad, String cargo) {
        String sql = 
            "SELECT o.id_entidad_externa as \"idEntidadExterna\", " +
            "       o.cargo_laboral as \"cargoLaboral\", " +
            "       o.perfil_buscado as \"perfilBuscado\", " +
            "       o.beneficios as \"beneficios\", " +
            "       o.rango_fecha_graduacion as \"rangoFechaGraduacion\", " +
            "       o.fecha_oferta as \"fechaOferta\", " +
            "       o.responsabilidades as \"responsabilidades\", " +
            "       o.estatus_vacante as \"estatusVacante\", " +
            "       o.indice_academico_min as \"indiceAcademicoMin\", " +
            "       e.razon_social as \"empresa\" " +
            "FROM Oferta_Laboral o " +
            "JOIN Externa e ON o.id_entidad_externa = e.id_entidad " +
            "WHERE o.id_entidad_externa = ? AND o.cargo_laboral = ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, idEntidad, cargo);
        if(results.isEmpty()) return null;
        return results.get(0);
    }
}
