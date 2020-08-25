package com.artsgard.socioregister.repository;

import com.artsgard.socioregister.mock.SocioMock;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.service.MapperService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WillemDragstra
 */
@Repository
public class SocioRepository {
    
    private final List<SocioModel> socios;
    
    @Autowired
    private MapperService mapperService;
    
    /**
     * Constructor innititates the socios mock
     */
    public SocioRepository() {
        this.socios = SocioMock.generateSocios();
    }
    
    /**
     * 
     * @return all socios
     */
    public List<SocioModel> findAll() {
        return socios;
    }

    /**
     * 
     * @param id
     * @return single socio by id
     */
    public SocioModel findById(Long id) {
        SocioModel socio = null;
        for (SocioModel value: this.socios) {
            if (Objects.equals(value.getId(), id)) {
                socio = value;
            }
        }
        return socio; 
    }

    /**
     * 
     * @param username
     * @return socio by username
     */
    public SocioModel findByUsername(String username) {
        SocioModel socio = null;
        for (SocioModel value: this.socios) {
            if (Objects.equals(value.getUsername(), username)) {
                socio = value;
            }
        }
        return socio;
    }

    /**
     * 
     * @param socio
     * @return save a socio
     */
    public SocioModel save(SocioModel socio) {
        socios.add(socio);
        return socio; 
    }

    /**
     * 
     * @param socio
     * @param id
     * @return update an exsisting socio
     */
    public SocioModel update(SocioModel socio, Long id) {
        
        for (SocioModel value: this.socios) {
            if (Objects.equals(value.getId(), id)) {
                this.socios.remove(value);
                this.socios.add(socio);
                break;
            }
        }
        return socio; 
    }
  
    /**
     * 
     * @param id 
     * Delete a single socio by id
     */
    public void deleteSocioById(Long id) {
        SocioModel socio = null;
        for (SocioModel value: this.socios) {
            if (Objects.equals(value.getId(), id)) {
                socios.remove(value);
                break;
            }
        }
    }
    
    /**
     * 
     * @param id
     * @return boolean has socio by id
     */
    public boolean hasById(Long id) {
        boolean hasSocio = false;
        for (SocioModel value : findAll()) {
            if (Objects.equals(value.getId(), id)) {
                hasSocio = true;
                break;
            }
        }
        return hasSocio;
    }

    /**
     * 
     * @param id
     * @return boolean is active by id
     */
    public boolean isActiveById(Long id) {
        boolean socioActive = false;
        for (SocioModel value : findAll()) {
            if (Objects.equals(value.getId(), id)) {
                socioActive = value.getActive();
                break;
            }
        }
        return socioActive;
    }
}
