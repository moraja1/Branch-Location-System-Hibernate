package com.una.data.dao;

import com.una.data.jpa.jpaUtil;
import com.una.data.model.Branch;
import com.una.data.model.Employee;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeDAO extends DAO<Employee> {
    @Override
    public List<Employee> getAllObjects() {
        List<Employee> employees;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
            employees = query.getResultList();
        }catch(Exception ex){
            employees = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return employees;
    }

    @Override
    public Employee getSingleObject(Integer key) {
        Employee employee;
        entityManager = jpaUtil.getEntityManager();
        try{
            TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findById", Employee.class);
            employee = query.getSingleResult();
        }catch(Exception ex){
            employee = null;
            ex.printStackTrace();
        }
        entityManager.close();
        jpaUtil.shutDown();
        return employee;
    }

    @Override
    protected boolean hasDependencies(Employee obj) {
        return false;
    }
}
