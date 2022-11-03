package com.una.data.dao;

import com.una.data.model.Branch;
import com.una.data.model.Canton;
import com.una.data.model.Province;
import com.una.data.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO extends DAO<Province>{
    @Override
    public List<Province> getAllObjects() {
        List<Province> provinces;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Province> query = session.createNamedQuery("Province.findAll", Province.class);
            provinces = query.getResultList();
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return provinces;
    }

    @Override
    public Province getSingleObject(Integer key) {
        Province province;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Province> findProvince = session.createNamedQuery("Province.findById", Province.class);
            findProvince.setParameter("idProvince", key);
            province = findProvince.getSingleResult();
            province.setCantons(getCantons(province));
            transaction.commit();
        }catch(Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return null;
        }
        return province;
    }
    public static List<Canton> getCantons(Province province){
        List<Canton> cantons;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Canton> findCantons = session.createNamedQuery("Canton.findByProvince", Canton.class);
            findCantons.setParameter("provinceById", province);
            cantons = findCantons.getResultList();
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
    public static List<Branch> getBranches(Province province){
        List<Branch> branches;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Branch> findBranches = session.createNamedQuery("Branch.findByProvince", Branch.class);
            findBranches.setParameter("provinceById", province);
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
    protected boolean hasDependencies(Province obj) {
        if(!obj.getCantons().isEmpty() &&
                !obj.getBranches().isEmpty()){
            return true;
        }
        return false;
    }
}
