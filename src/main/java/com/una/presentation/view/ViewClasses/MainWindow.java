package com.una.presentation.view.ViewClasses;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.una.business.dtoModels.BranchDetails;
import com.una.presentation.controller.ViewControllers.MainWindowViewController;
import com.una.presentation.model.mouseListener.ImageMouseSensor;
import com.una.presentation.model.viewModels.componentModels.BranchPointer;
import com.una.presentation.view.utils.GeneralUtilities;
import com.una.presentation.view.ViewParent;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Locale;

public class MainWindow extends ViewParent {
    private JPanel main_window_panel;
    private JTabbedPane tabbed_Pane;
    private JTextField branches_srch_bar;
    private JButton srch_branches_button;
    private JButton add_branch_button;
    private JButton erase_branch_button;
    private JButton report_branch_button;
    private JTextField employees_srch_bar;
    private JButton srch_employee_button;
    private JButton add_employee_button;
    private JButton erase_employee_button;
    private JButton report_employee_button;
    private JPanel tab_about;
    private JPanel emp_table_panel;
    private JPanel branch_table_panel;
    private JButton edit_employee_button;
    private JButton edit_branch_button;
    private JPanel map_panel;
    private JLabel map_image;
    private JLabel image_logo;
    private JTable emp_table;
    private JTable branch_table;
    private JLayeredPane map_layered_pane;

    public MainWindow() {
        super();
        GeneralUtilities utils = GeneralUtilities.getInstanceOf();

        if (!getContentPane().equals(main_window_panel)) {
            setContentPane(main_window_panel);
            setName("MainWindow");
            setSize(new Dimension(1400, 800));
            setTitle("Sistema de Sucursales y Empleados");
            setLocation(utils.getScreenX() / 11, utils.getScreenY() / 11);
            map_layered_pane = getLayeredPane();

            //Image about
            image_logo = new JLabel(new ImageIcon("src/main/resources/images/UNA_logo.png"));
            tab_about.add(image_logo, BorderLayout.CENTER);

            //Map Image
            ImageIcon map = new ImageIcon("src/main/resources/images/Mapa_de_Costa_Rica_(cantones_y_distritos).png");
            Image resizer = map.getImage();
            resizer = resizer.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
            map.setImage(resizer);
            map_image = new JLabel(map);
            map_image.setFocusable(true);
            map_panel.add(map_image, BorderLayout.CENTER);

            //Insert Tables
            emp_table = new JTable();
            emp_table_panel.setLayout(new BorderLayout());
            emp_table_panel.add(new JScrollPane(emp_table), BorderLayout.CENTER);

            branch_table = new JTable();
            branch_table_panel.setLayout(new BorderLayout());
            branch_table_panel.add(new JScrollPane(branch_table));
        }
    }

    public void initComponents() {
        //Employees Action Listeners
        add_employee_button.addActionListener(e -> MainWindowViewController.addEmployee());
        edit_employee_button.addActionListener(e -> {
            if (emp_table.getSelectedRow() != -1) {
                MainWindowViewController.editEmployee();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar un empleado de la tabla",
                        "Editar Empleado", JOptionPane.WARNING_MESSAGE);
            }
        });
        erase_employee_button.addActionListener(e -> {
            if (emp_table.getSelectedRow() != -1) {
                int n = JOptionPane.showConfirmDialog(new JFrame(), "Está seguro que desea borrar el empleado?",
                        "Confirmación requerida", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    MainWindowViewController.eraseEmployee();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar un empleado de la tabla",
                        "Eliminar Empleado", JOptionPane.WARNING_MESSAGE);
            }
        });
        srch_employee_button.addActionListener(e -> MainWindowViewController.searchEmployee());
        report_employee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.reportEmployee();
            }
        });
        //BRANCHES ACTION LISTENERS
        add_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.addBranch();
            }
        });
        edit_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (branch_table.getSelectedRow() != -1) {
                    MainWindowViewController.editBranch();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar una Sucursal de la tabla o del mapa",
                            "Editar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
                map_image.getMouseListeners()[0].mouseClicked(new MouseEvent(branch_table, 0, 0, 0, 100, 100, 1, false));
            }
        });
        erase_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (branch_table.getSelectedRow() != -1) {
                    int n = JOptionPane.showConfirmDialog(new JFrame(), "Está seguro que desea borrar la sucursal?",
                            "Confirmación requerida", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        MainWindowViewController.eraseBranch();
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar una Sucursal en el mapa",
                            "Eliminar Sucursal", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        srch_branches_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.searchBranch();
            }
        });
        report_branch_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindowViewController.reportBranch();
            }
        });
        tabbed_Pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                MainWindowViewController.windowInitialized();
                repaintWindow();
            }
        });
        employees_srch_bar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srch_employee_button.getActionListeners()[0].actionPerformed(e);
            }
        });
        employees_srch_bar.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (employees_srch_bar.getText().isEmpty()) {
                    MainWindowViewController.searchEmployee();
                }
            }
        });
        branches_srch_bar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srch_branches_button.getActionListeners()[0].actionPerformed(e);
            }
        });
        branches_srch_bar.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (branches_srch_bar.getText().isEmpty()) {
                    MainWindowViewController.windowInitialized();
                }
            }
        });
        map_image.addMouseListener(new ImageMouseSensor() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainWindowViewController.mapClicked(e);
                e.consume();
            }

            @Override
            public void mouseClickedOutside(MouseEvent e) {
                MainWindowViewController.mapClickedOutside(e);
                e.consume();
            }
        });
        branch_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainWindowViewController.tableRowSelected(e);
                e.consume();
            }
        });
        map_layered_pane.addMouseListener(map_image.getMouseListeners()[0]);
        //Controller initialize other components
        MainWindowViewController.windowInitialized();
        //Window opens
        setVisible(true);
    }

    public void selectTableRow(BranchPointer branch) {
        Integer branchID = branch.getBranchID();
        Integer tableID;
        for (int i = 0; i < branch_table.getRowCount(); i++) {
            tableID = (Integer) branch_table.getValueAt(i, 0);
            if (branchID == tableID) {
                branch_table.setRowSelectionInterval(i, i);
            }
        }
    }

    public int getSelectedTabIndex() {
        return tabbed_Pane.getSelectedIndex();
    }

    public void setTableModel(TableModel tm) {
        int tabSelected = getSelectedTabIndex();
        if (tabSelected == 0) {
            emp_table.setModel(tm);
        }
        if (tabSelected == 1) {
            branch_table.setModel(tm);
        }
    }

    public void setBranchPointOnMap(BranchPointer point) {
        point.setVisible(false);
        int x = point.getX() + 648;
        int y = point.getY() - 25;
        point.setBounds(x, y, 80, 80);
        map_layered_pane.add(point, 1);
        repaintWindow();
    }

    private void repaintWindow() {
        List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        if (getSelectedTabIndex() == 1) {
            for (Component c : points) {
                c.setVisible(true);
                c.setEnabled(true);
            }
            map_layered_pane.repaint();
        } else {
            for (Component c : points) {
                c.setVisible(false);
                c.setEnabled(false);
            }
            map_layered_pane.repaint();
        }
    }

    public JTable getSelectedTable() {
        if (getSelectedTabIndex() == 0) {
            return emp_table;
        } else {
            return branch_table;
        }

    }

    public String getEmployeesSearchBar() {
        return employees_srch_bar.getText();
    }

    public String getBranchesSearchBar() {
        return branches_srch_bar.getText();
    }

    public void cleanLayers() {
        List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        for (int i = 0; i < points.size(); i++) {
            map_layered_pane.remove(points.get(i));
        }
    }

    public List<BranchPointer> getPoints() {
        List<Component> points = List.of(map_layered_pane.getComponentsInLayer(0));
        List<BranchPointer> branches = (List<BranchPointer>) (List<?>) points;
        return branches;
    }

    public void removeTableSelection() {
        getSelectedTable().clearSelection();
    }

    public JTable getEmployee_table() {
        return emp_table;
    }

    public JTable getBranch_table() {
        return branch_table;
    }

    public void setPointSelected(BranchDetails e) {
        List<BranchPointer> branches = getPoints();
        for (BranchPointer bp : branches) {
            if (bp.getBranchID() == e.getIdBranch()) {
                bp.setSelected(true);
            }
        }
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
        main_window_panel = new JPanel();
        main_window_panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        main_window_panel.setFocusTraversalPolicyProvider(true);
        main_window_panel.setMinimumSize(new Dimension(1600, 900));
        tabbed_Pane = new JTabbedPane();
        main_window_panel.add(tabbed_Pane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1300, 900), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 5, new Insets(10, 10, 10, 10), -1, -1));
        tabbed_Pane.addTab("Empleados", panel1);
        final JLabel label1 = new JLabel();
        label1.setText("Nombre");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        srch_employee_button = new JButton();
        srch_employee_button.setText("Buscar");
        panel1.add(srch_employee_button, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_employee_button = new JButton();
        add_employee_button.setText("Agregar");
        panel1.add(add_employee_button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        erase_employee_button = new JButton();
        erase_employee_button.setText("Borrar");
        panel1.add(erase_employee_button, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        report_employee_button = new JButton();
        report_employee_button.setText("Reporte");
        panel1.add(report_employee_button, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        employees_srch_bar = new JTextField();
        panel1.add(employees_srch_bar, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(400, -1), new Dimension(500, -1), 0, false));
        emp_table_panel = new JPanel();
        emp_table_panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(emp_table_panel, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        edit_employee_button = new JButton();
        edit_employee_button.setText("Editar");
        panel1.add(edit_employee_button, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        tabbed_Pane.addTab("Sucursales", panel2);
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setEnabled(false);
        panel2.add(splitPane1, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 5, new Insets(10, 10, 10, 10), -1, -1));
        panel3.setMinimumSize(new Dimension(650, 118));
        panel3.setPreferredSize(new Dimension(650, 118));
        panel3.setRequestFocusEnabled(true);
        splitPane1.setLeftComponent(panel3);
        final JLabel label2 = new JLabel();
        label2.setText("Referencia");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        srch_branches_button = new JButton();
        srch_branches_button.setText("Buscar");
        panel3.add(srch_branches_button, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        add_branch_button = new JButton();
        add_branch_button.setText("Agregar");
        panel3.add(add_branch_button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        erase_branch_button = new JButton();
        erase_branch_button.setText("Borrar");
        panel3.add(erase_branch_button, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        branches_srch_bar = new JTextField();
        panel3.add(branches_srch_bar, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        report_branch_button = new JButton();
        report_branch_button.setText("Reporte");
        panel3.add(report_branch_button, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        branch_table_panel = new JPanel();
        branch_table_panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(branch_table_panel, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        edit_branch_button = new JButton();
        edit_branch_button.setText("Editar");
        panel3.add(edit_branch_button, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        map_panel = new JPanel();
        map_panel.setLayout(new BorderLayout(0, 0));
        map_panel.setAlignmentX(0.0f);
        map_panel.setAlignmentY(0.0f);
        map_panel.setAutoscrolls(false);
        map_panel.setMinimumSize(new Dimension(24, 24));
        map_panel.setOpaque(false);
        splitPane1.setRightComponent(map_panel);
        tab_about = new JPanel();
        tab_about.setLayout(new BorderLayout(0, 0));
        Font tab_aboutFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, tab_about.getFont());
        if (tab_aboutFont != null) tab_about.setFont(tab_aboutFont);
        tabbed_Pane.addTab("Acerca de ..", tab_about);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setHorizontalAlignment(0);
        label3.setText("SISE: Sistema de Sucursales y Empleados");
        tab_about.add(label3, BorderLayout.NORTH);
        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(0);
        label4.setText("Jason Mora Viquez - Carlos Daniel Sibaja Vindas - Brandon Alfaro Espinoza");
        tab_about.add(label4, BorderLayout.SOUTH);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main_window_panel;
    }

}
