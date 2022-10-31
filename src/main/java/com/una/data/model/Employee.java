package com.una.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends EntityParent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_employee")
    private Integer idEmployee;
    @Basic
    @Column(name = "name_employee")
    private String nameEmployee;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "base_salary")
    private Double baseSalary;
    @ManyToOne
    @JoinColumn(name = "id_branch", referencedColumnName = "id_branch", nullable = false)
    private Branch branchByIdBranch;

    public Integer getId() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Branch getBranchByIdBranch() {
        return branchByIdBranch;
    }

    public void setBranchByIdBranch(Branch branchByIdBranch) {
        this.branchByIdBranch = branchByIdBranch;
    }
}
