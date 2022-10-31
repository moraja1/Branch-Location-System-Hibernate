package com.una.business;

import com.una.business.dtoModels.BranchDetails;
import com.una.business.dtoModels.EmployeeDetails;
import com.una.data.dao.DAO;
import com.una.data.model.Branch;

import java.util.ArrayList;
import java.util.List;

public class DataServices {
    private static DAO dataDAO;
    public static List<EmployeeDetails> getEmployeesForTable() {
        return new ArrayList<>();
    }
    public static List<BranchDetails> getBranchesForTable() {
        return new ArrayList<>();
    }
    public static boolean addEmployeeExecution(EmployeeDetails e, BranchDetails b) {
        return false;
    }
    public static boolean removeEmployeeExecution(EmployeeDetails e) {
        return false;
    }
    public static BranchDetails getBranchInfo(Integer key) {
        return new BranchDetails(new Branch());
    }
    public static boolean editEmployeeExecution(EmployeeDetails e, BranchDetails b) {
        return false;
    }

    public static boolean addBranchExecution(BranchDetails b) {
        return false;
    }

    public static boolean removeBranchExecution(BranchDetails b) {
        return false;
    }

    public static boolean editBranchExecution(BranchDetails b) {
        return false;
    }
}
