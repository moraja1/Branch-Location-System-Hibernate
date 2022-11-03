package com.una.presentation.controller.ViewControllers;

import com.una.business.DataServices;
import com.una.business.dtoModels.*;
import com.una.presentation.controller.dto.BranchDetailsInput;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.view.ViewClasses.BranchAddView;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class BranchAddViewController {
    private static BranchAddView branch_add_view;

    private static Object[] model = null;

    public static  BranchAddView getBranch_add_view(){
        branch_add_view = new BranchAddView();
        return branch_add_view;
    }
    public static void addButtonPressed(){
        //Obtengo los valores
        Integer id = Integer.valueOf(branch_add_view.getBranchID());
        Integer districtId = ((DistrictDetails)branch_add_view.getDistrict_combo().getSelectedItem()).getIdDistrict();
        Integer cantonId = ((CantonDetails)branch_add_view.getCanton_combo().getSelectedItem()).getIdCanton();
        Byte provinceId = ((ProvinceDetails)branch_add_view.getProvince_combo().getSelectedItem()).getIdProvince();
        String address = branch_add_view.getBranchDir();
        BranchPointer point = branch_add_view.getNewBranch();

        BranchDetailsInput branch = new BranchDetailsInput(id, address, point.getCoordX(), point.getCoordY(), districtId, cantonId, provinceId);
        if (model == null) {
            if(DataServices.addBranchExecution(branch)){
                JOptionPane.showMessageDialog(new JFrame(), "Sucursal agregada correctamente", "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            if(DataServices.editBranchExecution(branch)){
                JOptionPane.showMessageDialog(new JFrame(), "Sucursal agregada correctamente", "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Error en almacenamiento de los datos", "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public static void clickOnMap(Point point) {
        Integer coordX = point.x;
        Integer coordY = point.y;
        BranchPointer branchInfo = new BranchPointer(coordX, coordY);
        branch_add_view.setNewBranch(branchInfo);
    }
    public static void windowClosed(){
        model = null;
        MainWindowViewController.windowInitialized();
    }

    public static void initProvinces() {
        List<ProvinceDetails> provinces = DataServices.getProvinces();
        branch_add_view.getProvince_combo().setModel(new DefaultComboBoxModel<>(new Vector<>(provinces)));
    }
    public static void initCombosByModel(Object[] model) {
        BranchAddViewController.model = model;
        ReferenceDetails referenceDetails = DataServices.getReferenceDetails((Integer)model[0]);
        JComboBox cboxModel = branch_add_view.getProvince_combo();
        for(int i = 0; i < cboxModel.getItemCount(); i++){
            if(((ProvinceDetails)cboxModel.getItemAt(i)).getIdProvince() == referenceDetails.getIdProvince()){
                cboxModel.setSelectedItem(i);
            }
        }
        provinceSelected();
        cboxModel = branch_add_view.getCanton_combo();
        for(int i = 0; i < cboxModel.getItemCount(); i++){
            if(((CantonDetails)cboxModel.getItemAt(i)).getNameCanton().equals(referenceDetails.getNameCanton())){
                cboxModel.setSelectedItem(i);
            }
        }
        cantonSelected();
        cboxModel = branch_add_view.getDistrict_combo();
        for(int i = 0; i < cboxModel.getItemCount(); i++){
            if(((DistrictDetails)cboxModel.getItemAt(i)).getNameDistrict().equals(referenceDetails.getNameDistrict())){
                cboxModel.setSelectedItem(i);
            }
        }
    }
    public static void provinceSelected() {
        ProvinceDetails provinceDetails = (ProvinceDetails) branch_add_view.getProvince_combo().getModel().getSelectedItem();
        if(provinceDetails != null){
            List<CantonDetails> cantons = DataServices.getCantonsByProvince(provinceDetails.getNameProvince());
            branch_add_view.getCanton_combo().setModel(new DefaultComboBoxModel<>(new Vector<>(cantons)));
            branch_add_view.getCanton_combo().setEnabled(true);
            branch_add_view.getAdd_branch_zon_text().setText(String.valueOf(provinceDetails.getZonePercentage()));
        }
    }
    public static void cantonSelected() {
        CantonDetails cantonDetails = (CantonDetails) branch_add_view.getCanton_combo().getModel().getSelectedItem();
        if(cantonDetails != null){
            List<DistrictDetails> districts = DataServices.getDistrictsByCanton(cantonDetails.getNameCanton());
            branch_add_view.getDistrict_combo().setModel(new DefaultComboBoxModel<>(new Vector<>(districts)));
            branch_add_view.getDistrict_combo().setEnabled(true);
        }
    }

    public static void initPointer() {
        BranchDetails branchDetails = DataServices.getBranchDetails((Integer) model[0]);
        if(branchDetails != null){
            branch_add_view.setNewBranch(new BranchPointer(branchDetails));
        }
    }
}

