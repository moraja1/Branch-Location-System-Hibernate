package com.una.data.dao;

import com.una.data.model.EntityParent;

import java.util.List;

public abstract class DAO<T extends EntityParent>{

    protected boolean exists(String key){
        return false;
    }
    public boolean add(T obj){
        return false;
    }
    public boolean erase(T obj){
        return false;
    }
    public boolean edit(T obj){
        return false;
    }

    public abstract List<T> getAllObjects();
    public abstract T getSingleObject(String key);
    protected abstract boolean hasDependencies();
}
