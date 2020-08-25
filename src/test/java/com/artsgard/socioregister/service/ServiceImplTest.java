package com.artsgard.socioregister.service;

import com.artsgard.socioregister.mock.SocioMock;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.SocioRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {
    
    @Mock
    private SocioRepository socioRepo;
    
    private List<SocioModel> socios;
    private SocioModel socio;

    @BeforeEach
    public void setup() {
        this.socio = new SocioModel(4L, "avi", "Antonio", "Vivaldi", "vivaldi@gmail.com", true);
        this.socios = SocioMock.generateSocios();
    }

    @Test
    public void findAllSociosTest() {
       given(socioRepo.findAll()).willReturn(socios);
       List<SocioModel> expected =  socioRepo.findAll();
       assertEquals(expected, socios);
    }

    @Test
    public void findSocioByIdTest() {
        given(socioRepo.findById(1L)).willReturn(this.socio);
        final SocioModel expected = socioRepo.findById(1L);
        assertEquals(socio, expected);
        assertThat(this.socio).isNotNull();
        assertThat(this.socio.getUsername()).isEqualTo("avi");
    }

    @Test
    public void findSocioByUsernameTest() {
        given(socioRepo.findByUsername("avi")).willReturn(this.socio); // given when confusing?
        when(socioRepo.findByUsername("avi")).thenReturn(this.socio);
        final SocioModel expected = socioRepo.findByUsername("avi");
        assertEquals(socio, expected);
        assertThat(this.socio).isNotNull();
        assertThat(this.socio.getUsername()).isEqualTo("avi");
    }

    @Test
    public void saveSocioTest() {
        given(socioRepo.save(socio)).willReturn(socio);
        final SocioModel expected = socioRepo.save(socio);
        assertThat(socioRepo.save(socio)).isNotNull();
        assertEquals(socio, expected);
    }

    @Test
    public void updateSocioTest() {
        given(socioRepo.update(socio, 1L)).willReturn(socio);
        final SocioModel expected = socioRepo.update(socio, 1L);
        assertThat(socioRepo.update(socio, 1L)).isNotNull();
    }

    @Test
    public void deleteSocioByIdTest() {
        socioRepo.deleteSocioById(1L);
        verify(socioRepo, times(1)).deleteSocioById(1L);
    }

    @Test
    public void hasSocioByIdTest() {
        given(socioRepo.hasById(1L)).willReturn(true);
        assertThat(socioRepo.hasById(1L)).isTrue();
    }

    @Test
    public void isSocioActiveByIdTest() {
        given(socioRepo.isActiveById(1L)).willReturn(true);
        assertThat(socioRepo.isActiveById(1L)).isTrue();
    }

}
