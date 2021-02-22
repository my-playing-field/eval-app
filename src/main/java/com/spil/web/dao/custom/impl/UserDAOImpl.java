package com.spil.web.dao.custom.impl;

import com.spil.web.dao.CrudDAOImpl;
import com.spil.web.dao.custom.UserDAO;
import com.spil.web.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends CrudDAOImpl<User, String> implements UserDAO {
}
