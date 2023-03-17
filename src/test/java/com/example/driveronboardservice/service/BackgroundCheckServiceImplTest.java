package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.BackgroundCheck;
import com.example.driveronboardservice.repository.BackgroundCheckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BackgroundCheckServiceImplTest {

    private BackgroundCheckServiceImpl backgroundCheckService;

    @Mock
    private BackgroundCheckRepository backgroundCheckRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        backgroundCheckService = new BackgroundCheckServiceImpl(backgroundCheckRepository);
    }

    @Test
    public void testInitiateCheck() {
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        backgroundCheck.setId(1L);
        backgroundCheck.setStatus("initiated");

        when(backgroundCheckRepository.save(backgroundCheck)).thenReturn(backgroundCheck);

        BackgroundCheck savedBackgroundCheck = backgroundCheckService.initiateCheck(backgroundCheck);

        assertEquals(savedBackgroundCheck, backgroundCheck);
        verify(backgroundCheckRepository, times(1)).save(backgroundCheck);
    }

    @Test
    public void testMarkVerified() {
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        backgroundCheck.setId(1L);
        backgroundCheck.setStatus("pending");

        when(backgroundCheckRepository.findById(1L)).thenReturn(java.util.Optional.of(backgroundCheck));
        when(backgroundCheckRepository.save(backgroundCheck)).thenReturn(backgroundCheck);

        BackgroundCheck verifiedBackgroundCheck = backgroundCheckService.markStatus(1L, "verified", "na");

        assertEquals(verifiedBackgroundCheck.getStatus(), "verified");
        assertEquals(verifiedBackgroundCheck.getComments(), "na");
        verify(backgroundCheckRepository, times(1)).findById(1L);
        verify(backgroundCheckRepository, times(1)).save(backgroundCheck);
    }

    @Test
    public void testGetById() {
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        backgroundCheck.setId(1L);
        backgroundCheck.setStatus("pending");

        when(backgroundCheckRepository.findById(1L)).thenReturn(java.util.Optional.of(backgroundCheck));

        BackgroundCheck retrievedBackgroundCheck = backgroundCheckService.getById(1L);

        assertEquals(retrievedBackgroundCheck, backgroundCheck);
        verify(backgroundCheckRepository, times(1)).findById(1L);
    }
}
