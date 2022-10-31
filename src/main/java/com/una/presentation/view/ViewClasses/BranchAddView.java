package com.una.presentation.view.ViewClasses;

import com.una.business.dtoModels.BranchDetails;
import com.una.presentation.controller.ViewControllers.BranchAddViewController;
import com.una.presentation.model.mouseListener.ImageMouseSensor;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.view.ViewParent;
import com.una.presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BranchAddView extends ViewParent {

    private JDialog dialog;
    private JTextField add_branch_cod_text;
    private JTextField add_branch_ref_text;
    private JTextField add_branch_dir_text;
    private JTextField add_branch_zon_text;
    private JButton add_branch_guardar_btn;
    private JButton add_branch_cancel_btn;
    private JPanel map_panel;
    private JLabel map_image;
    private JPanel branch_Add_Panel;
    private JLayeredPane map_layered_pane;
    private BranchPointer newBranch = null;
    private GeneralUtilities utils;
    public BranchAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if(!dialog.getContentPane().equals(branch_Add_Panel)){
            dialog.setContentPane(branch_Add_Panel);
            dialog.setName("BranchAddView");
            dialog.setSize(new Dimension(1000, 800));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX()/4, utils.getScreenY()/6);
            map_layered_pane = dialog.getLayeredPane();

            //Map Image
            ImageIcon map = new ImageIcon("src\\resources\\Mapa_de_Costa_Rica_(cantones_y_distritos).png");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(700, 700,  java.awt.Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image, BorderLayout.CENTER);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
    }

    public BranchAddView(Object[] model) {
        initComponents(model);
    }

    private void clearWindow() {
        add_branch_cod_text.setText("");
        add_branch_ref_text.setText("");
        add_branch_dir_text.setText("");
        add_branch_zon_text.setText("");
        if(newBranch != null){
            map_layered_pane.remove(newBranch);
        }
        map_layered_pane.repaint();
    }
    private void initComponents(Object[] model) {
        add_branch_cod_text.setText(String.valueOf(model[0]));
        add_branch_ref_text.setText(String.valueOf(model[1]));
        add_branch_dir_text.setText(String.valueOf(model[2]));
        add_branch_zon_text.setText(String.valueOf(model[3]));
        add_branch_cod_text.setEnabled(false);
    }
    @Override
    public void initComponents() {
        add_branch_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getBranchID().isEmpty() && !getBranchReference().isEmpty() && !getBranchDir().isEmpty()
                        && !getBranchZone().isEmpty() && getNewBranch() != null){
                    BranchAddViewController.addButtonPressed();
                }else if(getNewBranch() == null){
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar en el mapa la ubicacion de la sucursal.",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Debe llenar todos los campos.",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
                clearWindow();
            }
        });
        add_branch_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchAddViewController.windowClosed();
                dialog.dispose();
            }
        });
        add_branch_zon_text.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.'))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        dialog.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                BranchAddViewController.windowClosed();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BranchAddViewController.clickOnMap(e.getPoint());
            }
            @Override
            public void mouseClickedOutside(MouseEvent e) {
               e.consume();
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        dialog.setVisible(true);
    }
    public void updatePointer(BranchPointer newPointer) {
        int x = newPointer.getX() + 253;
        int y = newPointer.getY() - 45;
        newPointer.setBounds(x, y, 80, 80);
        map_layered_pane.add(newPointer, 1);
        map_layered_pane.repaint();
    }
    public String getBranchID() {return add_branch_cod_text.getText();}

    public String getBranchReference() {
        return add_branch_ref_text.getText();
    }
    public String getBranchDir() {return add_branch_dir_text.getText();}

    public String getBranchZone() {
        return add_branch_zon_text.getText();
    }
    public BranchPointer getNewBranch() {
        return newBranch;
    }
    public void setNewBranch(BranchPointer newBranch) {
        if (this.newBranch != null) {
            map_layered_pane.remove(this.newBranch);
        }
        this.newBranch = newBranch;
        updatePointer(newBranch);
    }
}
