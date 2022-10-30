package com.una.application;

import com.una.data.model.Canton;
import com.una.data.model.Province;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "SISE";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }

    public static void shutDown(){
        emFactoryObj.close();
    }

    public static void main(String[] args) {
        EntityManager entityMgr = getEntityManager();
        entityMgr.getTransaction().begin();
        Canton province = entityMgr.find(Canton.class, 45);
        entityMgr.getTransaction().commit();

        entityMgr.close();
        shutDown();

        System.out.println(province.getNameCanton());
    }
}
