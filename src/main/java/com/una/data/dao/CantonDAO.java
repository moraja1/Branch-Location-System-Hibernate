package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Canton;
import com.una.data.model.District;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class CantonDAO extends DAO<Canton> {
    @Override
    public List<Canton> getAllObjects() {
        List<Canton> cantons;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Canton> query = entityManager.createNamedQuery("Canton.findAll", Canton.class);
            cantons = query.getResultList();
            for(Canton c : cantons){
                c.setDistricts(getDistritcts(c));
            }
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
            canton.setDistricts(getDistritcts(canton));
        }catch(Exception ex){
            canton = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return canton;
    }
    public static List<District> getDistritcts(Canton canton){
        List<District> districts;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<District> findDistricts = entityManager.createNamedQuery("District.findByCanton", District.class);
            findDistricts.setParameter("cantonById", canton);
            districts = findDistricts.getResultList();
        }catch(Exception ex){
            districts = new ArrayList<>();
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return districts;
    }
    public static List<Branch> getBranches(Canton canton) {
        List<Branch> branches;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> findBranches = entityManager.createNamedQuery("Branch.findByCanton", Branch.class);
            findBranches.setParameter("cantonById", canton);
            branches = findBranches.getResultList();
        }catch(Exception ex){
            branches = new ArrayList<>();
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return branches;
    }
    @Override
    protected boolean hasDependencies(Canton obj) {
        if(!obj.getDistricts().isEmpty() &&
                !obj.getBranches().isEmpty()){
            return true;
        }
        return false;
    }
}
