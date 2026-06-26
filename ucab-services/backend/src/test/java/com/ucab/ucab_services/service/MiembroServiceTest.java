package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.impl.MiembroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MiembroServiceTest {

    @Mock
    private MiembroRepository miembroRepository;

    @InjectMocks
    private MiembroServiceImpl miembroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorCedula() {
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");
        miembro.setCorreoInstitucional("juan.perez@ucab.edu");

        when(miembroRepository.findById("12345678")).thenReturn(Optional.of(miembro));

        MiembroDetalleDTO result = miembroService.buscarPorCedula("12345678");

        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        assertEquals("juan.perez@ucab.edu", result.getCorreoInstitucional());
        verify(miembroRepository, times(1)).findById("12345678");
    }

    @Test
    void testBuscarPorCorreo() {
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");
        miembro.setCorreoInstitucional("juan.perez@ucab.edu");

        when(miembroRepository.findByCorreoInstitucional("juan.perez@ucab.edu")).thenReturn(Optional.of(miembro));

        MiembroDetalleDTO result = miembroService.buscarPorCorreo("juan.perez@ucab.edu");

        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        assertEquals("juan.perez@ucab.edu", result.getCorreoInstitucional());
        verify(miembroRepository, times(1)).findByCorreoInstitucional("juan.perez@ucab.edu");
    }

    @Test
    void testBuscarPorNombreOApellido() {
        Miembro miembro1 = new Miembro();
        miembro1.setCedulaMiembro("12345678");
        miembro1.setNombresCompletos("Juan");
        miembro1.setApellidosCompletos("Perez");

        Miembro miembro2 = new Miembro();
        miembro2.setCedulaMiembro("87654321");
        miembro2.setNombresCompletos("Maria");
        miembro2.setApellidosCompletos("Gonzalez");

        when(miembroRepository.buscarPorNombreOApellido("Juan")).thenReturn(List.of(miembro1, miembro2));

        List<MiembroDetalleDTO> result = miembroService.buscarPorNombreOApellido("Juan");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345678", result.get(0).getCedulaMiembro());
        assertEquals("87654321", result.get(1).getCedulaMiembro());
        verify(miembroRepository, times(1)).buscarPorNombreOApellido("Juan");
    }
}
