package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Province;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProvinceDAO extends DAO<Province>{
    @Override
    public List<Province> getAllObjects() {
        List<Province> provinces;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Province> query = entityManager.createNamedQuery("Province.findAll", Province.class);
            provinces = query.getResultList();
        }catch(Exception ex){
            provinces = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return provinces;
    }

    @Override
    public Province getSingleObject(Integer key) {
        Province province;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Province> query = entityManager.createNamedQuery("Province.findById", Province.class);
            province = query.getSingleResult();
        }catch(Exception ex){
            province = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return province;
    }

    @Override
    protected boolean hasDependencies() {
        return false;
    }
}
