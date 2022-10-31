package com.una.application;

import com.una.data.dao.DAO;
import com.una.data.model.Canton;

import java.util.List;

public class Application {
    static DAO<Canton> dao = new DAO<Canton>() {
        @Override
        public List<Canton> getAllObjects() {
            return null;
        }

        @Override
        public Canton getSingleObject(Integer key) {
            return null;
        }

        @Override
        protected boolean hasDependencies() {
            return false;
        }
    };
    public static void main(String[] args) {
        System.out.println(dao.exists(5));
    }
}
