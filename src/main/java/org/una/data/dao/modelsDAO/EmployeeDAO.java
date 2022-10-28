package org.una.data.dao.modelsDAO;

import org.una.data.dao.DAOHibernate;
import org.una.data.model.EmployeeEntity;

import java.util.HashMap;

public class EmployeeDAO extends DAOHibernate<EmployeeEntity> {
    @Override
    protected boolean exists(Integer key) {
        return false;
    }

    @Override
    public boolean add(EmployeeEntity obj) {
        return false;
    }

    @Override
    public boolean erase(EmployeeEntity obj) {
        return false;
    }

    @Override
    public boolean edit(EmployeeEntity obj) {
        return false;
    }

    @Override
    public HashMap<String, EmployeeEntity> getAllObjects() {
        return null;
    }

    @Override
    public EmployeeEntity getSingleObject(Integer key) {
        return null;
    }
}
