package com.una.data.dao;

import com.una.data.model.Branch;
import com.una.data.model.District;
import com.una.data.util.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DistrictDAO extends DAO<District> {

    @Override
    public List<District> getAllObjects() {
        List<District> districts;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<District> findDistricts = session.createNamedQuery("District.findAll", District.class);
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

    @Override
    public District getSingleObject(Integer key) {
        District district;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<District> findDistrict = session.createNamedQuery("District.findById", District.class);
            findDistrict.setParameter("idDistrict", key);
            district = findDistrict.getSingleResult();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return district;
    }
    public static List<Branch> getBranches(District district){
        List<Branch> branches;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Branch> findBranches = session.createNamedQuery("Branch.findByDistrict", Branch.class);
            findBranches.setParameter("districtById", district);
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
    public List<District> getDistrictsByCantonName(String nameCanton) {
        List<District> persistedDistrict;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<District> findDistricts = session.createNamedQuery("District.findByCantonByName", District.class);
            findDistricts.setParameter("nameCanton", nameCanton);
            persistedDistrict = findDistricts.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return persistedDistrict;
    }
}
