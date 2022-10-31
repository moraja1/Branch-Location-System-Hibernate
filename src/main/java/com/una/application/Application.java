package com.una.application;

import com.una.data.dao.BranchDAO;
import com.una.data.dao.CantonDAO;
import com.una.data.dao.DAO;
import com.una.data.model.Canton;

import java.util.List;

public class Application {
    static CantonDAO dao = new CantonDAO();
    public static void main(String[] args) {
        System.out.println(dao.getSingleObject(3).getDistrictsByIdCanton().isEmpty());
    }
}
