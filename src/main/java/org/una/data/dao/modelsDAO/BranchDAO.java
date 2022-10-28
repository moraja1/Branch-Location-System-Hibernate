package org.una.data.dao.modelsDAO;

import org.una.data.dao.DAOHibernate;
import org.una.data.model.BranchEntity;
import org.una.data.model.EmployeeEntity;

import java.util.Collection;
import java.util.HashMap;

public class BranchDAO extends DAOHibernate<BranchEntity> {
    @Override
    protected boolean exists(Integer key) {
        boolean flag = true;
        turnOn();
        BranchEntity branch = entityManager.find(BranchEntity.class, key);
        if(branch == null){
            flag = false;
        }
        shutDown();
        return flag;
    }

    @Override
    public boolean add(BranchEntity obj) {

        return false;
    }

    @Override
    public boolean erase(BranchEntity obj) {
        boolean flag = true;
        BranchEntity persistedBranch = getSingleObject(obj.getIdBranch());
        if(persistedBranch != null){
            Collection<EmployeeEntity> persistedEmployees = persistedBranch.getEmployees();
            if(!persistedEmployees.isEmpty()){
                flag = false;
            }else{
                turnOn();
                entityManager.getTransaction().begin();
                entityManager.remove(obj);
                entityManager.getTransaction().commit();
                shutDown();
            }
        }
        return flag;
    }

    @Override
    public boolean edit(BranchEntity obj) {
        boolean flag = true;
        if(exists(obj.getIdBranch())){
            turnOn();
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            shutDown();
        }else{
            flag = false;
        }
        return flag;
    }

    @Override
    public HashMap<String, BranchEntity> getAllObjects() {
        return null;
    }

    @Override
    public BranchEntity getSingleObject(Integer key) {
        return null;
    }
}
