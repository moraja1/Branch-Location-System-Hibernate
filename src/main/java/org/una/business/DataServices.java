package org.una.business;

import org.una.data.repository.Branch;
import org.una.data.repository.Coordinates;
import org.una.data.repository.Employee;
import org.una.data.dao.DAO;
import org.una.data.dao.modelsDAO.BDAO;
import org.una.data.dao.modelsDAO.EDAO;
import org.una.presentation.model.viewModels.BranchInfo;
import org.una.presentation.model.viewModels.EmployeeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataServices {
    private static DAO dataDAO;
    public static List<EmployeeInfo> getEmployeesForTable() {
        dataDAO = new EDAO();
        HashMap<String, Employee> dataEmployees = dataDAO.getAllObjects();
        List<Employee> dataEmployeesList = dataEmployees.values().stream().toList();
        dataDAO = new BDAO();
        HashMap<String, Branch> dataBranches = dataDAO.getAllObjects();

        List<EmployeeInfo> employees = new ArrayList<>();

        for (Employee emp : dataEmployeesList){
            Branch branch = dataBranches.get(emp.getBranch().getId());

            String id = emp.getId();
            String name = emp.getName();
            String phone_number = emp.getPhone_number();
            double base_salary = emp.getBase_salary();
            String branch_reference;
            double zoning_percentage;
            double total_salary;
            if(branch == null){
                branch_reference = "null";
                zoning_percentage = 0.0;
                total_salary = 0.0;
            }else{
                branch_reference = branch.getReference();
                zoning_percentage = branch.getZoning_percentage();
                total_salary = (base_salary * zoning_percentage) - base_salary;
            }
            employees.add(new EmployeeInfo(id, name, phone_number, base_salary, branch_reference,
                    zoning_percentage, total_salary));
        }
        return employees;
    }
    public static List<BranchInfo> getBranchesForTable() {
        dataDAO = new BDAO();
        HashMap<String, Branch> dataBranches = dataDAO.getAllObjects();
        List<Branch> dataBranchesList = dataBranches.values().stream().toList();

        HashMap<String, Coordinates> dataCoords = dataDAO.getAllObjects();

        List<BranchInfo> branches = new ArrayList<>();
        for(Branch branch : dataBranchesList){
            BranchInfo bInfo = BranchParser.toBranchInfo(branch);
            if(bInfo != null){
                branches.add(bInfo);
            }
        }
        return branches;
    }
    public static boolean addEmployeeExecution(EmployeeInfo e, BranchInfo b) {
        //Get Branch
        dataDAO = new BDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(b.getId());
        if(branch == null){
            return false;
        }

        Employee employee = EmployeeParser.toEmployee(e);
        branch.getEmployees().add(employee);

        if(dataDAO.edit(branch)){
            dataDAO = new EDAO();
            return dataDAO.add(employee);
        }
        return false;
    }
    public static boolean removeEmployeeExecution(EmployeeInfo e) {
        Employee employee = EmployeeParser.toEmployee(e);
        if(employee != null){
            dataDAO = new EDAO();
            dataDAO.erase(employee);

            Branch branch = employee.getBranch();
            List<Employee> employeeList = branch.getEmployees();
            Employee eCopy = null;
            for (Employee a : employeeList){
                if(a.getId().equals(employee.getId())){
                    eCopy = a;
                }
            }
            if (eCopy != null) {
                employeeList.remove(eCopy);
            }
            branch.setEmployees(employeeList);

            dataDAO = new BDAO();
            return dataDAO.edit(branch);

        }
        return false;
    }
    public static BranchInfo getBranchInfo(String key) {
        BDAO dataDAO = new BDAO();
        Branch branch = dataDAO.getSingleObject(key);
        if(branch == null){
            branch = dataDAO.getBranchByReference(key);
            if (branch == null){
                return null;
            }
        }
        BranchInfo b = BranchParser.toBranchInfo(branch);
        return b;
    }
    public static boolean editEmployeeExecution(EmployeeInfo e, BranchInfo b) {
        //Get Branch
        dataDAO = new BDAO();
        Branch newBranch = (Branch) dataDAO.getSingleObject(b.getId());
        Employee newEmployee = EmployeeParser.toEmployee(e);

        dataDAO = new EDAO();
        Employee oldEmployee = (Employee) dataDAO.getSingleObject(e.getId());

        List<Employee> employeesOnNewBranch = newBranch.getEmployees();
        boolean containsEmployee = false;
        for(Employee em : employeesOnNewBranch){
            if(em.getId().equals(newEmployee.getId())) {
                containsEmployee = true;
            }
        }
        if(!containsEmployee) {
            newBranch.getEmployees().add(newEmployee);
            BDAO dataDAO = new BDAO();
            dataDAO.edit(newBranch);

            Branch oldBranch = dataDAO.getSingleObject(oldEmployee.getBranch().getId());
            if(oldBranch != null){
                List<Employee> employeesOnOldBranch = oldBranch.getEmployees();
                Employee employeeEraser = null;
                for(Employee em : employeesOnOldBranch){
                    if(em.getId().equals(newEmployee.getId())) {
                        employeeEraser = em;
                    }
                }
                if(employeeEraser != null) {
                    oldBranch.getEmployees().remove(employeeEraser);
                    dataDAO.edit(oldBranch);
                }
            }
        }
        dataDAO = new EDAO();
        return dataDAO.edit(newEmployee);
    }

    public static boolean addBranchExecution(BranchInfo b) {
        Branch newBranch = BranchParser.toBranch(b);
        Coordinates coordinates = newBranch.getCoords();

        dataDAO = new BDAO();
        if(dataDAO.add(newBranch)){
            return dataDAO.add(coordinates);
        }
        return false;
    }

    public static boolean removeBranchExecution(BranchInfo b) {
        dataDAO = new BDAO();
        Branch branch = (Branch) dataDAO.getSingleObject(b.getId());
        dataDAO = new EDAO();
        List<Employee> employeesOfBranch = new ArrayList<>();
        for(Employee em : branch.getEmployees()){
            Employee emp = (Employee) dataDAO.getSingleObject(em.getId());
            emp.setBranch(null);
            employeesOfBranch.add(emp);
        }
        if(branch != null){
            Coordinates coordinates = branch.getCoords();
            coordinates = (Coordinates) dataDAO.getSingleObject(coordinates.getId());
            dataDAO = new BDAO();
            if(dataDAO.erase(branch)){
                dataDAO = new EDAO();
                for(Employee em : employeesOfBranch){
                    dataDAO.edit(em);
                }
                return dataDAO.erase(coordinates);
            }
        }
        return false;
    }

    public static boolean editBranchExecution(BranchInfo b) {
        Branch newBranch = BranchParser.toBranch(b);
        Coordinates coordinates = newBranch.getCoords();

        dataDAO = new BDAO();
        if(dataDAO.edit(newBranch)){
            return dataDAO.edit(coordinates);
        }
        return false;
    }
}
