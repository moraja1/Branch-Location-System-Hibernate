package com.una.data.dao;

import com.una.data.model.Employee;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends DAO<Employee> {
    @Override
    public List<Employee> getAllObjects() {
        Transaction transaction = null;
        List<Employee> employees = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Employee> query = session.createNamedQuery("Employee.findAll", Employee.class);
            employees = query.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return employees;
    }

    @Override
    public Employee getSingleObject(Integer key) {
        Transaction transaction = null;
        Employee employee = new Employee();
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Employee> query = session.createNamedQuery("Employee.findById", Employee.class);
            query.setParameter("idEmployee", key);
            employee = query.getSingleResult();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return employee;
    }
}
