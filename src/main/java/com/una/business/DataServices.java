package com.una.business;

import com.una.business.dtoModels.*;
import com.una.data.dao.*;
import com.una.data.model.*;
import com.una.presentation.controller.dto.BranchDetailsInput;

import java.util.ArrayList;
import java.util.List;

public class DataServices {
    private static DAO dataDAO;
    public static List<EmployeeDetails> getEmployeesForTable() {
        dataDAO = new EmployeeDAO();
        List<Employee> persistedEmployees = dataDAO.getAllObjects();
        if(persistedEmployees.isEmpty()){
            return new ArrayList<>();
        }
        List<EmployeeDetails> employeesForTable = new ArrayList<>();
        for(Employee e : persistedEmployees){
            employeesForTable.add(new EmployeeDetails(e));
        }
        return employeesForTable;
    }
    public static List<BranchDetails> getBranchesForTable() {
        dataDAO = new BranchDAO();
        List<Branch> persistedBranches = dataDAO.getAllObjects();
        if(persistedBranches.isEmpty()){
            return new ArrayList<>();
        }
        List<BranchDetails> branchesForTable = new ArrayList<>();
        for(Branch b : persistedBranches){
            branchesForTable.add(new BranchDetails(b));
        }
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

    public static boolean addBranchExecution(BranchDetailsInput b) {
        Branch newBranch = BranchInputParser.toBranch(b);
        dataDAO = new BranchDAO();
        return dataDAO.add(newBranch);
    }

    public static boolean removeBranchExecution(BranchDetails b) {
        return false;
    }

    public static boolean editBranchExecution(BranchDetails b) {
        return false;
    }

    public static List<ProvinceDetails> getProvinces() {
        dataDAO = new ProvinceDAO();
        List<Province> persistedProvinces = dataDAO.getAllObjects();
        if(persistedProvinces.isEmpty()){
            return new ArrayList<>();
        }
        List<ProvinceDetails> provinces = new ArrayList<>();
        for(Province p : persistedProvinces){
            provinces.add(new ProvinceDetails(p));
        }
        return provinces;
    }

    public static List<CantonDetails> getCantonsByProvince(String nameProvince) {
        CantonDAO dataDAO = new CantonDAO();
        List<Canton> persistedCantons = dataDAO.getCantonsByNameProvince(nameProvince);

        List<CantonDetails> cantons = new ArrayList<>();
        for(Canton c : persistedCantons){
            cantons.add(new CantonDetails(c));
        }

        return cantons;
    }

    public static List<DistrictDetails> getDistrictsByCanton(String nameCanton) {
        DistrictDAO dataDAO = new DistrictDAO();
        List<District> persistedDistrict = dataDAO.getDistrictsByCantonName(nameCanton);

        List<DistrictDetails> districts = new ArrayList<>();
        for(District c : persistedDistrict){
            districts.add(new DistrictDetails(c));
        }

        return districts;
    }
}
