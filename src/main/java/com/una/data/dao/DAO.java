package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.EntityParent;
import jakarta.persistence.EntityManager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DAO<T extends EntityParent>{
    private Class<T> typeOfT;
    @SuppressWarnings("unchecked")
    public DAO(){
        this.typeOfT = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public boolean exists(Integer key){
        boolean flag = true;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            if(entityManager.find(typeOfT, key) == null){
                flag = false;
            }
        }catch(Exception ex){
            flag = false;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return flag;
    }
    public boolean add(T obj){
        boolean flag = true;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            if(exists(obj.getId())){
                flag = false;
            }else{
                entityManager.getTransaction().begin();
                entityManager.persist(obj);
                entityManager.getTransaction().commit();
            }
        }catch(Exception ex){
            flag = false;
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return flag;
    }
    public boolean erase(T obj){
        boolean flag = true;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            if(exists(obj.getId()) && !hasDependencies()){
                entityManager.getTransaction().begin();
                entityManager.remove(obj);
                entityManager.getTransaction().commit();
            }else{
                flag = false;
            }
        }catch(Exception ex){
            flag = false;
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return flag;
    }
    public boolean edit(T obj){
        boolean flag = true;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            if(exists(obj.getId())){
                entityManager.getTransaction().begin();
                entityManager.merge(obj);
                entityManager.getTransaction().commit();
            }else{
                flag = false;
            }
        }catch(Exception ex){
            flag = false;
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return flag;
    }
    public abstract List<T> getAllObjects();
    public abstract T getSingleObject(Integer key);
    protected abstract boolean hasDependencies();
}
