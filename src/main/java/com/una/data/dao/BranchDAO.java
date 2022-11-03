package com.una.data.dao;

import com.una.data.model.Branch;
import com.una.data.model.Employee;
import com.una.data.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchDAO extends DAO<Branch> {
    public BranchDAO(){
        super();
    }
    @Override
    public List<Branch> getAllObjects() {
        Transaction transaction = null;
        List<Branch> branches = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Branch> query = session.createNamedQuery("Branch.findAll", Branch.class);
            branches = query.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return branches;
    }

    @Override
    public Branch getSingleObject(Integer key) {
        Branch branch = new Branch();
        Transaction transaction = null;
        Session session;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            TypedQuery<Branch> findBranch = session.createNamedQuery("Branch.findById", Branch.class);
            findBranch.setParameter("idBranch", key);
            branch = findBranch.getSingleResult();
            branch.setEmployees(getEmployees(branch));
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return branch;
    }
    public static List<Employee> getEmployees(Branch branch){
        List<Employee> employees = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Employee> findEmployees = session.createNamedQuery("Employee.findByBranch", Employee.class);
            findEmployees.setParameter("branchById", branch);
            employees = findEmployees.getResultList();
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
    protected boolean hasDependencies(Branch obj) {
        if(!obj.getEmployees().isEmpty()){
            return true;
        }
        return false;
    }
}
