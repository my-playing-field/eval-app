package com.spil.web.business.custom.impl;

import com.spil.web.business.custom.UserBO;
import com.spil.web.business.util.EntityDTOMapper;
import com.spil.web.dao.custom.UserDAO;
import com.spil.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserBOImpl implements UserBO {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EntityDTOMapper mapper;


    @Override
    public void saveUser(UserDTO dto) throws Exception {
        userDAO.save(mapper.getUser(dto));
    }

    @Override
    public void updateUser(UserDTO dto) throws Exception {
        userDAO.update(mapper.getUser(dto));
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        userDAO.delete(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllUsers() throws Exception {
        return mapper.getUserDTOs(userDAO.getAll());
    }

    @Override
    public UserDTO findUser(String id) throws  Exception{
        return mapper.getUserDTO(userDAO.get(id));
    }


}
