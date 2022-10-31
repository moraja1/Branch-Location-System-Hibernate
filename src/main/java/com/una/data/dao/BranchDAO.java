package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BranchDAO extends DAO<Branch> {
    @Override
    public List<Branch> getAllObjects() {
        List<Branch> branches;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> query = entityManager.createNamedQuery("Branch.findAll", Branch.class);
            branches = query.getResultList();
        }catch(Exception ex){
            branches = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return branches;
    }

    @Override
    public Branch getSingleObject(Integer key) {
        Branch branch;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> query = entityManager.createNamedQuery("Branch.findById", Branch.class);
            branch = query.getSingleResult();
        }catch(Exception ex){
            branch = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return branch;
    }

    @Override
    protected boolean hasDependencies() {
        return false;
    }
}
