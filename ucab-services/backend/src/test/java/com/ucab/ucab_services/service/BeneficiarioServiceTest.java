package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.repository.BeneficiarioRepository;
import com.ucab.ucab_services.service.impl.BeneficiarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneficiarioServiceTest {

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @InjectMocks
    private BeneficiarioServiceImpl beneficiarioService;

    @Test
    void testFindAll() {
        // Act
        beneficiarioService.findAll();

        // Assert
        verify(beneficiarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        String id = "123";
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setDocumentoIdentidad(id);
        when(beneficiarioRepository.findById(id)).thenReturn(Optional.of(beneficiario));

        // Act
        Optional<Beneficiario> result = beneficiarioService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getDocumentoIdentidad());
        verify(beneficiarioRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        // Arrange
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setDocumentoIdentidad("123");
        beneficiario.setNombre("Juan");
        // No apellido field in entity

        when(beneficiarioRepository.save(any(Beneficiario.class))).thenReturn(beneficiario);

        // Act
        Beneficiario result = beneficiarioService.save(beneficiario);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getDocumentoIdentidad());
        assertEquals("Juan", result.getNombre());
        verify(beneficiarioRepository, times(1)).save(beneficiario);
    }

    @Test
    void testDeleteById() {
        // Arrange
        String id = "123";
        doNothing().when(beneficiarioRepository).deleteById(id);

        // Act
        beneficiarioService.deleteById(id);

        // Assert
        verify(beneficiarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testExistsById() {
        // Arrange
        String id = "123";
        when(beneficiarioRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = beneficiarioService.existsById(id);

        // Assert
        assertTrue(result);
        verify(beneficiarioRepository, times(1)).existsById(id);
    }

    @Test
    void testCount() {
        // Arrange
        when(beneficiarioRepository.count()).thenReturn(5L);

        // Act
        long result = beneficiarioService.count();

        // Assert
        assertEquals(5, result);
        verify(beneficiarioRepository, times(1)).count();
    }
}