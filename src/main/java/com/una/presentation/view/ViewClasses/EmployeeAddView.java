package com.una.presentation.view.ViewClasses;

import com.una.presentation.controller.ViewControllers.EmployeeAddViewController;
import com.una.presentation.controller.ViewControllers.MainWindowViewController;
import com.una.presentation.model.mouseListener.ImageMouseSensor;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.view.ViewParent;
import com.una.presentation.view.utils.GeneralUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeAddView extends ViewParent {
    private JDialog dialog;
    private JTextField add_emp_ced_text;
    private JTextField add_emp_nombre_text;
    private JTextField add_emp_tel_text;
    private JTextField add_emp_salario_text;
    private JButton add_emp_save_btn;
    private JButton add_emp_cancel_btn;
    private JPanel map_panel;
    private JPanel emp_add_panel;
    private JLabel map_image;
    private JLayeredPane map_layered_pane;
    private GeneralUtilities utils;
    private BranchPointer selectedBranch;

    public EmployeeAddView() {
        dialog = new JDialog(this, true);

        utils = GeneralUtilities.getInstanceOf();
        if (!dialog.getContentPane().equals(emp_add_panel)) {
            dialog.setContentPane(emp_add_panel);
            dialog.setName("EmployeeAddView");
            dialog.setSize(new Dimension(1000, 800));
            dialog.setTitle("Sistema de Sucursales y Empleados");
            dialog.setLocation(utils.getScreenX() / 4, utils.getScreenY() / 6);
            dialog.setResizable(false);
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

    public EmployeeAddView(Object[] model) {
        this();
        initComponents(model);
    }

    private void clearWindow() {
        add_emp_ced_text.setText("");
        add_emp_nombre_text.setText("");
        add_emp_tel_text.setText("");
        add_emp_salario_text.setText("");
    }

    private void initComponents(Object[] model) {
        add_emp_ced_text.setText(String.valueOf(model[0]));
        add_emp_nombre_text.setText(String.valueOf(model[1]));
        add_emp_tel_text.setText(String.valueOf(model[2]));
        add_emp_salario_text.setText(String.valueOf(model[3]));
    }

    @Override
    public void initComponents() {
        add_emp_save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getEmployeeID().isEmpty() && !getEmployeeName().isEmpty() && !getEmployeePhoneNumber().isEmpty()
                        && !getEmployeeSalary().isEmpty() && getSelectedBranch() != null) {
                    EmployeeAddViewController.addButtonPressed();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Debe llenar todos los campos",
                            "Agregar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add_emp_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
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
                MainWindowViewController.windowInitialized();
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
        add_emp_tel_text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        add_emp_ced_text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        add_emp_salario_text.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
                List<BranchPointer> pointers = (List<BranchPointer>) (List<?>) points;
                for (BranchPointer point : pointers) {
                    if (point.isSelected()) {
                        point.mouseClickedOutside(e);
                    }
                }
                e.consume();
            }

            @Override
            public void mouseClickedOutside(MouseEvent e) {
                List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
                List<BranchPointer> pointers = (List<BranchPointer>) (List<?>) points;
                for (BranchPointer point : pointers) {
                    if (point.isSelected()) {
                        selectedBranch = point;
                    }
                }
                e.consume();
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        //Controller initialize other components
        EmployeeAddViewController.windowInitialized();
        dialog.setVisible(true);
    }

    public void setBranchPointOnMap(BranchPointer point) {
        point.setVisible(false);
        int x = point.getX() + 242;
        int y = point.getY() - 41;
        point.setBounds(x, y, 80, 80);
        map_layered_pane.add(point, 1);
        repaintWindow();
    }

    private void repaintWindow() {
        List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        for (Component c : points) {
            c.setVisible(true);
            c.setEnabled(true);
        }
    }

    public String getEmployeeID() {
        return add_emp_ced_text.getText();
    }

    public String getEmployeeName() {
        return add_emp_nombre_text.getText();
    }

    public String getEmployeePhoneNumber() {
        return add_emp_tel_text.getText();
    }

    public String getEmployeeSalary() {
        return add_emp_salario_text.getText();
    }

    public BranchPointer getSelectedBranch() {
        return selectedBranch;
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
        emp_add_panel = new JPanel();
        emp_add_panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 4, new Insets(10, 10, 10, 10), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Cedula");
        emp_add_panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        emp_add_panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        add_emp_ced_text = new JTextField();
        emp_add_panel.add(add_emp_ced_text, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Nombre");
        emp_add_panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Telefono");
        emp_add_panel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        map_panel = new JPanel();
        map_panel.setLayout(new BorderLayout(0, 0));
        emp_add_panel.add(map_panel, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 6, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        map_image = new JLabel();
        map_image.setText("");
        map_panel.add(map_image, BorderLayout.CENTER);
        final JLabel label4 = new JLabel();
        label4.setText("Salario");
        emp_add_panel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_emp_nombre_text = new JTextField();
        emp_add_panel.add(add_emp_nombre_text, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_emp_tel_text = new JTextField();
        emp_add_panel.add(add_emp_tel_text, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_emp_salario_text = new JTextField();
        emp_add_panel.add(add_emp_salario_text, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        add_emp_save_btn = new JButton();
        add_emp_save_btn.setBackground(new Color(-14773725));
        add_emp_save_btn.setText("Guardar");
        emp_add_panel.add(add_emp_save_btn, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_emp_cancel_btn = new JButton();
        add_emp_cancel_btn.setBackground(new Color(-3909584));
        add_emp_cancel_btn.setText("Cancelar");
        emp_add_panel.add(add_emp_cancel_btn, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        emp_add_panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return emp_add_panel;
    }
}
