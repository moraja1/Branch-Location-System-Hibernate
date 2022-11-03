package com.una.data.dao;

import com.una.business.dtoModels.CantonDetails;
import com.una.data.model.Branch;
import com.una.data.model.Canton;
import com.una.data.model.District;
import com.una.data.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CantonDAO extends DAO<Canton> {
    @Override
    public List<Canton> getAllObjects() {
        List<Canton> cantons;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Canton> query = session.createNamedQuery("Canton.findAll", Canton.class);
            cantons = query.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return cantons;
    }

    @Override
    public Canton getSingleObject(Integer key) {
        Canton canton;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Canton> findCanton = session.createNamedQuery("Canton.findById", Canton.class);
            findCanton.setParameter("idCanton", key);
            canton = findCanton.getSingleResult();
            canton.setDistricts(getDistritcts(canton));
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return canton;
    }
    public List<Canton> getCantonsByNameProvince(String nameProvince){
        List<Canton> persistedCantons;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Canton> findCantons = session.createNamedQuery("Canton.findByProvinceByName", Canton.class);
            findCantons.setParameter("nameProvince", nameProvince);
            persistedCantons = findCantons.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return persistedCantons;
    }
    public static List<District> getDistritcts(Canton canton){
        List<District> districts;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<District> findDistricts = session.createNamedQuery("District.findByCanton", District.class);
            findDistricts.setParameter("cantonById", canton);
            districts = findDistricts.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return districts;
    }
    public static List<Branch> getBranches(Canton canton) {
        List<Branch> branches;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Branch> findBranches = session.createNamedQuery("Branch.findByCanton", Branch.class);
            findBranches.setParameter("cantonById", canton);
            branches = findBranches.getResultList();
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
    protected boolean hasDependencies(Canton obj) {
        if(!obj.getDistricts().isEmpty() &&
                !obj.getBranches().isEmpty()){
            return true;
        }
        return false;
    }
}
