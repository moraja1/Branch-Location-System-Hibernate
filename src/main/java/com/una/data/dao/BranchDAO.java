package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends DAO<Branch> {
    public BranchDAO(){
        super();
    }
    @Override
    public List<Branch> getAllObjects() {
        List<Branch> branches;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> query = entityManager.createNamedQuery("Branch.findAll", Branch.class);
            branches = query.getResultList();
            for(Branch b: branches){
                b.setEmployees(getEmployees(b));
            }
        }catch(Exception ex){
            branches = new ArrayList<>();;
            ex.printStackTrace();
        }
        entityManager.close();
        return branches;
    }

    @Override
    public Branch getSingleObject(Integer key) {
        Branch branch;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Branch> findBranch = entityManager.createNamedQuery("Branch.findById", Branch.class);
            findBranch.setParameter("idBranch", key);
            branch = findBranch.getSingleResult();
            branch.setEmployees(getEmployees(branch));
        }catch(Exception ex){
            branch = null;
            ex.printStackTrace();
        }
        entityManager.close();
        return branch;
    }
    public static List<Employee> getEmployees(Branch branch){
        List<Employee> employees;
        EntityManager entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Employee> findEmployees = entityManager.createNamedQuery("Employee.findByBranch", Employee.class);
            findEmployees.setParameter("branchById", branch);
            employees = findEmployees.getResultList();
        }catch(Exception ex){
            employees = new ArrayList<>();
            ex.printStackTrace();
        }
        entityManager.close();
        return employees;
    }
    @Override
    protected boolean hasDependencies(Branch obj) {
        if(!obj.getEmployees().isEmpty()){
            return true;
        }
        return false;
    }
}
