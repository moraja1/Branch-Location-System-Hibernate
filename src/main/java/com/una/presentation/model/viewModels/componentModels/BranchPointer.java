package com.una.presentation.model.viewModels.componentModels;

import com.una.business.dtoModels.BranchDetails;
import com.una.presentation.model.mouseListener.ImageMouseSensor;
import jakarta.persistence.criteria.CriteriaBuilder;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BranchPointer extends JLabel implements MouseInputListener {
    protected boolean selected = false;
    private Integer branchID;
    private Integer coordX;
    private Integer coordY;

    public BranchPointer(Integer coordX, Integer coordY) {
        this.branchID = null;
        this.coordX = coordX;
        this.coordY = coordY;
        create();
    }

    public BranchPointer(BranchDetails branchDetails) {
        this.branchID = branchDetails.getIdBranch();
        this.coordX = branchDetails.getCoordX();
        this.coordY = branchDetails.getCoordY();
        create();
    }

    private void create(){
        setLocation(coordX, coordY);
        setIcon(getPointerImage());
        addMouseListener(this);

        setVisible(true);
        setEnabled(true);
        setFocusable(true);
    }
    protected Icon getPointerImage() {
        return getPointerImage(selected);
    }

    private Icon getPointerImage(boolean temporary) {
        ImageIcon pointer;
        if(!temporary){
            pointer = new ImageIcon("src/main/resources/images/Ubicación no seleccionada.png");
        }else{
            pointer = new ImageIcon("src/main/resources/images/Ubicación seleccionada.png");
        }
        Image resizer = pointer.getImage();
        resizer = resizer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        pointer.setImage(resizer);
        return pointer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getParent().getMouseListeners()[0].mouseClicked(e);
        selected = true;
        setIcon(getPointerImage());
        if(getParent() != null){
            ((ImageMouseSensor)getParent().getMouseListeners()[0]).mouseClickedOutside(e);
        }
        repaint();
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!selected){
            setIcon(getPointerImage(true));
        }
        e.consume();
    }

    public Integer getBranchID() {
        return branchID;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!selected){
            setIcon(getPointerImage(false));
        }
        e.consume();
    }
    public void mouseClickedOutside(MouseEvent e){
        selected = false;
        setIcon(getPointerImage());
        repaint();
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.consume();
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        setIcon(getPointerImage());
    }
}
