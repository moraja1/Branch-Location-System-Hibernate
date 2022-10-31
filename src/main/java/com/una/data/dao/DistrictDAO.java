package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.District;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DistrictDAO extends DAO<District> {

    @Override
    public List<District> getAllObjects() {
        List<District> districts;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<District> query = entityManager.createNamedQuery("District.findAll", District.class);
            districts = query.getResultList();
        }catch(Exception ex){
            districts = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return districts;
    }

    @Override
    public District getSingleObject(Integer key) {
        District district;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<District> query = entityManager.createNamedQuery("District.findById", District.class);
            district = query.getSingleResult();
        }catch(Exception ex){
            district = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return district;
    }

    @Override
    protected boolean hasDependencies() {
        return false;
    }
}
