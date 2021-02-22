package com.spil.web.business.custom;

import com.spil.web.business.SuperBO;
import com.spil.web.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    void saveUser(UserDTO dto) throws Exception;

    void updateUser(UserDTO dto) throws Exception;

    void deleteUser(String userId) throws Exception;

    List<UserDTO> findAllUsers() throws Exception;

    UserDTO findUser(String userId) throws Exception;
}
