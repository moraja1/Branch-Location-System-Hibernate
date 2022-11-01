package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Canton;
import com.una.data.model.Province;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO extends DAO<Province>{
    @Override
    public List<Province> getAllObjects() {
        List<Province> provinces;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Province> query = entityManager.createNamedQuery("Province.findAll", Province.class);
            provinces = query.getResultList();
            for(Province p : provinces){
                p.setCantons(getCantons(p));
            }
        }catch(Exception ex){
            provinces = new ArrayList<>();
            ex.printStackTrace();
        }
        entityManager.close();
        return provinces;
    }

    @Override
    public Province getSingleObject(Integer key) {
        Province province;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Province> findProvince = entityManager.createNamedQuery("Province.findById", Province.class);
            findProvince.setParameter("idProvince", key);
            province = findProvince.getSingleResult();
            province.setCantons(getCantons(province));
        }catch(Exception ex){
            province = null;
            ex.printStackTrace();
        }
        entityManager.close();
        return province;
    }
    public static List<Canton> getCantons(Province province){
        List<Canton> cantons;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Canton> findCantons = entityManager.createNamedQuery("Canton.findByProvince", Canton.class);
            findCantons.setParameter("provinceById", province);
            cantons = findCantons.getResultList();
        }catch(Exception ex){
            cantons = new ArrayList<>();
            ex.printStackTrace();
        }
        return cantons;
    }
    public static List<Branch> getBranches(Province province){
        List<Branch> branches;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> findBranches = entityManager.createNamedQuery("Branch.findByProvince", Branch.class);
            findBranches.setParameter("provinceById", province);
            branches = findBranches.getResultList();
        }catch(Exception ex){
            branches = new ArrayList<>();
            ex.printStackTrace();
        }
        entityManager.close();
        return branches;
    }

    @Override
    protected boolean hasDependencies(Province obj) {
        if(!obj.getCantons().isEmpty() &&
                !obj.getBranches().isEmpty()){
            return true;
        }
        return false;
    }
}
