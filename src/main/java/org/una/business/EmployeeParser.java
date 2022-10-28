package org.una.business;

import org.una.data.repository.Branch;
import org.una.data.repository.Employee;
import org.una.data.dao.modelsDAO.BDAO;
import org.una.presentation.model.viewModels.EmployeeInfo;

public class EmployeeParser {
    public static Employee toEmployee(EmployeeInfo e){
        //Create Employee
        String id = e.getId();
        String name = e.getName();
        String phone_number = e.getPhone_number();
        double base_salary = e.getBase_salary();
        String reference = e.getBranch_reference();

        BDAO dataDAO = new BDAO();
        Branch branch = dataDAO.getBranchByReference(reference);
        if(branch != null){
            return new Employee(id, name, phone_number, base_salary, branch);
        }
        return null;
    }
}
