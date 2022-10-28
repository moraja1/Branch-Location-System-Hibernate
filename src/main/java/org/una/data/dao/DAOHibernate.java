package org.una.data.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;

public abstract class DAOHibernate<T> {
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    protected EntityManager getEntityManager(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        return entityManager = entityManagerFactory.createEntityManager();
    }

    protected boolean exists(Integer key){
        boolean flag = true;
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        try{
            entityTransaction.begin();


            entityTransaction.commit();
        }finally {
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
                flag = false;
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return flag;
    }
    public abstract boolean add(T obj);
    public abstract boolean erase(T obj);
    public abstract boolean edit(T obj);
    public abstract HashMap<String, T> getAllObjects();
    public abstract T getSingleObject(String key);
}
