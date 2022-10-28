package org.una.data.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;

public abstract class DAOHibernate<T> {
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    protected void turnOn(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }
    protected void shutDown(){
        entityManager.close();
        entityManagerFactory.close();
    }
    protected abstract boolean exists(Integer key);
    public abstract boolean add(T obj);
    public abstract boolean erase(T obj);
    public abstract boolean edit(T obj);
    public abstract HashMap<String, T> getAllObjects();
    public abstract T getSingleObject(Integer key);
}
