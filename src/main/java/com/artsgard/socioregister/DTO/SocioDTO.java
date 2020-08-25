package com.artsgard.socioregister.DTO;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author WillemDragstra
 */
@RequiredArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class SocioDTO implements Serializable {

    /**
     * 
     * @param id
     * @param username
     * @param firstName
     * @param lastName
     * @param email
     * @param active 
     */
    public SocioDTO(Long id, String username, String firstName, String lastName, String email, Boolean active) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    private Long id;
    
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private String username;
    
    private String firstName;
    
    private String lastName;
    
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private String email;
    
    private Timestamp registerDate;
   
    private Boolean active;
}
