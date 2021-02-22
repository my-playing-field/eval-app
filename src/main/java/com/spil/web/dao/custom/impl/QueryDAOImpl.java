package com.spil.web.dao.custom.impl;


import com.spil.web.dao.custom.QueryDAO;
import org.hibernate.Session;

public class QueryDAOImpl implements QueryDAO {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
    }
}
