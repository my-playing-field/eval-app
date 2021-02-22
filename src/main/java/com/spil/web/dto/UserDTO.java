package com.spil.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*@Data
@AllArgsConstructor
@NoArgsConstructor*/
public class UserDTO implements Serializable {
    private String id;
    private String name;
    private String address;


    public UserDTO() {
    }

    public UserDTO(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
