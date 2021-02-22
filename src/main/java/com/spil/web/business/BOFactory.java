package com.spil.web.business;

import com.spil.web.business.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null)? (boFactory = new BOFactory()): boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){

            case USER:
                return (T)  new UserBOImpl();
            default:
                return null;
        }
    }
}
