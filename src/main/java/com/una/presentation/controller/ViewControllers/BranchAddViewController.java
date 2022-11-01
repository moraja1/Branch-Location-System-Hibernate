package com.una.presentation.controller.ViewControllers;

import com.una.business.DataServices;
import com.una.business.dtoModels.BranchDetails;
import com.una.business.dtoModels.CantonDetails;
import com.una.business.dtoModels.DistrictDetails;
import com.una.business.dtoModels.ProvinceDetails;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.view.ViewClasses.BranchAddView;
import com.una.presentation.view.ViewParent;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class BranchAddViewController {
    private static BranchAddView branch_add_view;

    public static  BranchAddView getBranch_add_view(){
        branch_add_view = new BranchAddView();
        return branch_add_view;
    }
    public static ViewParent getBranch_edit_view(Object[] model) {
        branch_add_view = new BranchAddView(model);
        return branch_add_view;
    }
    public static void addButtonPressed(){
        //Obtengo los valores
        Integer id = Integer.valueOf(branch_add_view.getBranchID());
        String reference = branch_add_view.getDistrict_combo().getName();
        String address = branch_add_view.getBranchDir();
        Byte zoning_percentage = Byte.valueOf(branch_add_view.getBranchZone());
        BranchPointer point = branch_add_view.getNewBranch();

        BranchDetails branch = new BranchDetails(id, address, point.getCoordX(), point.getCoordY(), reference, zoning_percentage);

        if(DataServices.addBranchExecution(branch)){
            JOptionPane.showMessageDialog(new JFrame(), "Sucursal agregada correctamente", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void clickOnMap(Point point) {
        Integer coordX = point.x;
        Integer coordY = point.y;
        BranchPointer branchInfo = new BranchPointer(coordX, coordY);
        branch_add_view.setNewBranch(branchInfo);
    }
    public static void windowClosed(){
        MainWindowViewController.windowInitialized();
    }

    public static void initProvinces() {
        List<ProvinceDetails> provinces = DataServices.getProvinces();
        branch_add_view.getProvince_combo().setModel(new DefaultComboBoxModel<>(new Vector<>(provinces)));
    }

    public static void provinceSelected() {
        ProvinceDetails provinceDetails = (ProvinceDetails) branch_add_view.getProvince_combo().getSelectedItem();
        List<CantonDetails> cantons = DataServices.getCantonsByProvince(provinceDetails.getNameProvince());
        branch_add_view.getCanton_combo().setModel(new DefaultComboBoxModel<>(new Vector<>(cantons)));
        branch_add_view.getCanton_combo().setEnabled(true);
    }
    public static void cantonSelected() {
        CantonDetails cantonDetails = (CantonDetails) branch_add_view.getProvince_combo().getSelectedItem();
        List<DistrictDetails> cantons = DataServices.getDistrictsByCanton(cantonDetails.getNameCanton());
        branch_add_view.getCanton_combo().setModel(new DefaultComboBoxModel<>(new Vector<>(cantons)));
        branch_add_view.getCanton_combo().setEnabled(true);
    }
}

