package com.artsgard.socioregister.serviceimpl;

import com.artsgard.socioregister.service.MapperService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.socioregister.DTO.SocioDTO;
import com.artsgard.socioregister.model.SocioModel;
import com.artsgard.socioregister.repository.SocioRepository;
import com.artsgard.socioregister.service.SocioService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author WillemDragstra
 */
@Service
public class SocioServiceImpl implements SocioService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(SocioServiceImpl.class);

    @Autowired
    private MapperService mapperService;
    
    @Autowired
    private SocioRepository socioRepò;

    /**
     * 
     */
    public SocioServiceImpl() {
    }

    /**
     *
     * @return list of all socios
     */
    @Override
    public List<SocioDTO> findAllSocios() {
        List<SocioModel> socios = socioRepò.findAll();
        List<SocioDTO> list = new ArrayList();
        socios.forEach((sci) -> {
            list.add(mapperService.mapSocioModelToSocioDTO(sci));
        });
        return list;
    }

    /**
     *
     * @param id
     * @return single socio by id
     */
    @Override
    public SocioDTO findSocioById(Long id) {
        return mapperService.mapSocioModelToSocioDTO(socioRepò.findById(id));
    }

    /**
     *
     * @param username
     * @return single socio by username
     */
    @Override
    public SocioDTO findSocioByUsername(String username) {
        return mapperService.mapSocioModelToSocioDTO(socioRepò.findByUsername(username));
    }

    /**
     *
     * @param socioDTO
     * @return save single socio
     */
    @Override
    public SocioDTO saveSocio(SocioDTO socioDTO) {
        SocioModel socio = mapperService.mapSocioDTOToSocioModel(socioDTO);
        socio.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        return mapperService.mapSocioModelToSocioDTO( socioRepò.save(socio));
    }

    /**
     *
     * @param socioDTO
     * @param id
     * @return update single socio by id
     */
    @Override
    public SocioDTO updateSocio(SocioDTO socioDTO, Long id) {
        
        SocioDTO repoSocio = null;
        for (SocioModel value : socioRepò.findAll()) {
            if (Objects.equals(value.getId(), id)) {
                repoSocio = mapperService.mapSocioModelToSocioDTO(value);
            }
        }
        
        SocioDTO updatedDTO;
        if (repoSocio != null) {
            
            if (socioDTO.getUsername() == null) {
                socioDTO.setUsername(repoSocio.getUsername());
            }

            if (socioDTO.getFirstName() == null) {
                socioDTO.setFirstName(repoSocio.getFirstName());
            }

            if (socioDTO.getLastName() == null) {
                socioDTO.setLastName(repoSocio.getLastName());
            }

            if (socioDTO.getEmail() == null) {
                socioDTO.setEmail(repoSocio.getEmail());
            }
            socioDTO.setRegisterDate(repoSocio.getRegisterDate());
            
        } else {
            return null;
        }
        SocioModel socio = mapperService.mapSocioDTOToSocioModel(socioDTO);
        return mapperService.mapSocioModelToSocioDTO(socioRepò.update(socio, id));
    }

    /**
     *
     * Delete socio
     *
     * @param id
     */
    @Override
    public void deleteSocioById(Long id) {
        socioRepò.deleteSocioById(id);
    }

    /**
     *
     * @param id
     * @return boolean has socio
     */
    @Override
    public boolean hasSocioById(Long id) {
        return socioRepò.hasById(id);
    }

    /**
     *
     * @param id
     * @return boolean is socio active
     */
    @Override
    public boolean isSocioActiveById(Long id) {
        return socioRepò.isActiveById(id);
    }
}
