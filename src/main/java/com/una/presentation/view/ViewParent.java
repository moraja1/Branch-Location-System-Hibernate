package com.una.presentation.view;

import com.una.presentation.controller.ViewControllers.MainWindowViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class ViewParent extends JFrame {
    protected JDialog dialog;
    public ViewParent(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\resources\\logoESCINF.jpg");
        setIconImage(icon);
    }

    /**
     * Initialize window components of every class that extends this superclass.
     */
    public abstract void initComponents();

    public abstract void initComponents(Object[] model);

    public JDialog getDialog() {
        return dialog;
    }
}
