package com.una.business;

import com.una.data.dao.DAO;
import com.una.data.dao.DistrictDAO;
import com.una.data.model.Branch;
import com.una.data.model.District;
import com.una.presentation.controller.dto.BranchDetailsInput;

public class BranchInputParser {
    private static DAO dataDAO;
    public static Branch toBranch(BranchDetailsInput b) {
        Branch branch = new Branch();
        branch.setIdBranch(b.getIdBranch());
        branch.setAddress(b.getAddress());
        branch.setCoordX(b.getCoordX());
        branch.setCoordY(b.getCoordY());

        dataDAO = new DistrictDAO();
        District district = (District) dataDAO.getSingleObject(b.getDistrictId());
        if(district != null){
            branch.setDistrict(district);
            branch.setCanton(district.getCanton());
            branch.setProvince(district.getCanton().getProvince());
            return branch;
        }
        return null;
    }
}
