package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Estudiante;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.service.impl.EstudianteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;

    @BeforeEach
    void setUp() {}

    @Test
    void testFindAll() {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setCedulaMiembro("12345678");
        estudiante1.setNombresCompletos("Juan");
        estudiante1.setApellidosCompletos("Perez");
        estudiante1.setPromedio(new BigDecimal("4.5")); // ✅ CORREGIDO A BIGDECIMAL

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setCedulaMiembro("87654321");
        estudiante2.setNombresCompletos("Maria");
        estudiante2.setApellidosCompletos("Gonzalez");
        estudiante2.setPromedio(new BigDecimal("3.8")); // ✅ CORREGIDO A BIGDECIMAL

        List<Estudiante> estudiantes = Arrays.asList(estudiante1, estudiante2);
        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        List<Estudiante> result = estudianteService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Estudiante estudiante = new Estudiante();
        estudiante.setCedulaMiembro("12345678");
        estudiante.setNombresCompletos("Juan");
        estudiante.setApellidosCompletos("Perez");
        estudiante.setPromedio(new BigDecimal("4.5")); // ✅ CORREGIDO A BIGDECIMAL

        when(estudianteRepository.findById("12345678")).thenReturn(Optional.of(estudiante));

        Optional<Estudiante> result = estudianteService.findById("12345678");

        assertTrue(result.isPresent());
        assertEquals("12345678", result.get().getCedulaMiembro());
        verify(estudianteRepository, times(1)).findById("12345678");
    }

    @Test
    void testSave() {
        Estudiante estudiante = new Estudiante();
        estudiante.setCedulaMiembro("12345678");
        estudiante.setNombresCompletos("Juan");
        estudiante.setApellidosCompletos("Perez");
        estudiante.setPromedio(new BigDecimal("4.5"));

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        Estudiante result = estudianteService.save(estudiante);

        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        verify(estudianteRepository, times(1)).save(estudiante);
    }

    @Test
    void testDeleteById() {
        String cedula = "12345678";
        doNothing().when(estudianteRepository).deleteById(cedula);
        estudianteService.deleteById(cedula);
        verify(estudianteRepository, times(1)).deleteById(cedula);
    }

    @Test
    void testExistsById() {
        String cedula = "12345678";
        when(estudianteRepository.existsById(cedula)).thenReturn(true);
        boolean result = estudianteService.existsById(cedula);
        assertTrue(result);
        verify(estudianteRepository, times(1)).existsById(cedula);
    }

    @Test
    void testCount() {
        when(estudianteRepository.count()).thenReturn(5L);
        long result = estudianteService.count();
        assertEquals(5, result);
        verify(estudianteRepository, times(1)).count();
    }
}