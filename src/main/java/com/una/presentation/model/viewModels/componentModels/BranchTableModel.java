package com.una.presentation.model.viewModels.componentModels;


import com.una.business.dtoModels.BranchDetails;

import java.util.List;

public class BranchTableModel extends TableModelTemplate<BranchDetails> {


    public BranchTableModel(List<BranchDetails> list) {
        super(list, new String[]{"ID", "Referencia", "Direccion", "Porcentaje de Zona", "X", "Y"},
                new Class[]{Integer.class, String.class, String.class, Double.class, String.class, String.class});
    }

    //FALTA CAMBIAR ESTE METODO PARA ADECUARLO A ESTA CLASE
    @Override
    protected void setRows(List<BranchDetails> list) {
        if(list != null){
            rows = new Object[list.size()][columns.length];
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < columns.length; j++){
                    switch (j){
                        case 0:
                            rows[i][j] = list.get(i).getIdBranch();
                            break;
                        case 1:
                            rows[i][j] = list.get(i).getReference();
                            break;
                        case 2:
                            rows[i][j] = list.get(i).getAddress();
                            break;
                        case 3:
                            rows[i][j] = list.get(i).getZonePercentage();
                            break;
                        case 4:
                            rows[i][j] = list.get(i).getCoordX();
                            break;
                        case 5:
                            rows[i][j] = list.get(i).getCoordY();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
