package com.spil.web.business.custom.impl;

import com.spil.web.business.custom.UserBO;
import com.spil.web.business.util.EntityDTOMapper;
import com.spil.web.dao.DAOFactory;
import com.spil.web.dao.DAOTypes;
import com.spil.web.dao.custom.UserDAO;
import com.spil.web.dto.UserDTO;
import org.hibernate.Session;

import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO;
    private final EntityDTOMapper mapper = EntityDTOMapper.instance;
    private Session session;

    public UserBOImpl(){
        userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        userDAO.setSession(session);
    }

    @Override
    public void saveUser(UserDTO dto) throws Exception {
        session.beginTransaction();
        userDAO.save(mapper.getUser(dto));
        session.getTransaction().commit();
    }

    @Override
    public void updateUser(UserDTO dto) throws Exception {
        session.beginTransaction();
        userDAO.update(mapper.getUser(dto));
        session.getTransaction().commit();
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        session.beginTransaction();
        userDAO.delete(userId);
        session.getTransaction().commit();
    }

    @Override
    public List<UserDTO> findAllUsers() throws Exception {
        session.beginTransaction();
        List<UserDTO> collect = mapper.getUserDTOs(userDAO.getAll());
        session.getTransaction().commit();
        return collect;
    }

    @Override
    public UserDTO findUser(String userId) throws Exception {
        session.beginTransaction();
        UserDTO userDTO = mapper.getUserDTO(userDAO.get(userId));
        session.getTransaction().commit();
        return userDTO;
    }


}
