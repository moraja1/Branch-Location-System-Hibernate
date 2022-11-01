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

    private JDialog dialog;
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

    public BranchAddView(Object[] model) {
        this();
        initComponents(model);
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

    private void initComponents(Object[] model) {
        add_branch_cod_text.setText(String.valueOf(model[0]));
        add_branch_dir_text.setText(String.valueOf(model[2]));
        add_branch_zon_text.setText(String.valueOf(model[3]));
        add_branch_cod_text.setEnabled(false);
    }

    @Override
    public void initComponents() {
        add_branch_guardar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getBranchID().isEmpty() && district_combo.getSelectedIndex() != -1 && !getBranchDir().isEmpty()
                        && !getBranchZone().isEmpty() && getNewBranch() != null) {
                    BranchAddViewController.addButtonPressed();
                } else if (getNewBranch() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar en el mapa la ubicacion de la sucursal.",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                } else {
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
        add_branch_zon_text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
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
        BranchAddViewController.initProvinces();
        dialog.setVisible(true);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        branch_Add_Panel = new JPanel();
        branch_Add_Panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 4, new Insets(10, 10, 10, 10), -1, -1));
        branch_Add_Panel.setPreferredSize(new Dimension(1300, 900));
        final JLabel label1 = new JLabel();
        label1.setText("Codigo");
        branch_Add_Panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Direccion");
        branch_Add_Panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Zonaje");
        branch_Add_Panel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_branch_cod_text = new JTextField();
        branch_Add_Panel.add(add_branch_cod_text, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_branch_dir_text = new JTextField();
        branch_Add_Panel.add(add_branch_dir_text, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_branch_zon_text = new JTextField();
        branch_Add_Panel.add(add_branch_zon_text, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_branch_guardar_btn = new JButton();
        add_branch_guardar_btn.setBackground(new Color(-14773725));
        add_branch_guardar_btn.setText("Guardar");
        branch_Add_Panel.add(add_branch_guardar_btn, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_branch_cancel_btn = new JButton();
        add_branch_cancel_btn.setBackground(new Color(-3909584));
        add_branch_cancel_btn.setText("Cancelar");
        branch_Add_Panel.add(add_branch_cancel_btn, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        map_panel = new JPanel();
        map_panel.setLayout(new BorderLayout(0, 0));
        branch_Add_Panel.add(map_panel, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 8, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        map_image = new JLabel();
        map_image.setText("");
        map_panel.add(map_image, BorderLayout.CENTER);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        branch_Add_Panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        branch_Add_Panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Provincia");
        branch_Add_Panel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        province_combo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        province_combo.setModel(defaultComboBoxModel1);
        branch_Add_Panel.add(province_combo, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Canton");
        branch_Add_Panel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        canton_combo = new JComboBox();
        canton_combo.setEnabled(false);
        branch_Add_Panel.add(canton_combo, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Distrito");
        branch_Add_Panel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        district_combo = new JComboBox();
        district_combo.setEnabled(false);
        branch_Add_Panel.add(district_combo, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return branch_Add_Panel;
    }
}
