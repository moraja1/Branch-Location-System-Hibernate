package com.una.presentation.view.ViewClasses;

import com.una.presentation.controller.ViewControllers.BranchAddViewController;
import com.una.presentation.model.mouseListener.ImageMouseSensor;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.view.ViewParent;
import com.una.presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BranchAddView extends ViewParent {
    private JTextField add_branch_cod_text;
    private JTextField add_branch_dir_text;
    private JTextField add_branch_zon_text;
    private JButton add_branch_guardar_btn;
    private JButton add_branch_cancel_btn;
    private JPanel map_panel;
    private JLabel map_image;
    private JPanel branch_Add_Panel;
    private JComboBox province_combo;
    private JComboBox canton_combo;
    private JComboBox district_combo;
    private JLayeredPane map_layered_pane;
    private BranchPointer newBranch = null;
    private GeneralUtilities utils;

    public BranchAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if (!dialog.getContentPane().equals(branch_Add_Panel)) {
            dialog.setContentPane(branch_Add_Panel);
            dialog.setName("BranchAddView");
            dialog.setSize(new Dimension(1000, 800));
            dialog.setTitle("Sistema de Sucuracles y Empleados");
            dialog.setLocation(utils.getScreenX() / 4, utils.getScreenY() / 6);
            map_layered_pane = dialog.getLayeredPane();

            //Map Image
            ImageIcon map = new ImageIcon("src/main/resources/images/Mapa_de_Costa_Rica_(cantones_y_distritos).png");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image, BorderLayout.CENTER);
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        clearWindow();
    }

    private void clearWindow() {
        add_branch_cod_text.setText("");
        add_branch_dir_text.setText("");
        add_branch_zon_text.setText("");
        province_combo.setSelectedIndex(-1);
        canton_combo.setSelectedIndex(-1);
        district_combo.setSelectedIndex(-1);
        canton_combo.setEnabled(false);
        district_combo.setEnabled(false);
        province_combo.setEnabled(true);
        if (newBranch != null) {
            map_layered_pane.remove(newBranch);
        }
        map_layered_pane.repaint();
    }

    public void initComponents(Object[] model) {
        BranchAddViewController.initCombosByModel(model);
        add_branch_cod_text.setText(String.valueOf(model[0]));
        add_branch_dir_text.setText(String.valueOf(model[2]));
        add_branch_zon_text.setText(String.valueOf(model[3]));
        add_branch_cod_text.setEnabled(false);
        BranchAddViewController.initPointer();
    }

    @Override
    public void initComponents() {
        add_branch_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getBranchID().isEmpty() && district_combo.getSelectedIndex() != -1 && !getBranchDir().isEmpty()
                        && !getBranchZone().isEmpty() && getNewBranch() != null && canton_combo.getModel().getSelectedItem() != null
                        && province_combo.getModel().getSelectedItem() != null && district_combo.getModel().getSelectedItem() != null) {
                    BranchAddViewController.addButtonPressed();
                } else if (getNewBranch() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar en el mapa la ubicacion de la sucursal.",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                    clearWindow();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Debe llenar todos los campos.",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add_branch_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchAddViewController.windowClosed();
                dialog.dispose();
            }
        });
        add_branch_zon_text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                BranchAddViewController.windowClosed();
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
        province_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchAddViewController.provinceSelected();
            }
        });
        canton_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchAddViewController.cantonSelected();
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        BranchAddViewController.initProvinces();
    }

    public void updatePointer(BranchPointer newPointer) {
        int x = newPointer.getX() + 253;
        int y = newPointer.getY() - 45;
        newPointer.setBounds(x, y, 80, 80);
        map_layered_pane.add(newPointer, 1);
        map_layered_pane.repaint();
    }

    public String getBranchID() {
        return add_branch_cod_text.getText();
    }

    public String getBranchDir() {
        return add_branch_dir_text.getText();
    }

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

    public JComboBox getProvince_combo() {
        return province_combo;
    }

    public JComboBox getCanton_combo() {
        return canton_combo;
    }

    public JComboBox getDistrict_combo() {
        return district_combo;
    }

    public JTextField getAdd_branch_zon_text() {
        return add_branch_zon_text;
    }

}
