package com.una.presentation.model.viewModels.componentModels;

import com.una.business.dtoModels.EmployeeDetails;

import java.util.List;

public class EmployeeTableModel extends TableModelTemplate<EmployeeDetails> {

    public EmployeeTableModel(List<EmployeeDetails> list){
        super(list, new String[]{"ID", "Nombre", "Numero de Telefono", "Salario Base", "Referencia de Sucursal", "Porcentaje por Zona", "Salario Total"},
                new Class[]{String.class, String.class, String.class, Double.class, String.class, Double.class, Double.class});
    }
    @Override
    protected void setRows(List<EmployeeDetails> list) {
        if(list != null){
            rows = new Object[list.size()][columns.length];
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < columns.length; j++){
                    switch (j){
                        case 0:
                            rows[i][j] = list.get(i).getIdEmployee();
                            break;
                        case 1:
                            rows[i][j] = list.get(i).getNameEmployee();
                            break;
                        case 2:
                            rows[i][j] = list.get(i).getPhoneNumber();
                            break;
                        case 3:
                            rows[i][j] = list.get(i).getBaseSalary();
                            break;
                        case 4:
                            rows[i][j] = list.get(i).getBranchReference();
                            break;
                        case 5:
                            rows[i][j] = list.get(i).getZonePercentage();
                            break;
                        case 6:
                            rows[i][j] = list.get(i).getTotalSalary();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
