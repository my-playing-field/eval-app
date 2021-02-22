package com.spil.web.api;


import com.spil.web.business.custom.UserBO;
import com.spil.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    @Autowired
    private UserBO userBO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() throws Exception {
        return userBO.findAllUsers();
    }

    @GetMapping(value = "/{id:U\\d{3}}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable String id) throws Exception {
        return userBO.findUser(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveUser(@RequestBody UserDTO user) throws Exception {
        userBO.saveUser(user);
        return user.getId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id:U\\d{3}}")
    public void deleteUser( @PathVariable String id) throws Exception{
        userBO.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id:U\\d{3}}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(String id,@RequestBody UserDTO user) throws Exception{
        user.setId(id);
        userBO.updateUser(user);
    }
}
