package com.una.presentation.controller.ViewControllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.una.business.DataServices;

import com.una.business.dtoModels.BranchDetails;
import com.una.business.dtoModels.EmployeeDetails;
import com.una.presentation.controller.flowController.MainController;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.model.viewModels.componentModels.BranchTableModel;
import com.una.presentation.model.viewModels.componentModels.EmployeeTableModel;
import com.una.presentation.view.ViewClasses.MainWindow;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainWindowViewController {
    private static final MainWindow main_window = new MainWindow();

    public static MainWindow getMain_window() {
        return main_window;
    }
    public static void windowInitialized() {
        cleanWindow();
        updateTables();
        updateImages();
    }
    private static void cleanWindow(){
        main_window.cleanLayers();
    }
    private static void updateTables() {
        int selectedTab = main_window.getSelectedTabIndex();
        switch (selectedTab){
            case 0:
                List<EmployeeDetails> employees = DataServices.getEmployeesForTable();
                EmployeeTableModel empTableModel = new EmployeeTableModel(employees);
                main_window.setTableModel(empTableModel);
                break;
            case 1:
                List<BranchDetails> branches = DataServices.getBranchesForTable();
                BranchTableModel braTableModel = new BranchTableModel(branches);
                main_window.setTableModel(braTableModel);
                break;
            default:
                break;
        }
    }
    private static void updateImages() {
        List<BranchDetails> branches = DataServices.getBranchesForTable();
        if(branches != null){
            for(BranchDetails branch : branches){
                main_window.setBranchPointOnMap(new BranchPointer(branch));
            }
        }
    }
    public static void addEmployee() {
        MainController.changeWindow(EmployeeAddViewController.getEmployee_add_view());
    }
    public static void editEmployee() {
        Object[] model = getObjectModel();
        MainController.changeWindow(EmployeeAddViewController.getEmployee_edit_view(model));
    }
    public static void eraseEmployee() {
        Integer id = (Integer) getObjectModel()[0];
        String name = String.valueOf(getObjectModel()[1]);
        String phone_number = String.valueOf(getObjectModel()[2]);
        double salary = (Double)getObjectModel()[3];
        String reference = String.valueOf(getObjectModel()[4]);
        Byte zone_percentage = (Byte)getObjectModel()[5];
        double total_salary = (Double)getObjectModel()[6];

        EmployeeDetails employee = new EmployeeDetails(id, name, phone_number, salary, reference, zone_percentage, total_salary);
        if(DataServices.removeEmployeeExecution(employee)){
            JOptionPane.showMessageDialog(new JFrame(), "Empleado eliminado correctamente",
                    "Eliminar Empleado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Empleado no eliminado",
                    "Eliminar Empleado", JOptionPane.INFORMATION_MESSAGE);
        }
        updateTables();
    }
    public static void searchEmployee() {
        String id = main_window.getEmployeesSearchBar();
        if(id.isEmpty()){
            updateTables();
        }else{
            List<EmployeeDetails> employees = DataServices.getEmployeesForTable();
            List<EmployeeDetails> employeesFinded = new ArrayList<>();
            for(EmployeeDetails e : employees){
                if(e.getIdEmployee().equals(id) || e.getNameEmployee().equals(id) || e.getPhoneNumber().equals(id) || e.getBranchReference().equals(id)){
                    employeesFinded.add(e);
                }
            }

            if(!employeesFinded.isEmpty()){
                EmployeeTableModel empTableModel = new EmployeeTableModel(employeesFinded);
                main_window.setTableModel(empTableModel);
            }
        }
    }public static void reportEmployee() {
        String path = "";
        JFileChooser j = new JFileChooser();
        java.util.Date fecha = new Date();

        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(main_window);

        if(x == JFileChooser.APPROVE_OPTION){
            path = j.getSelectedFile().getPath();
        }

        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc,new FileOutputStream(path + "\\Employees.pdf"));
            doc.open();

            PdfPTable Title2 = new PdfPTable(3);
            Title2.addCell("LISTA DE EMPLEADOS");
            Title2.addCell("");
            Title2.addCell(String.valueOf(fecha));

            Title2.setWidthPercentage(100f);

            PdfPTable pdfTable = new PdfPTable(7);
            pdfTable.setWidthPercentage(100f);

            //ADD HEADERS
            pdfTable.addCell("ID");
            pdfTable.addCell("Nombre");
            pdfTable.addCell("Tel");
            pdfTable.addCell("Salario");
            pdfTable.addCell("Sucursal");
            pdfTable.addCell("% Zona");
            pdfTable.addCell("Salario Total");


            for(int i = 0; i < main_window.getEmployee_table().getRowCount(); i++){

                String ID = main_window.getEmployee_table().getValueAt(i,0).toString();
                String NAME = main_window.getEmployee_table().getValueAt(i,1).toString();
                String TEL = main_window.getEmployee_table().getValueAt(i,2).toString();
                String SAL = main_window.getEmployee_table().getValueAt(i,3).toString();
                String SUC = main_window.getEmployee_table().getValueAt(i,4).toString();
                String ZON = main_window.getEmployee_table().getValueAt(i,5).toString();
                String STOTAL = main_window.getEmployee_table().getValueAt(i,6).toString();

                pdfTable.addCell(ID);
                pdfTable.addCell(NAME);
                pdfTable.addCell(TEL);
                pdfTable.addCell(SAL);
                pdfTable.addCell(SUC);
                pdfTable.addCell(ZON);
                pdfTable.addCell(STOTAL);
            }
            doc.add(Title2);
            doc.add(pdfTable);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        doc.close();
    }

    public static void addBranch() {
        MainController.changeWindow(BranchAddViewController.getBranch_add_view());
    }
    public static void editBranch() {
        Object[] model = getObjectModel();
        MainController.changeWindow(BranchAddViewController.getBranch_edit_view(model));
    }
    public static void eraseBranch() {
        Integer id = (Integer) getObjectModel()[0];
        String reference = String.valueOf(getObjectModel()[1]);
        String address = String.valueOf(getObjectModel()[2]);
        Byte zoning_percentage = (Byte) getObjectModel()[3];
        Integer coordX = (Integer) getObjectModel()[4];
        Integer coordY = (Integer) getObjectModel()[5];

        BranchDetails branchInfo = new BranchDetails(id, address, coordX, coordY, reference, zoning_percentage);
        if(DataServices.removeBranchExecution(branchInfo)){
            JOptionPane.showMessageDialog(new JFrame(), "Sucursal eliminada correctamente",
                    "Eliminar Sucursal", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Sucursal no eliminada",
                    "Eliminar Sucursal", JOptionPane.INFORMATION_MESSAGE);
        }
        windowInitialized();
    }
    public static void searchBranch() {
        String id = main_window.getBranchesSearchBar();
        if(id.isEmpty()){
            updateTables();
        }else{
            List<BranchDetails> branches = DataServices.getBranchesForTable();
            List<BranchDetails> branchesFinded = new ArrayList<>();
            for(BranchDetails e : branches){
                if(e.getIdBranch().equals(id) || e.getReference().equals(id) || e.getCoordX().equals(id) || e.getCoordY().equals(id)){
                    if(branchesFinded.isEmpty()){
                        main_window.setPointSelected(e);
                    }
                    branchesFinded.add(e);
                }
            }
            BranchTableModel branchTableModel = new BranchTableModel(branchesFinded);
            main_window.setTableModel(branchTableModel);

            List<BranchPointer> pointers = main_window.getPoints();
            for(BranchDetails bd : branchesFinded){
                for(BranchPointer bp : pointers){
                    if(bd.getIdBranch() == bp.getBranchID()){
                        if(bp.isSelected()){
                            main_window.selectTableRow(bp);
                        }
                    }
                }
            }
            main_window.cleanLayers();
            pointers.forEach(a-> main_window.setBranchPointOnMap(a));
        }
    }

    public static void reportBranch()  {
        String path = "";
        JFileChooser j = new JFileChooser();
        java.util.Date fecha = new Date();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(main_window);

        if(x == JFileChooser.APPROVE_OPTION){
            path = j.getSelectedFile().getPath();
        }

        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc,new FileOutputStream(path + "\\Branches.pdf"));
            doc.open();
            PdfPTable Title2 = new PdfPTable(3);
            Title2.addCell("LISTA DE SUCURSALES");
            Title2.addCell("");
            Title2.addCell(String.valueOf(fecha));

            PdfPTable pdfTable = new PdfPTable(5);

            //ADD HEADERS
            pdfTable.addCell("ID");
            pdfTable.addCell("Referencia");
            pdfTable.addCell("Direccion");
            pdfTable.addCell("% Zona");
            pdfTable.addCell("Coordenadas");

            for(int i = 0; i < main_window.getBranch_table().getRowCount(); i++){

                String ID = main_window.getBranch_table().getValueAt(i,0).toString();
                String REFE = main_window.getBranch_table().getValueAt(i,1).toString();
                String DIR = main_window.getBranch_table().getValueAt(i,2).toString();
                String POR = main_window.getBranch_table().getValueAt(i,3).toString();
                String COOR = main_window.getBranch_table().getValueAt(i,4).toString();

                pdfTable.addCell(ID);
                pdfTable.addCell(REFE);
                pdfTable.addCell(DIR);
                pdfTable.addCell(POR);
                pdfTable.addCell(COOR);
            } doc.add(Title2);
            doc.add(pdfTable);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        doc.close();
    }

    public static Object[] getObjectModel(){
        JTable table = main_window.getSelectedTable();
        int row = table.getSelectedRow();

        Object[] model = new Object[table.getColumnCount()];

        for(int i = 0; i < table.getColumnCount(); i++){
            model[i] = table.getValueAt(row, i);
        }
        return model;
    }
    public static void mapClicked(MouseEvent e) {
        List<BranchPointer> branches = main_window.getPoints();
        for (BranchPointer branch : branches) {
            if(branch.isSelected()){
                branch.mouseClickedOutside(e);
            }
        }
        main_window.removeTableSelection();
    }
    public static void mapClickedOutside(MouseEvent e) {
        List<BranchPointer> branches = main_window.getPoints();
        for (BranchPointer branch : branches) {
            if(branch.isSelected()){
                main_window.selectTableRow(branch);

            }
        }
    }
    public static void tableRowSelected(MouseEvent e) {
        List<BranchPointer> pointers = main_window.getPoints();
        for (BranchPointer bp : pointers) {
            if(bp.isSelected()){
                bp.mouseClickedOutside(e);
            }
        }
        Object[] model = getObjectModel();
        for (BranchPointer bp : pointers) {
            if(bp.getBranchID().equals(model[0])){
                bp.mouseClicked(e);
            }
        }
    }
    public static BranchPointer getSelectedPoint() {
        List<BranchPointer> pointers = main_window.getPoints();
        for (BranchPointer pointer : pointers) {
            if(pointer.isSelected()){
                return pointer;
            }
        }
        return null;
    }
}
