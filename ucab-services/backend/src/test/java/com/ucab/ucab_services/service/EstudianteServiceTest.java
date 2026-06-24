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
    void setUp() {
        // MockitoAnnotations.openMocks(this); // not needed with extension
    }

    @Test
    void testFindAll() {
        // Arrange
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setCedulaMiembro("12345678");
        estudiante1.setNombresCompletos("Juan");
        estudiante1.setApellidosCompletos("Perez");
        estudiante1.setPromedio(4.5);

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setCedulaMiembro("87654321");
        estudiante2.setNombresCompletos("Maria");
        estudiante2.setApellidosCompletos("Gonzalez");
        estudiante2.setPromedio(3.8);

        List<Estudiante> estudiantes = Arrays.asList(estudiante1, estudiante2);
        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        // Act
        List<Estudiante> result = estudianteService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345678", result.get(0).getCedulaMiembro());
        assertEquals("87654321", result.get(1).getCedulaMiembro());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Estudiante estudiante = new Estudiante();
        estudiante.setCedulaMiembro("12345678");
        estudiante.setNombresCompletos("Juan");
        estudiante.setApellidosCompletos("Perez");
        estudiante.setPromedio(4.5);

        when(estudianteRepository.findById("12345678")).thenReturn(Optional.of(estudiante));

        // Act
        Optional<Estudiante> result = estudianteService.findById("12345678");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("12345678", result.get().getCedulaMiembro());
        assertEquals("Juan", result.get().getNombresCompletos());
        assertEquals("Perez", result.get().getApellidosCompletos());
        assertEquals(4.5, result.get().getPromedio());
        verify(estudianteRepository, times(1)).findById("12345678");
    }

    @Test
    void testSave() {
        // Arrange
        Estudiante estudiante = new Estudiante();
        estudiante.setCedulaMiembro("12345678");
        estudiante.setNombresCompletos("Juan");
        estudiante.setApellidosCompletos("Perez");
        estudiante.setPromedio(4.5);

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        // Act
        Estudiante result = estudianteService.save(estudiante);

        // Assert
        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        assertEquals(4.5, result.getPromedio());
        verify(estudianteRepository, times(1)).save(estudiante);
    }

    @Test
    void testDeleteById() {
        // Arrange
        String cedula = "12345678";
        doNothing().when(estudianteRepository).deleteById(cedula);

        // Act
        estudianteService.deleteById(cedula);

        // Assert
        verify(estudianteRepository, times(1)).deleteById(cedula);
    }

    @Test
    void testExistsById() {
        // Arrange
        String cedula = "12345678";
        when(estudianteRepository.existsById(cedula)).thenReturn(true);

        // Act
        boolean result = estudianteService.existsById(cedula);

        // Assert
        assertTrue(result);
        verify(estudianteRepository, times(1)).existsById(cedula);
    }

    @Test
    void testCount() {
        // Arrange
        when(estudianteRepository.count()).thenReturn(5L);

        // Act
        long result = estudianteService.count();

        // Assert
        assertEquals(5, result);
        verify(estudianteRepository, times(1)).count();
    }
}