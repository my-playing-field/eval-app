package com.spil.web.dao.custom.impl;


import com.spil.web.dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryDAOImpl implements QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

}
