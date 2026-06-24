package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.impl.MiembroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
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
    void testFindAll() {
        // Arrange
        Miembro miembro1 = new Miembro();
        miembro1.setCedulaMiembro("12345678");
        miembro1.setNombresCompletos("Juan");
        miembro1.setApellidosCompletos("Perez");

        Miembro miembro2 = new Miembro();
        miembro2.setCedulaMiembro("87654321");
        miembro2.setNombresCompletos("Maria");
        miembro2.setApellidosCompletos("Gonzalez");

        List<Miembro> miembros = Arrays.asList(miembro1, miembro2);
        when(miembroRepository.findAll()).thenReturn(miembros);

        // Act
        List<Miembro> result = miembroService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345678", result.get(0).getCedulaMiembro());
        assertEquals("87654321", result.get(1).getCedulaMiembro());
        verify(miembroRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");

        when(miembroRepository.findById("12345678")).thenReturn(Optional.of(miembro));

        // Act
        Optional<Miembro> result = miembroService.findById("12345678");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("12345678", result.get().getCedulaMiembro());
        assertEquals("Juan", result.get().getNombresCompletos());
        assertEquals("Perez", result.get().getApellidosCompletos());
        verify(miembroRepository, times(1)).findById("12345678");
    }

    @Test
    void testSave() {
        // Arrange
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");

        when(miembroRepository.save(any(Miembro.class))).thenReturn(miembro);

        // Act
        Miembro result = miembroService.save(miembro);

        // Assert
        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        verify(miembroRepository, times(1)).save(miembro);
    }

    @Test
    void testDeleteById() {
        // Arrange
        String cedula = "12345678";
        doNothing().when(miembroRepository).deleteById(cedula);

        // Act
        miembroService.deleteById(cedula);

        // Assert
        verify(miembroRepository, times(1)).deleteById(cedula);
    }

    @Test
    void testExistsById() {
        // Arrange
        String cedula = "12345678";
        when(miembroRepository.existsById(cedula)).thenReturn(true);

        // Act
        boolean result = miembroService.existsById(cedula);

        // Assert
        assertTrue(result);
        verify(miembroRepository, times(1)).existsById(cedula);
    }

    @Test
    void testCount() {
        // Arrange
        when(miembroRepository.count()).thenReturn(5L);

        // Act
        long result = miembroService.count();

        // Assert
        assertEquals(5, result);
        verify(miembroRepository, times(1)).count();
    }
}