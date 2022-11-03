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
        return branchesForTable;
    }
    public static boolean addEmployeeExecution(EmployeeDetails e, BranchDetails b) {
        return false;
    }
    public static boolean removeEmployeeExecution(EmployeeDetails e) {
        return false;
    }
    public static BranchDetails getBranchInfo(Integer key) {
        dataDAO = new BranchDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(key);
        if(branch == null){
            return null;
        }
        return new BranchDetails(branch);
    }
    public static boolean editEmployeeExecution(EmployeeDetails e, BranchDetails b) {
        return false;
    }

    public static boolean addBranchExecution(BranchDetailsInput b) {
        Branch newBranch = BranchInputParser.toBranch(b);
        return dataDAO.add(newBranch);
    }

    public static boolean removeBranchExecution(BranchDetails b) {
        dataDAO = new BranchDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(b.getIdBranch());
        return dataDAO.erase(branch);
    }

    public static boolean editBranchExecution(BranchDetailsInput b) {
        Branch newBranch = BranchInputParser.toBranch(b);
        return dataDAO.edit(newBranch);
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

    public static ReferenceDetails getReferenceDetails(Integer key) {
        dataDAO = new BranchDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(key);
        if(branch != null){
            District district = branch.getDistrict();
            return new ReferenceDetails(district);
        }
        return null;
    }
}
