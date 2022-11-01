package com.una.data.dao;

import com.una.business.dtoModels.CantonDetails;
import com.una.business.dtoModels.DistrictDetails;
import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Canton;
import com.una.data.model.District;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class DistrictDAO extends DAO<District> {

    @Override
    public List<District> getAllObjects() {
        List<District> districts;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<District> findDistricts = entityManager.createNamedQuery("District.findAll", District.class);
            districts = findDistricts.getResultList();
        }catch(Exception ex){
            districts = null;
            ex.printStackTrace();
        }
        entityManager.close();
        return districts;
    }

    @Override
    public District getSingleObject(Integer key) {
        District district;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<District> findDistrict = entityManager.createNamedQuery("District.findById", District.class);
            findDistrict.setParameter("idDistrict", District.class);
            district = findDistrict.getSingleResult();
        }catch(Exception ex){
            district = null;
            ex.printStackTrace();
        }
        entityManager.close();
        return district;
    }
    public static List<Branch> getBranches(District district){
        List<Branch> branches;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> findBranches = entityManager.createNamedQuery("Branch.findByDistrict", Branch.class);
            findBranches.setParameter("districtById", district);
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
    protected boolean hasDependencies(District obj) {
        if(!obj.getBranches().isEmpty()){
            return true;
        }
        return false;
    }
    public List<DistrictDetails> getDistrictsByCantonName(String nameCanton) {
        List<District> persistedDistrict;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<District> findDistricts = entityManager.createNamedQuery("District.findByCantonByName", District.class);
            findDistricts.setParameter("nameCanton", nameCanton);
            persistedDistrict = findDistricts.getResultList();
        }catch(Exception ex){
            persistedDistrict = new ArrayList<>();
            ex.printStackTrace();
        }
        entityManager.close();
        if(persistedDistrict.isEmpty()){
            return new ArrayList<>();
        }
        List<DistrictDetails> districts = new ArrayList<>();
        for(District c : persistedDistrict){
            districts.add(new DistrictDetails(c));
        }
        return districts;
    }
}
