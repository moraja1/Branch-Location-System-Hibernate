package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Canton;
import com.una.data.model.District;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CantonDAO extends DAO<Canton> {
    @Override
    public List<Canton> getAllObjects() {
        List<Canton> cantons;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Canton> query = entityManager.createNamedQuery("Canton.findAll", Canton.class);
            cantons = query.getResultList();
        }catch(Exception ex){
            cantons = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return cantons;
    }

    @Override
    public Canton getSingleObject(Integer key) {
        Canton canton;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Canton> findCanton = entityManager.createNamedQuery("Canton.findById", Canton.class);
            findCanton.setParameter("idCanton", key);
            canton = findCanton.getSingleResult();
            TypedQuery<District> findDistricts = entityManager.createNamedQuery("District.findByCantonById", District.class);
            findDistricts.setParameter("cantonById", canton);
            canton.setDistrictsByIdCanton(findDistricts.getResultList());
        }catch(Exception ex){
            canton = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return canton;
    }

    @Override
    protected boolean hasDependencies() {
        return false;
    }
}
