package com.una.application;

import com.una.data.dao.ProvinceDAO;
import com.una.data.jpa.jpaUtil;
import com.una.presentation.controller.flowController.MainController;

public class Application {
    public static void main(String[] args) {
        MainController.initFlow();
    }
}
