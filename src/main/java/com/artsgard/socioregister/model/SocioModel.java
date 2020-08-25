package com.artsgard.socioregister.model;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author WillemDragstra
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SocioModel implements Serializable {

    /**
     * 
     * @param id
     * @param username
     * @param firstName
     * @param lastName
     * @param email
     * @param active 
     */
    public SocioModel(Long id, String username, String firstName, String lastName, String email, Boolean active) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp registerDate;
    private Boolean active;
}
