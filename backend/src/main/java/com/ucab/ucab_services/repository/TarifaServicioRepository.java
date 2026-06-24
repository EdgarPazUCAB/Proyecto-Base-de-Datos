package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaServicioRepository extends JpaRepository<TarifaServicio, TarifaServicioId> {

    /**
     * Todas las tarifas (para todos los perfiles) de un servicio dado.
     */
    @Query("SELECT t FROM TarifaServicio t WHERE t.id.codigoServicio = :codigoServicio")
    List<TarifaServicio> findByCodigoServicio(@Param("codigoServicio") String codigoServicio);

    /**
     * Tarifas vigentes (sin Fecha_Fin o aún no vencidas) para un
     * servicio + perfil específico. Útil al calcular el costo final
     * de un servicio para un miembro.
     */
    @Query("""
            SELECT t FROM TarifaServicio t
            WHERE t.id.codigoServicio = :codigoServicio
              AND t.id.perfilSolicitante = :perfilSolicitante
              AND (t.fechaFin IS NULL OR t.fechaFin >= CURRENT_DATE)
            """)
    List<TarifaServicio> findVigentes(@Param("codigoServicio") String codigoServicio,
                                       @Param("perfilSolicitante") String perfilSolicitante);
}